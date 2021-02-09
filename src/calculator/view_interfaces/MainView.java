package calculator.view_interfaces;

public interface MainView {
    void showCurrentNumber(String number);
    void showCurrentExpressionValue(String value);
    void showCurrentOperation(String operation);
    void addToHistory(String getCurrentExpressionValue,String lastOperation, String getCurrentNumber, String value);
}
