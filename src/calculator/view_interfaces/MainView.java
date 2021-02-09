package calculator.view_interfaces;

import java.math.BigInteger;

public interface MainView {
    void showCurrentNumber(String number);

    void showCurrentExpressionValue(String value);

    void showCurrentOperation(String operation);

    void addToHistory(BigInteger getCurrentExpressionValue, String lastOperation, String getCurrentNumber, String value);
}
