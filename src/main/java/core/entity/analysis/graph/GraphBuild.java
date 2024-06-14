package core.entity.analysis.graph;

import soot.*;
import soot.jimple.spark.SparkTransformer;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.options.Options;
import soot.util.dot.DotGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GraphBuild {
    public CallGraph buildGraph(String classPath, String className) {
        Options.v().set_prepend_classpath(true);
        Options.v().set_output_format(Options.output_format_none);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_whole_program(true);

        Options.v().set_soot_classpath(classPath);

        SootClass sootClass = Scene.v().loadClassAndSupport(className);

        sootClass.setApplicationClass();

        // spark
        enableSpark();

        return Scene.v().getCallGraph();
    }

    public static CallGraph build(String classPath, String className){
        // Initialize Soot
        soot.G.reset();

        // Set Soot options
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_allow_phantom_refs(true);

        // Set the source code format to Java
        Options.v().set_src_prec(Options.src_prec_java);

        Options.v().set_soot_classpath(classPath + ":" + System.getProperty("java.home") + "/jmods");
        System.setProperty("java.home", "/Library/Java/JavaVirtualMachines/jdk-9.0.4.jdk/Contents/Home");

        // Load the class to be analyzed
        SootClass sourceClass = Scene.v().loadClassAndSupport(className);
        sourceClass.setApplicationClass();

        // Load all necessary classes
        Scene.v().loadNecessaryClasses();

        // Set Spark as the call graph algorithm
        Options.v().setPhaseOption("cg.spark", "on");

        // Create the call graph
        PackManager.v().getPack("cg").apply();

        // Output the call graph information
        CallGraph callGraph = Scene.v().getCallGraph();
        DotGraph dotGraph = new DotGraph("callgraph");
        callGraph.forEach(edge -> {
            dotGraph.drawEdge(edge.getSrc().method().getDeclaringClass().getName() + "." + edge.getSrc().method().getName(),
                    edge.getTgt().method().getDeclaringClass().getName() + "." + edge.getTgt().method().getName());
        });

        return callGraph;
    }
    public static void main(String[] args) {
        Options.v().set_prepend_classpath(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_output_format(Options.output_format_jimple);

        Options.v().set_whole_program(true);

        List<String> processDirs = new ArrayList<>();
        processDirs.add("target/classes");
        Options.v().set_process_dir(processDirs);

        Scene.v().loadNecessaryClasses();

        SootClass sootClass = Scene.v().getSootClass("demo.Demo");
        for (SootMethod method : sootClass.getMethods()) {
            System.out.println("Method: " + method.getName());
        }
        Options.v().set_include(new ArrayList<>(List.of("demo.")));
        Options.v().setPhaseOption("cg.spark", "on");
//        Options.v().setPhaseOption("cg.cha", "on");
        Options.v().setPhaseOption("cg", "safe-newinstance:true");
        Options.v().set_no_bodies_for_excluded(true);
        PackManager.v().getPack("cg").apply();

        CallGraph callGraph = Scene.v().getCallGraph();
        System.out.println("Call Graph:");
        callGraph.forEach(edge -> {
            if (isProjectClass(edge.getSrc().method().getDeclaringClass().getName(), "") &&
                    isProjectClass(edge.getTgt().method().getDeclaringClass().getName(), "")) {
                System.out.println("src: " + edge.getSrc().method().getName());
                System.out.println("tgt: " + edge.getTgt().method().getName());
            }
        });

        DotGraph dotGraph = new DotGraph("callgraph");
        callGraph.forEach(edge -> {
            if (isProjectClass(edge.getSrc().method().getDeclaringClass().getName(), "") &&
                    isProjectClass(edge.getTgt().method().getDeclaringClass().getName(), "")) {
                dotGraph.drawEdge(edge.getSrc().method().getDeclaringClass().getName() + "." + edge.getSrc().method().getName(),
                        edge.getTgt().method().getDeclaringClass().getName() + "." + edge.getTgt().method().getName());
            }
        });
    }

    private static boolean isProjectClass(String className, String filter) {
        return className.startsWith(filter);
    }
    private static void enableSpark() {
        //Enable Spark
        HashMap<String, String> opt = new HashMap<String, String>();
        //opt.put("verbose","true");
        opt.put("propagator", "worklist");
        opt.put("simple-edges-bidirectional", "false");
        opt.put("on-fly-cg", "true");
        opt.put("set-impl", "double");
        opt.put("double-set-old", "hybrid");
        opt.put("double-set-new", "hybrid");
//
        opt.put("pre_jimplify", "true");
        SparkTransformer.v().transform("", opt);
        PhaseOptions.v().setPhaseOption("cg.spark", "enabled:true");
    }

}
