package calculator.view_controller;

import calculator.DataBase.DBProvider;

public class RecordView{
    public DBProvider dbProvider;

    public RecordView(DBProvider dbProvider) {
        this.dbProvider=dbProvider;
    }

    public String CurrentExpressionValue;
    public String lastOperation;
    public String getCurrentNumber;
    public String value;
    public long time;
}
