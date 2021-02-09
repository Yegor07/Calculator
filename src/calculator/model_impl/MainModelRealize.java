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
    //  private String currentExpressionValue = "";  //TODO make it BigInteger
    private BigInteger currentExpressionValue = new BigInteger("0");
    private String lastOperation = "plus";

    @Override
    public void updateCurrentNumber(String digit) {
        currentNumber.append(digit);
        view.showCurrentNumber(getCurrentNumber());
    }

    public void updateCurrentExpressionValue(BigInteger value) {
        currentExpressionValue = value;
        view.showCurrentExpressionValue(getCurrentExpressionValue().toString());
    }

    @Override
    public void negateValue() {
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
    public void calculateDigit(String operation) {

        if (lastOperation.equals("equally")) {
            lastOperation = operation;
        }

        view.showCurrentOperation(operation);
        if (currentNumber.length() > 0) {
            BigInteger a, b, c;
            c = new BigInteger(getCurrentNumber());

            a = getCurrentExpressionValue();
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
            view.addToHistory(getCurrentExpressionValue(), lastOperation, getCurrentNumber(), c.toString());


            lastOperation = operation;
            updateCurrentExpressionValue(c);


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
    public BigInteger getCurrentExpressionValue() {
        return currentExpressionValue;
    }

    @Override
    public void cleanLastDigit() {
        if (currentNumber.length() > 0) {
            currentNumber.setLength(currentNumber.length() - 1);
            if (currentNumber.toString().equals("-")) {
                currentNumber.setLength(0);
            }
            view.showCurrentNumber(getCurrentNumber());
        }
    }

    @Override
    public void cleanAll() {
        currentNumber.setLength(0);
        currentExpressionValue = BigInteger.valueOf(0);
        lastOperation = "plus";
        view.showCurrentExpressionValue("");
        view.showCurrentNumber(getCurrentNumber());
        view.showCurrentOperation("");
    }


}
