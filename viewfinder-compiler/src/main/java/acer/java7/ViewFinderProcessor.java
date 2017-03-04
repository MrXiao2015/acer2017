package acer.java7;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import acer.java7.model.AnnotatedClass;
import acer.java7.model.BindViewField;
import acer.java7.model.OnClickMethod;

@AutoService(Processor.class)
public class ViewFinderProcessor extends AbstractProcessor {
    private Map<String, AnnotatedClass> mAnnotatedClassMap = new HashMap<>();

    /**
     * 使用 Google 的 auto-service 库可以自动生成 META-INF/services/javax.annotation.processing.Processor 文件
     */

    private Filer mFiler; //文件相关的辅助类
    private Elements mElementUtils; //元素相关的辅助类
    private Messager mMessager; //日志相关的辅助类

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
    }

    /**
     * @return 该注解处理器处理的注解的集合
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(BindView.class.getCanonicalName());
        annotationTypes.add(OnClick.class.getCanonicalName());
        return annotationTypes;
    }

    /**
     * @return 指定支持的java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 1.扫描
     * 2.处理注解
     * 3.生成java文件
     *
     * @param set
     * @param roundEnvironment 可以通过该参数，查询被特定注解标注的元素
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mAnnotatedClassMap.clear();//process可能会多次调用

        try {
            processBindView(roundEnvironment);
            processOnClick(roundEnvironment);
        } catch (IllegalArgumentException e) {
            error(e.getMessage());
            return true;
        }

        for (AnnotatedClass annotatedClass : mAnnotatedClassMap.values()) {
            try {
                info("Generating file for %s", annotatedClass.getFullClassName());
                annotatedClass.generateFinder().writeTo(mFiler);
            } catch (IOException e) {
                error("Generate file failed, reason: %s", e.getMessage());
                return true;
            }
        }

        return true;
    }

    private void processBindView(RoundEnvironment roundEnv) throws IllegalArgumentException {
        for (Element element : roundEnv.getElementsAnnotatedWith(BindView.class)) {
            AnnotatedClass annotatedClass = generateAnnotatedClass(element);
            BindViewField field = new BindViewField(element);
            annotatedClass.addField(field);
        }
    }

    private void processOnClick(RoundEnvironment roundEnv) throws IllegalArgumentException {
        for (Element element : roundEnv.getElementsAnnotatedWith(OnClick.class)) {
            AnnotatedClass annotatedClass = generateAnnotatedClass(element);
            OnClickMethod method = new OnClickMethod(element);
            annotatedClass.addMethod(method);
        }
    }

    private AnnotatedClass generateAnnotatedClass(Element element) {
        TypeElement classElement = (TypeElement) element.getEnclosingElement();
        String fullClassName = classElement.getQualifiedName().toString();

        AnnotatedClass annotatedClass = mAnnotatedClassMap.get(fullClassName);
        if (annotatedClass == null) {
            annotatedClass = new AnnotatedClass(classElement, mElementUtils);
            mAnnotatedClassMap.put(fullClassName, annotatedClass);
        }
        return annotatedClass;
    }

    private void error(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
    }

    private void info(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
    }
}
