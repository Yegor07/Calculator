package calculator.view_controller;

import calculator.model.DBModel;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryEntity {
    private DBModel dbModel;

    public HistoryEntity(DBModel dbModel) {
        this.dbModel = dbModel;
    }

    private String currentExpressionValue;
    private String lastOperation;
    private String currentNumber;
    private String value;
    private long time;
    private Date date;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm  zzz");
        return (String.format("%.30s", this.currentExpressionValue) + (this.currentExpressionValue.length() > 30 ? "..." : "")) + " "
                + this.lastOperation + " "
                + (String.format("%.30s", this.currentNumber) + (this.currentNumber.length() > 30 ? "..." : "")) + " = "
                + (String.format("%.30s", value) + (value.length() > 30 ? "..." : "")) + "    time: "
                + this.time + "ms.  "
                + "Date: " + formatForDateNow.format(this.date);
    }


    public String getCurrentExpressionValue() {
        return currentExpressionValue;
    }

    public void setCurrentExpressionValue(String currentExpressionValue) {
        this.currentExpressionValue = currentExpressionValue;
    }

    public String getLastOperation() {
        return lastOperation;
    }

    public void setLastOperation(String lastOperation) {
        this.lastOperation = lastOperation;
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(String currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void deleteEntity() {
        dbModel.deleteRecord(this);
    }
}
