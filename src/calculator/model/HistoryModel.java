package calculator.model;

import calculator.view_controller.RecordView;
import javafx.collections.ObservableList;

public interface HistoryModel extends BaseModel {

    ObservableList<RecordView> historyGet();

}
