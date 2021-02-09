package calculator.model;


import calculator.model_impl.MainModelRealize;
import calculator.view_interfaces.MainView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
            public void addToHistory(String getCurrentExpressionValue,String lastOperation, String getCurrentNumber, String value) {

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
    }

    @Test
    void cleanAllDigit(){
        m.cleanAll();
        assertEquals("",m.getCurrentNumber());

        m.updateCurrentNumber("5");
        m.updateCurrentNumber("3");
        m.cleanAll();
        assertEquals("",m.getCurrentNumber());

        m.updateCurrentExpressionValue("3");
        m.updateCurrentNumber("2");
        m.cleanAll();
        assertEquals("",m.getCurrentNumber());
        assertEquals("",m.getCurrentExpressionValue());

    }

    @Test
    void addiction(){

        //region EQUALLY
        m.calculationDigit("equally");
        assertEquals("",m.getCurrentNumber());
        //endregion EQUALLY

        //region PLUS
        m.calculationDigit("plus");
        assertEquals("",m.getCurrentNumber());
        m.updateCurrentNumber("5");
        m.calculationDigit("plus");
        m.updateCurrentNumber("3");
        m.calculationDigit("plus");
        assertEquals("8",m.getCurrentExpressionValue());


        m.updateCurrentNumber("5");
        m.calculationDigit("plus");
        m.updateCurrentNumber("3");
        m.calculationDigit("equally");
        assertEquals("16",m.getCurrentExpressionValue());
        //endregion PLUS

        //region MINUS
        m.cleanAll();

        m.calculationDigit("minus");
        assertEquals("",m.getCurrentNumber());

        m.updateCurrentNumber("5");
        m.calculationDigit("minus");
        m.updateCurrentNumber("3");
        m.calculationDigit("minus");
        assertEquals("2",m.getCurrentExpressionValue());


        m.updateCurrentNumber("5");
        m.calculationDigit("minus");
        m.updateCurrentNumber("3");
        m.calculationDigit("equally");
        assertEquals("-6",m.getCurrentExpressionValue());
        //endregion MINUS

        //region MULTIPLY
        m.cleanAll();

        m.calculationDigit("multiply");
        assertEquals("",m.getCurrentNumber());

        m.updateCurrentNumber("5");
        m.calculationDigit("multiply");
        m.updateCurrentNumber("3");
        m.calculationDigit("multiply");
        assertEquals("15",m.getCurrentExpressionValue());


        m.updateCurrentNumber("5");
        m.calculationDigit("multiply");
        m.updateCurrentNumber("3");
        m.calculationDigit("equally");
        assertEquals("225",m.getCurrentExpressionValue());
        //endregion MULTIPLY

        //region DIVIDE
        m.cleanAll();

        m.calculationDigit("divide");
        assertEquals("",m.getCurrentNumber());

        m.updateCurrentNumber("15");
        m.calculationDigit("divide");
        m.updateCurrentNumber("3");
        m.calculationDigit("divide");
        assertEquals("5",m.getCurrentExpressionValue());


        m.updateCurrentNumber("5");
        m.calculationDigit("divide");
        m.updateCurrentNumber("3");
        m.calculationDigit("equally");
        assertEquals("0",m.getCurrentExpressionValue());
        //endregion DIVIDE

        //region PERCENT

        m.cleanAll();

        m.calculationDigit("percent");
        assertEquals("",m.getCurrentNumber());

        m.updateCurrentNumber("15");
        m.calculationDigit("percent");
        m.updateCurrentNumber("3");
        m.calculationDigit("equally");
        assertEquals("0",m.getCurrentExpressionValue());

        m.cleanAll();
        m.updateCurrentNumber("5");
        m.calculationDigit("percent");
        m.updateCurrentNumber("3");
        m.calculationDigit("equally");
        assertEquals("2",m.getCurrentExpressionValue());
        //endregion PERCENT

        //region POWER
        m.cleanAll();

        m.calculationDigit("power");
        assertEquals("",m.getCurrentNumber());

        m.updateCurrentNumber("2");
        m.calculationDigit("power");
        m.updateCurrentNumber("1");
        m.calculationDigit("power");
        assertEquals("2",m.getCurrentExpressionValue());

        m.updateCurrentNumber("2");
        m.calculationDigit("power");
        m.updateCurrentNumber("3");
        m.calculationDigit("equally");
        assertEquals("64",m.getCurrentExpressionValue());

        m.cleanAll();
        m.updateCurrentNumber("-2");
        m.calculationDigit("power");
        m.updateCurrentNumber("4");
        m.calculationDigit("equally");
        assertEquals("16",m.getCurrentExpressionValue());

        m.cleanAll();
        m.updateCurrentNumber("-2");
        m.calculationDigit("power");
        m.updateCurrentNumber("3");
        m.calculationDigit("equally");
        assertEquals("-8",m.getCurrentExpressionValue());

        m.cleanAll();
        m.updateCurrentNumber("2");
        m.calculationDigit("power");
        m.updateCurrentNumber("-4");
        m.calculationDigit("equally");
        assertEquals("",m.getCurrentExpressionValue());
        assertEquals("",m.getCurrentNumber());
        //endregion POWER

    }
}