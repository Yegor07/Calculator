package calculator.view_controller;

import calculator.RecordListViewCell;
import calculator.model.HistoryModel;
import calculator.view_interfaces.HistoryView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.Map;

import static calculator.core.Constants.HISTORY_VIEW_ID;
import static calculator.core.Constants.MAIN_VIEW_ID;


public class HistoryViewController extends BaseViewController<HistoryModel> implements HistoryView {

    @FXML
    private ListView<HistoryEntity> listView;

    @Override
    public void refresh(Map<String, String> bundle) {
        listView.setItems(model.historyGet());

        listView.setCellFactory(x -> new RecordListViewCell(viewManager));
    }


    @Override
    public int getViewId() {
        return HISTORY_VIEW_ID;
    }

    @FXML
    public void clickHistoryButton(ActionEvent event) {
        viewManager.changeView(MAIN_VIEW_ID);
    }


}
