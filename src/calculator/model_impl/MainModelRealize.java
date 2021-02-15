package calculator.model_impl;

import calculator.DataBase.DBProvider;
import calculator.model.MainModel;
import calculator.view_controller.HistoryEntity;
import calculator.view_interfaces.MainView;

import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainModelRealize extends BaseModelRealize<MainView> implements MainModel {

    private ExecutorService executor;
    private DBProvider dbProvider;


    public MainModelRealize(DBProvider db) {

        executor = Executors.newSingleThreadExecutor();

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

    private void compute(String operation){
        startTime = System.currentTimeMillis();
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
                        c = binPow(a, b);
                        // c = a.pow(b.intValue());
                    } else {
                   //     cleanAll();//TODO Handle Exception
                        return;
                    }
                    break;
                case "tetration":
                    if (currentNumber.charAt(0) != '-') {
                        if (b.compareTo(BigInteger.ZERO) == 0) {
                            c = BigInteger.ONE;
                            break;
                        }
                        c = new BigInteger(a.toString());

                        while (b.compareTo(BigInteger.ONE) != 0) {

                            c = binPow(a, c);

                            b = b.subtract(BigInteger.ONE);
                        }
                    } else {
                      //  cleanAll(); //TODO Handle Exception
                        return;
                    }


                    break;

            }
            endTime = System.currentTimeMillis();

            HistoryEntity record = new HistoryEntity(dbProvider);
            record.setDate(new Date());

            record.setTime(endTime - startTime);
            record.setCurrentExpressionValue(getCurrentExpressionValue());
            record.setLastOperation(view.switchOperation(lastOperation));
            record.setCurrentNumber(getCurrentNumber());
            record.setValue(c.toString());

            dbProvider.addRecord(record);


            lastOperation = operation;
            updateCurrentExpressionValue(c);


            if (currentNumber.length() > 0) {
                currentNumber.setLength(0);
                view.showCurrentNumber(getCurrentNumber());
            }
        }
        view.enableUI();
    }

    @Override
    public void calculateExpression(String operation) {

        view.disableUI();
        executor.execute(()->compute(operation));

    }

    @Override
    public void shutDownExecute(){
        executor.shutdownNow();
        try {
            if (!executor.awaitTermination(100, TimeUnit.MICROSECONDS)) {
                System.exit(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private BigInteger binPow(BigInteger a, BigInteger b) {
        BigInteger c = BigInteger.ONE;
        while (b.compareTo(BigInteger.ZERO) != 0) {
            if (b.and(BigInteger.ONE).compareTo(BigInteger.ONE) == 0) {
                c = c.multiply(a);
            }
            a = a.multiply(a);
            b = b.shiftRight(1);
        }
        return c;
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
    public void setCurrentExpressionValue(String current) {
        this.currentExpressionValue = new BigInteger(current);
        view.showCurrentExpressionValue(current);
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
