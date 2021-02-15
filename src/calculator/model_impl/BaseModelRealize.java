package calculator.model_impl;

import calculator.model.BaseModel;
import calculator.view_interfaces.BaseView;


public abstract class BaseModelRealize<V extends BaseView> implements BaseModel {

    protected V view;

    public void setView(V view) {
        this.view = view;
    }
}
