package demo;

import soot.PackManager;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.options.Options;
import soot.util.dot.DotGraph;

import java.util.ArrayList;
import java.util.List;

public class SootExample2 {
    public static void main(String[] args) {
        // 配置 Soot
        Options.v().set_prepend_classpath(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_output_format(Options.output_format_jimple);

        // Set the source code format to Java
        Options.v().set_src_prec(Options.src_prec_java);


        List<String> processDirs = new ArrayList<>();
        processDirs.add("target/classes"); // 你需要分析的 class 文件路径
        Options.v().set_process_dir(processDirs);

        // 加载主类
        Scene.v().loadNecessaryClasses();

        // 运行分析或转换
        SootClass sootClass = Scene.v().getSootClass("demo.HelloWorld");
        for (SootMethod method : sootClass.getMethods()) {
            System.out.println("Method: " + method.getName());
            // 执行其他分析逻辑
        }


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

        // 打印分析结果
        System.out.println("Analysis completed.");
    }
}
