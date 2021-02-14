package calculator.DataBase;

import calculator.RecordListViewCell;
import calculator.view_controller.RecordView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBProvider {
    private ObservableList<RecordView> record =  FXCollections.observableArrayList();

    public void addRecord(RecordView record){
        this.record.add(record);

    }

    public ObservableList<RecordView> getRecord(){
        return this.record;
    }

    public void deleteRecord(RecordView record){
       int index = this.record.indexOf(record);
       this.record.remove(index);

    }
}
