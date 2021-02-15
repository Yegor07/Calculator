package calculator.view_interfaces;

public interface MainView extends BaseView {
    void showCurrentNumber(String number);

    void showCurrentExpressionValue(String value);

    void showCurrentOperation(String operation);

    String switchOperation(String operation);

    void enableUI();

    void disableUI();

    void disableExecute();
}
