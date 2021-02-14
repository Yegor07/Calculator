package calculator.view_interfaces;

import calculator.view_controller.RecordView;

public interface MainView extends BaseView {
    void showCurrentNumber(String number);

    void showCurrentExpressionValue(String value);

    void showCurrentOperation(String operation);

    RecordView addToHistory(RecordView record);
}
