package calculator.model;


import calculator.DataBase.DBProvider;
import calculator.view_controller.RecordView;
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
        m = new MainModelRealize(new DBProvider());
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
            public RecordView addToHistory(RecordView record) {
                return record;
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
        assertEquals("", m.getCurrentNumber());
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
        assertEquals("0", m.getCurrentExpressionValue());
    }

    @Test
    void equally() {
        m.calculateExpression("equally");
        assertEquals("", m.getCurrentNumber());
    }

    @Test
    void subtraction() {
        m.cleanAll();

        m.calculateExpression("minus");
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentNumber("5");
        m.calculateExpression("minus");
        m.updateCurrentNumber("3");
        m.calculateExpression("minus");
        assertEquals("2", m.getCurrentExpressionValue());


        m.updateCurrentNumber("5");
        m.calculateExpression("minus");
        m.updateCurrentNumber("3");
        m.calculateExpression("equally");
        assertEquals("-6", m.getCurrentExpressionValue());
    }

    @Test
    void multiplication() {
        m.cleanAll();

        m.calculateExpression("multiply");
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentNumber("5");
        m.calculateExpression("multiply");
        m.updateCurrentNumber("3");
        m.calculateExpression("multiply");
        assertEquals("15", m.getCurrentExpressionValue());


        m.updateCurrentNumber("5");
        m.calculateExpression("multiply");
        m.updateCurrentNumber("3");
        m.calculateExpression("equally");
        assertEquals("225", m.getCurrentExpressionValue());

    }

    @Test
    void division() {

        m.cleanAll();

        m.calculateExpression("divide");
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentNumber("15");
        m.calculateExpression("divide");
        m.updateCurrentNumber("3");
        m.calculateExpression("divide");
        assertEquals("5", m.getCurrentExpressionValue());


        m.updateCurrentNumber("5");
        m.calculateExpression("divide");
        m.updateCurrentNumber("3");
        m.calculateExpression("equally");
        assertEquals("0", m.getCurrentExpressionValue());

    }

    @Test
    void exponentiation() {

        m.cleanAll();

        m.calculateExpression("power");
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentNumber("2");
        m.calculateExpression("power");
        m.updateCurrentNumber("1");
        m.calculateExpression("power");
        assertEquals("2", m.getCurrentExpressionValue());

        m.updateCurrentNumber("2");
        m.calculateExpression("power");
        m.updateCurrentNumber("3");
        m.calculateExpression("equally");
        assertEquals("64", m.getCurrentExpressionValue());

        m.cleanAll();
        m.updateCurrentNumber("-2");
        m.calculateExpression("power");
        m.updateCurrentNumber("4");
        m.calculateExpression("equally");
        assertEquals("16", m.getCurrentExpressionValue());

        m.cleanAll();
        m.updateCurrentNumber("-2");
        m.calculateExpression("power");
        m.updateCurrentNumber("3");
        m.calculateExpression("equally");
        assertEquals("-8", m.getCurrentExpressionValue());

        m.cleanAll();
        m.updateCurrentNumber("2");
        m.calculateExpression("power");
        m.updateCurrentNumber("-4");
        m.calculateExpression("equally");
        assertEquals("0", m.getCurrentExpressionValue());
        assertEquals("", m.getCurrentNumber());

    }

    @Test
    void divisionRemainder() {


        m.cleanAll();

        m.calculateExpression("percent");
        assertEquals("", m.getCurrentNumber());

        m.updateCurrentNumber("15");
        m.calculateExpression("percent");
        m.updateCurrentNumber("3");
        m.calculateExpression("equally");
        assertEquals("0", m.getCurrentExpressionValue());

        m.cleanAll();
        m.updateCurrentNumber("5");
        m.calculateExpression("percent");
        m.updateCurrentNumber("3");
        m.calculateExpression("equally");
        assertEquals("2", m.getCurrentExpressionValue());

    }

    @Test
    void addiction() {

        m.calculateExpression("plus");
        assertEquals("", m.getCurrentNumber());
        m.updateCurrentNumber("5");
        m.calculateExpression("plus");
        m.updateCurrentNumber("3");
        m.calculateExpression("plus");
        assertEquals("8", m.getCurrentExpressionValue());


        m.updateCurrentNumber("5");
        m.calculateExpression("plus");
        m.updateCurrentNumber("3");
        m.calculateExpression("equally");
        assertEquals("16", m.getCurrentExpressionValue());

    }
}