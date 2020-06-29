package com.example.apt.handler.visits;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;

import java.lang.reflect.Field;

/**
 * @Author myou
 * @Date 2020/6/28  3:41 下午
 */
public class ValidMode extends TreeTranslator {
    private Context context;

    public ValidMode(Context context) {
        this.context = context;
    }

    @Override
    public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
        super.visitMethodDef(jcMethodDecl);
        Names names = new Names(context);
        TreeMaker treeMaker = TreeMaker.instance(context);
        JCTree.JCVariableDecl map = treeMaker.VarDef(treeMaker.Modifiers(Flags.PRIVATE), names.fromString("map"), treeMaker.Ident(names.fromString("Map<String, Object>")), null);

        List<JCTree.JCVariableDecl> parameters = jcMethodDecl.getParameters();
        parameters.forEach(item -> {
//            System.out.println(String.format("paName:%s", item.));
            Field[] fields = item.getClass().getDeclaredFields();
            for (Field field : fields) {
                System.out.println(String.format("fieldName:%s,fieldType:%s", field.getName(), field.getModifiers()));
            }
        });
    }
}
