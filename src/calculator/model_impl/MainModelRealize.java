package calculator.model_impl;

import calculator.DataBase.DBProvider;
import calculator.model.MainModel;
import calculator.view_controller.RecordView;
import calculator.view_interfaces.MainView;

import java.math.BigInteger;

public class MainModelRealize extends BaseModelRealize<MainView> implements MainModel {


    private DBProvider dbProvider;

    public MainModelRealize(DBProvider db) {
        dbProvider = db;
    }


    private StringBuilder currentNumber = new StringBuilder();
    private BigInteger currentExpressionValue = new BigInteger("0");
    private String lastOperation = "plus";
    private long startTime;
    private long endTime;

    @Override
    public void updateCurrentNumber(String digit) {
        currentNumber.append(digit);
        view.showCurrentNumber(getCurrentNumber());
    }

    public void updateCurrentExpressionValue(BigInteger value) {
        currentExpressionValue = value;
        view.showCurrentExpressionValue(getCurrentExpressionValue());
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
    public void calculateExpression(String operation) {
        startTime= System.currentTimeMillis();
        if (lastOperation.equals("equally")) {
            lastOperation = operation;
        }

        view.showCurrentOperation(operation);
        if (currentNumber.length() > 0) {
            BigInteger a, b, c;
            c = new BigInteger(getCurrentNumber());

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
            endTime=System.currentTimeMillis();

            RecordView record = new RecordView(dbProvider);

            record.time = endTime-startTime;
            record.CurrentExpressionValue = String.format("%.30s", getCurrentExpressionValue()) + (getCurrentExpressionValue().length() > 30 ? "..." : "");
            record.lastOperation = lastOperation;
            record.getCurrentNumber = String.format("%.30s", getCurrentNumber()) + (getCurrentNumber().length() > 30 ? "..." : "");
            record.value = String.format("%.30s", c.toString()) + (c.toString().length() > 30 ? "..." : "");

            dbProvider.addRecord(view.addToHistory(record));

            lastOperation = operation;
            updateCurrentExpressionValue(c);


            if (currentNumber.length() > 0) {
                currentNumber.setLength(0);
                view.showCurrentNumber(getCurrentNumber());
            }
        }

       // System.out.println("time: "+(endTime-startTime)+"ms");

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
