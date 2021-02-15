package calculator.model;

import calculator.view_controller.HistoryEntity;
import javafx.collections.ObservableList;

public interface HistoryModel extends BaseModel {

    ObservableList<HistoryEntity> historyGet();

}
