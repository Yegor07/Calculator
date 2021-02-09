package calculator.model;

import java.math.BigInteger;

public interface MainModel {
    void updateCurrentNumber(String digit);
    String getCurrentNumber();
    BigInteger getCurrentExpressionValue();
    void cleanLastDigit();
    void cleanAll();
    void calculateDigit(String operation);
    void negateValue();

}
