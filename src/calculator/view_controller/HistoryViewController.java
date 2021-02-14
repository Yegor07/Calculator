package calculator.view_controller;

import calculator.RecordListViewCell;
import calculator.model.HistoryModel;
import calculator.view_interfaces.HistoryView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import static calculator.core.ViewConstants.HISTORY_VIEW_ID;
import static calculator.core.ViewConstants.MAIN_VIEW_ID;


public class HistoryViewController extends BaseViewController<HistoryModel> implements HistoryView {

    @FXML
    private ListView<RecordView> listView;

    @Override
    public void refresh() {
        listView.setItems(model.historyGet());

        listView.setCellFactory(x -> new RecordListViewCell());
    }

    @Override
    public int getViewId(){
        return HISTORY_VIEW_ID;
    }

    @FXML
    public void clickHistoryButton(ActionEvent event) {
        viewManager.changeView(MAIN_VIEW_ID);
    }


}
