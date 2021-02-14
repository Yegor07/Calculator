package calculator.model_impl;

import calculator.DataBase.DBProvider;
import calculator.model.HistoryModel;
import calculator.view_interfaces.HistoryView;
import calculator.view_controller.RecordView;
import javafx.collections.ObservableList;


public class HistoryModelRealize extends BaseModelRealize<HistoryView> implements HistoryModel {

    private DBProvider dbProvider;

    public HistoryModelRealize(DBProvider db) {
        dbProvider = db;
    }





    public ObservableList<RecordView> historyGet(){
       return dbProvider.getRecord();
    }


}
