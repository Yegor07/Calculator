package calculator;


import calculator.core.ViewManager;
import calculator.view_controller.HistoryEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.HashMap;

import static calculator.core.Constants.CURRENT_EXPRESSION;
import static calculator.core.Constants.MAIN_VIEW_ID;


public class RecordListViewCell extends ListCell<HistoryEntity> {

    @FXML
    private Button delete;
    @FXML
    private Button copy;
    @FXML
    private Button use;
    @FXML
    private HBox hBox;
    @FXML
    private Label textExpression;


    private FXMLLoader mLLoader;

    private ViewManager viewManager;

    public RecordListViewCell(ViewManager vm) {
        viewManager = vm;
    }

    @Override
    protected void updateItem(HistoryEntity record, boolean empty) {
        super.updateItem(record, empty);

        if (empty || record == null) {

            setText(null);
            setGraphic(null);

        } else {

            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("layouts/RecordLayout.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            delete.setOnAction(e -> {
                record.deleteEntity();
            });
            copy.setOnAction(e -> {
                StringSelection stringSelection = new StringSelection(record.getValue());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            });
            use.setOnAction(e -> {
                HashMap<String, String> bundle = new HashMap<>();
                bundle.put(CURRENT_EXPRESSION, record.getValue());
                viewManager.changeView(MAIN_VIEW_ID, bundle);
            });
            textExpression.setText(record.toString());
            setText(null);
            setGraphic(hBox);


        }

    }
}
