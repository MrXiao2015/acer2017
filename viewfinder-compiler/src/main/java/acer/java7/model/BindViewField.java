package acer.java7.model;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import acer.java7.BindView;

/**
 * Created by xiaozheng on 2017/2/21.
 */

public class BindViewField {
    private VariableElement mFieldElement;
    private int mResId;

    public BindViewField(Element element) throws IllegalArgumentException{
        if(element.getKind() != ElementKind.FIELD){
            throw new IllegalArgumentException(
                    String.format("Only fields can be annotated with @%s", BindView.class.getSimpleName())
            );
        }

        mFieldElement = (VariableElement) element;
        BindView bindView = mFieldElement.getAnnotation(BindView.class);
        mResId = bindView.value();
    }

    public int getResId() {
        return mResId;
    }

    public Name getFeildName(){
        return mFieldElement.getSimpleName();
    }

    public TypeMirror getFieldType(){
        return mFieldElement.asType();
    }


}
