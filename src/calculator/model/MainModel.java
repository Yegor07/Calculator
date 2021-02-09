package calculator.model;

public interface MainModel {
    void updateCurrentNumber(String digit);
    String getCurrentNumber();
    String getCurrentExpressionValue();
    void cleanLastDigit();
    void cleanAll();
    void calculationDigit(String operation);
    void negativeValue();

}
