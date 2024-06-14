package core.entity.analysis.graph;

import soot.*;
import soot.jimple.spark.SparkTransformer;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.options.Options;

import java.util.HashMap;

public class GraphBuild {
    public CallGraph buildGraph(String classPath, String className) {
        Options.v().set_prepend_classpath(true);
        Options.v().set_output_format(Options.output_format_none);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_whole_program(true);

        Options.v().set_soot_classpath(classPath);

        SootClass sootClass = Scene.v().loadClassAndSupport(className);

        sootClass.setApplicationClass();
        enableSpark();

        return Scene.v().getCallGraph();
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
