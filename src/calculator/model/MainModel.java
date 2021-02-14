package calculator.model;

public interface MainModel extends BaseModel{
    void updateCurrentNumber(String digit);
    String getCurrentNumber();
    String getCurrentExpressionValue();
    void cleanLastDigit();
    void cleanAll();
    void calculateExpression(String operation);
    void negateValue();

}
