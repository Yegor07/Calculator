package calculator.model;


import calculator.model_impl.MainModelRealize;
import calculator.view_interfaces.MainView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainModelRealizeTest {

    MainModelRealize m;

    @BeforeEach
    void initTest() {
        m = new MainModelRealize();
        m.setView(new MainView() {
            @Override
            public void showCurrentNumber(String number) {
            }

            @Override
            public void showCurrentExpressionValue(String value) {
            }

            @Override
            public void showCurrentOperation(String operation) {

            }

            @Override
            public void addToHistory(BigInteger getCurrentExpressionValue, String lastOperation, String getCurrentNumber, String value) {

            }


        });
    }

    @Test
    void checkInitNumberValue() {
        assertEquals("", m.getCurrentNumber());
    }

    @Test
    void updateCurrentNumber() {
        m.updateCurrentNumber("1");
        m.updateCurrentNumber("1");
        assertEquals("11", m.getCurrentNumber());
    }

    @Test
    void cleanLastDigit() {
        m.cleanLastDigit();
        assertEquals("", m.getCurrentNumber());
        m.updateCurrentNumber("1");
        m.updateCurrentNumber("4");
        m.cleanLastDigit();
        assertEquals("1", m.getCurrentNumber());

        m.cleanAll();

        m.updateCurrentNumber("-3");
        m.cleanLastDigit();
        assertEquals("",m.getCurrentNumber());
    }

    @Test
    void cleanAllDigit() {
        m.cleanAll();
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentNumber("5");
        m.updateCurrentNumber("3");
        m.cleanAll();
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentExpressionValue(BigInteger.valueOf(3));
        m.updateCurrentNumber("2");
        m.cleanAll();
        assertEquals("", m.getCurrentNumber());
        assertEquals("0", m.getCurrentExpressionValue().toString());
    }

    @Test
    void equally() {
        //region EQUALLY
        m.calculateDigit("equally");
        assertEquals("", m.getCurrentNumber());
        //endregion EQUALLY
    }

    @Test
    void subtraction() {
        //region MINUS
        m.cleanAll();

        m.calculateDigit("minus");
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentNumber("5");
        m.calculateDigit("minus");
        m.updateCurrentNumber("3");
        m.calculateDigit("minus");
        assertEquals("2", m.getCurrentExpressionValue().toString());


        m.updateCurrentNumber("5");
        m.calculateDigit("minus");
        m.updateCurrentNumber("3");
        m.calculateDigit("equally");
        assertEquals("-6", m.getCurrentExpressionValue().toString());
        //endregion MINUS
    }

    @Test
    void multiplication() {
        //region MULTIPLY
        m.cleanAll();

        m.calculateDigit("multiply");
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentNumber("5");
        m.calculateDigit("multiply");
        m.updateCurrentNumber("3");
        m.calculateDigit("multiply");
        assertEquals("15", m.getCurrentExpressionValue().toString());


        m.updateCurrentNumber("5");
        m.calculateDigit("multiply");
        m.updateCurrentNumber("3");
        m.calculateDigit("equally");
        assertEquals("225", m.getCurrentExpressionValue().toString());
        //endregion MULTIPLY
    }

    @Test
    void division() {
        //region DIVIDE
        m.cleanAll();

        m.calculateDigit("divide");
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentNumber("15");
        m.calculateDigit("divide");
        m.updateCurrentNumber("3");
        m.calculateDigit("divide");
        assertEquals("5", m.getCurrentExpressionValue().toString());


        m.updateCurrentNumber("5");
        m.calculateDigit("divide");
        m.updateCurrentNumber("3");
        m.calculateDigit("equally");
        assertEquals("0", m.getCurrentExpressionValue().toString());
        //endregion DIVIDE
    }

    @Test
    void exponentiation() {
        //region POWER
        m.cleanAll();

        m.calculateDigit("power");
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentNumber("2");
        m.calculateDigit("power");
        m.updateCurrentNumber("1");
        m.calculateDigit("power");
        assertEquals("2", m.getCurrentExpressionValue().toString());

        m.updateCurrentNumber("2");
        m.calculateDigit("power");
        m.updateCurrentNumber("3");
        m.calculateDigit("equally");
        assertEquals("64", m.getCurrentExpressionValue().toString());

        m.cleanAll();
        m.updateCurrentNumber("-2");
        m.calculateDigit("power");
        m.updateCurrentNumber("4");
        m.calculateDigit("equally");
        assertEquals("16", m.getCurrentExpressionValue().toString());

        m.cleanAll();
        m.updateCurrentNumber("-2");
        m.calculateDigit("power");
        m.updateCurrentNumber("3");
        m.calculateDigit("equally");
        assertEquals("-8", m.getCurrentExpressionValue().toString());

        m.cleanAll();
        m.updateCurrentNumber("2");
        m.calculateDigit("power");
        m.updateCurrentNumber("-4");
        m.calculateDigit("equally");
        assertEquals("0", m.getCurrentExpressionValue().toString());
        assertEquals("", m.getCurrentNumber());
        //endregion POWER
    }

    @Test
    void divisionRemainder() {
        //region PERCENT

        m.cleanAll();

        m.calculateDigit("percent");
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentNumber("15");
        m.calculateDigit("percent");
        m.updateCurrentNumber("3");
        m.calculateDigit("equally");
        assertEquals("0", m.getCurrentExpressionValue().toString());

        m.cleanAll();
        m.updateCurrentNumber("5");
        m.calculateDigit("percent");
        m.updateCurrentNumber("3");
        m.calculateDigit("equally");
        assertEquals("2", m.getCurrentExpressionValue().toString());
        //endregion PERCENT
    }

    @Test
    void addiction() {
        //region PLUS
        m.calculateDigit("plus");
        assertEquals("", m.getCurrentNumber());
        m.updateCurrentNumber("5");
        m.calculateDigit("plus");
        m.updateCurrentNumber("3");
        m.calculateDigit("plus");
        assertEquals("8", m.getCurrentExpressionValue().toString());


        m.updateCurrentNumber("5");
        m.calculateDigit("plus");
        m.updateCurrentNumber("3");
        m.calculateDigit("equally");
        assertEquals("16", m.getCurrentExpressionValue().toString());
        //endregion PLUS
    }
}