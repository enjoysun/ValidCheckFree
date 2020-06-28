package com.example.apt.handler.visits;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.List;

/**
 * @Author myou
 * @Date 2020/6/28  3:41 下午
 */
public class ValidMode extends TreeTranslator {
    @Override
    public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
        super.visitMethodDef(jcMethodDecl);
        System.out.println("我是方法修饰");
        List<JCTree.JCVariableDecl> parameters = jcMethodDecl.getParameters();
        parameters.forEach(System.out::println);
    }

    @Override
    public void visitVarDef(JCTree.JCVariableDecl jcVariableDecl) {
        super.visitVarDef(jcVariableDecl);
        System.out.println(String.format("我是参数修饰:%s", jcVariableDecl));
    }
}
