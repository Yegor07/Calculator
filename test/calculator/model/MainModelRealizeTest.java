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
        m.cleanAllDigit();
        assertEquals("",m.getCurrentNumber());

        m.updateCurrentNumber("5");
        m.updateCurrentNumber("3");
        m.cleanAllDigit();
        assertEquals("",m.getCurrentNumber());
    }

    @Test
    void calculationDigit(){
        //TODO
        m.calculationDigit("plus");
        assertEquals("",m.getCurrentNumber());

        m.updateCurrentNumber("5");
        m.updateCurrentExpressionValue("3");
        m.calculationDigit("plus");
        assertEquals("8",m.getCurrentExpressionValue());


    }
}