package calculator.view_controller;

import calculator.core.ViewManager;
import calculator.model.BaseModel;
import calculator.view_interfaces.BaseView;

import java.util.Map;

public abstract class BaseViewController<M extends BaseModel> implements BaseView {
    protected ViewManager viewManager;
    protected M model;

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public void setModel(M model) {
        this.model = model;
    }

    public abstract int getViewId();

    public void disableExecute() {

    }

    public void refresh(Map<String, String> bundle) {

    }


}
