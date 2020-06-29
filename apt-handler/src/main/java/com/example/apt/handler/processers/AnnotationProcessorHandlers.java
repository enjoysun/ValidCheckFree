package com.example.apt.handler.processers;

import com.example.apt.handler.annotations.ValidCheck;
import com.example.apt.handler.visits.ValidMode;
import com.google.auto.service.AutoService;
import com.sun.source.tree.Tree;
import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author myou
 * @Date 2020/6/28  3:21 下午
 */
public class AnnotationProcessorHandlers {
    @AutoService(Processor.class)
    public static class ValidCheckHandler extends AbstractProcessor {

        // 抽象语法树
        private Trees mTrees;

        private Messager messager;

        private Filer filer;

        private Elements elements;

        private Context context;

        @Override
        public Set<String> getSupportedOptions() {
            return super.getSupportedOptions();
        }

        @Override
        public Set<String> getSupportedAnnotationTypes() {
            HashSet<String> set = new HashSet<>();
            set.add(ValidCheck.class.getCanonicalName());
            return Collections.unmodifiableSet(set);
        }

        @Override
        public SourceVersion getSupportedSourceVersion() {
            return SourceVersion.latestSupported();
        }

        @Override
        public synchronized void init(ProcessingEnvironment processingEnv) {
            super.init(processingEnv);
            System.out.println("init1");
            messager = processingEnv.getMessager();
            filer = processingEnv.getFiler();
            elements = processingEnv.getElementUtils();
            context = ((JavacProcessingEnvironment) processingEnv).getContext();
            if (processingEnv instanceof JavacProcessingEnvironment) {
                mTrees = Trees.instance((JavacProcessingEnvironment) processingEnv);
            }
        }

        @Override
        public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
            if (!roundEnv.processingOver() && mTrees != null) {
                roundEnv.getElementsAnnotatedWith(ValidCheck.class).stream().filter(item -> item.getKind() == ElementKind.METHOD)
                        .forEach(item -> {
                            ExecutableElement element = (ExecutableElement) item;
                            System.out.println(String.format("treeName:%s,treeType:%s", element.getSimpleName(), element.getParameters()));
                            System.out.println(String.format("is——here:%s", mTrees.getTree(element) == null));
                            ((JCTree) mTrees.getTree(element)).accept(new ValidMode(context));
                        });
            }
            return true;
        }

        @Override
        public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member, String userText) {
            return super.getCompletions(element, annotation, member, userText);
        }

        @Override
        protected synchronized boolean isInitialized() {
            return super.isInitialized();
        }
    }
}
