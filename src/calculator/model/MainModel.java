package calculator.model;

public interface MainModel {
    void updateCurrentNumber(String digit);
    void updateCurrentExpressionValue(String digit);
    String getCurrentNumber();
    String getCurrentExpressionValue();
    void cleanLastDigit();
    void cleanAllDigit();
    void calculationDigit(String operation);

}
