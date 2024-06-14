import soot.*;
import soot.options.Options;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.util.dot.DotGraph;

import java.io.File;

public class SootDemo1 {

    public static void main(String[] args) {
        // Initialize Soot
        soot.G.reset();

        // Set Soot options
        Options.v().set_output_format(Options.output_format_jimple);
        Options.v().set_allow_phantom_refs(true);

        // Set the source code format to Java
        Options.v().set_src_prec(Options.src_prec_java);

        // Add the directory containing your source code to Soot's classpath
        String classPath = "/Users/gaoyi/IdeaProjects/LLMPocMigration/src/main/java";

        // Get JAVA_HOME and construct the module path for JDK 9+
        String javaHome = System.getProperty("java.home");
        System.out.println("JAVA_HOME: " + javaHome);

        // Since java.ext.dirs is null in Java 9+, we manually add necessary paths
        String jmodsPath = javaHome + "/jmods";
        System.out.println("jmods path: " + jmodsPath);

        // Manually specify the paths to ensure all required libraries are included
        String sootClasspath = classPath + File.pathSeparator + jmodsPath;

        // Set Soot classpath to include the source path and the jmods path
        Options.v().set_soot_classpath(sootClasspath);

        // Specify the fully-qualified class name to be analyzed
        String className = "utils.CallUtil";

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
        System.out.println("Call Graph:");
        callGraph.forEach(edge -> {
            System.out.println("src: " + edge.getSrc().method().getName());
            System.out.println("tgt: " + edge.getTgt().method().getName());
        });

        // Optionally, write the call graph to a DOT file
        DotGraph dotGraph = new DotGraph("callgraph");
        callGraph.forEach(edge -> {
            dotGraph.drawEdge(edge.getSrc().method().getDeclaringClass().getName() + "." + edge.getSrc().method().getName(),
                    edge.getTgt().method().getDeclaringClass().getName() + "." + edge.getTgt().method().getName());
        });
        dotGraph.plot("callgraph.dot");
    }
}
