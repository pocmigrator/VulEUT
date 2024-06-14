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

public class SootExample {
    public static void main(String[] args) {
        // 配置 Soot
        Options.v().set_prepend_classpath(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_output_format(Options.output_format_jimple);

        // 设置 whole-program mode
        Options.v().set_whole_program(true);

        // 添加必要的基本类
//        Scene.v().addBasicClass("java.lang.ThreadGroup", SootClass.SIGNATURES);
//        Scene.v().addBasicClass("java.lang.Object", SootClass.SIGNATURES);
//        Scene.v().addBasicClass("java.lang.String", SootClass.SIGNATURES);

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
        Options.v().set_include(new ArrayList<>(List.of("demo.")));
        // 设置 Spark 作为调用图算法
        Options.v().setPhaseOption("cg.spark", "on");
//        Options.v().setPhaseOption("cg.cha", "on");
        Options.v().setPhaseOption("cg", "safe-newinstance:true");
        Options.v().set_no_bodies_for_excluded(true);
        // 创建调用图
        PackManager.v().getPack("cg").apply();

        // 输出调用图信息
        CallGraph callGraph = Scene.v().getCallGraph();
        System.out.println("Call Graph:");
        callGraph.forEach(edge -> {
            if (isProjectClass(edge.getSrc().method().getDeclaringClass().getName()) && 
                isProjectClass(edge.getTgt().method().getDeclaringClass().getName())) {
                System.out.println("src: " + edge.getSrc().method().getName());
                System.out.println("tgt: " + edge.getTgt().method().getName());
            }
        });

        // 可选：将调用图写入到 DOT 文件
        DotGraph dotGraph = new DotGraph("callgraph");
        callGraph.forEach(edge -> {
            if (isProjectClass(edge.getSrc().method().getDeclaringClass().getName()) && 
                isProjectClass(edge.getTgt().method().getDeclaringClass().getName())) {
                dotGraph.drawEdge(edge.getSrc().method().getDeclaringClass().getName() + "." + edge.getSrc().method().getName(),
                        edge.getTgt().method().getDeclaringClass().getName() + "." + edge.getTgt().method().getName());
            }
        });
        dotGraph.plot("callgraph.dot");

        // 打印分析结果
        System.out.println("Analysis completed.");
    }

    private static boolean isProjectClass(String className) {
        // 只包含包名为 "demo" 的类
        return className.startsWith("demo");
    }
}
