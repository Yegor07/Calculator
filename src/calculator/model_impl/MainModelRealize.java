package calculator.model_impl;

import calculator.model.MainModel;
import calculator.view_interfaces.MainView;

import java.math.BigInteger;

public class MainModelRealize implements MainModel {

    private MainView view;

    public void setView(MainView view) {
        this.view = view;
    }

    private StringBuilder currentNumber = new StringBuilder();
    private String currentExpressionValue = "";
    private String lastOperation = "";

    @Override
    public void updateCurrentNumber(String digit) {
        currentNumber.append(digit);
        view.showCurrentNumber(getCurrentNumber());
    }

    public void updateCurrentExpressionValue(String value) {
        currentExpressionValue = value;
        view.showCurrentExpressionValue(getCurrentExpressionValue());
    }

    @Override
    public void calculationDigit(String operation) {

        if (lastOperation.equals("equally")) {
            lastOperation = operation;
        }

        view.showCurrentOperation(operation);
        if (currentNumber.length() > 0) {
            BigInteger a, b, c;
            c = new BigInteger(getCurrentNumber());

            if (currentExpressionValue.length() > 0) {

                a = new BigInteger(getCurrentExpressionValue());
                b = new BigInteger(getCurrentNumber());


                switch (lastOperation) {
                    case "plus":
                        c = a.add(b);
                        break;
                    case "multiply":
                        c = a.multiply(b);
                        break;
                    case "equally":

                        break;
                }

            }

            lastOperation = operation;
            updateCurrentExpressionValue(String.valueOf(c));

            cleanAllDigit();
        }
        System.out.println();
    }


    @Override
    public String getCurrentNumber() {
        return currentNumber.toString();
    }

    @Override
    public String getCurrentExpressionValue() {
        return currentExpressionValue.toString();
    }

    @Override
    public void cleanLastDigit() {
        if (currentNumber.length() > 0) {
            currentNumber.setLength(currentNumber.length() - 1);
            view.showCurrentNumber(getCurrentNumber());
        }
    }

    @Override
    public void cleanAllDigit() {
        if (currentNumber.length() > 0) {
            currentNumber.setLength(0);
            view.showCurrentNumber(getCurrentNumber());
        }
    }
}
