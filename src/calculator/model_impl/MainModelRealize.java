package calculator.model_impl;

import calculator.model.MainModel;
import calculator.view_interfaces.MainView;

public class MainModelRealize implements MainModel {

    private MainView view;

    public void setView(MainView view) {
        this.view = view;
    }

    StringBuilder currentNumber = new StringBuilder();
    String currentExpressionValue = "";
    String lastOperation = "";

    @Override
    public void updateCurrentNumber(String digit) {
        currentNumber.append(digit);
        view.showCurrentNumber(getCurrentNumber());
    }

    @Override
    public void updateCurrentExpressionValue(String digit) {
        currentExpressionValue = digit;
        view.showCurrentExpressionValue(getCurrentExpressionValue());
    }

    @Override
    public void calculationDigit(String operation) {
       /* int a = 0;
        int b = 0;
        int c = 0;
        if (currentExpressionValue.length() != 0) c = Integer.parseInt(getCurrentExpressionValue());
        if (currentNumber.length() != 0) c = Integer.parseInt(getCurrentNumber());


        view.showCurrentOperation(operation);

        if (currentNumber.length() > 0) {
            if (currentExpressionValue.length() != 0) a = Integer.parseInt(getCurrentExpressionValue());
            if (currentNumber.length() != 0) b = Integer.parseInt(getCurrentNumber());
            if (operation.equals("equally")) {
                operation = lastOperation;
            }
            switch (operation) {
                case "plus":
                    c = a + b;
                    break;
                case "multiply":
                    c = a * b;
                    break;
                case "equally":

                    break;
            }
            lastOperation = operation;
        }
        updateCurrentExpressionValue(String.valueOf(c));
        cleanAllDigit();*/

        if (lastOperation.equals("equally")) {
            lastOperation = operation;
        }

        view.showCurrentOperation(operation);
        if (currentNumber.length() > 0) {
            int a = 0;
            int b = 0;
            int c = Integer.parseInt(getCurrentNumber());


            if (currentExpressionValue.length() > 0) {

                a = Integer.parseInt(getCurrentExpressionValue());
                b = Integer.parseInt(getCurrentNumber());


                switch (lastOperation) {
                    case "plus":
                        c = a + b;
                        break;
                    case "multiply":
                        c = a * b;
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
