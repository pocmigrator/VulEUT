import soot.*;
import soot.options.Options;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.util.dot.DotGraph;

public class SootDemo {

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
        // For Java 9 and above, add the module path
        Options.v().set_soot_classpath(classPath + ":" + System.getProperty("java.home") + "/jmods");

        // Set the Java home path
        System.setProperty("java.home", "/Library/Java/JavaVirtualMachines/jdk-9.0.4.jdk/Contents/Home");

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
