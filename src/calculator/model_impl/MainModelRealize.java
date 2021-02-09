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
    private StringBuilder history = new StringBuilder();
    private String currentExpressionValue = "";  //TODO make it BigInteger
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
    public void negativeValue() {
        if (currentNumber.length() > 0) {
            if (currentNumber.charAt(0) != '-') {
                currentNumber.insert(0, "-");
            } else {
                currentNumber.deleteCharAt(0);
            }
            view.showCurrentNumber(getCurrentNumber());
        }
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
                    case "divide":
                        c = a.divide(b);
                        break;
                    case "minus":
                        c = a.subtract(b);
                        break;
                    case "percent":
                        c = a.remainder(b);
                        break;
                    case "power":
                        if (currentNumber.charAt(0) != '-') {
                            c = a.pow(b.intValue());
                        } else {
                            cleanAll();
                            return;
                        }
                        break;
                }
                view.addToHistory(getCurrentExpressionValue(), lastOperation, getCurrentNumber(), String.valueOf(c));
            }

            lastOperation = operation;
            updateCurrentExpressionValue(String.valueOf(c));


            if (currentNumber.length() > 0) {
                currentNumber.setLength(0);
                view.showCurrentNumber(getCurrentNumber());
            }
        }

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
    public void cleanAll() {
        currentNumber.setLength(0);
        currentExpressionValue = "";
        view.showCurrentExpressionValue(getCurrentExpressionValue());
        view.showCurrentNumber(getCurrentNumber());
        view.showCurrentOperation("");
    }


}
