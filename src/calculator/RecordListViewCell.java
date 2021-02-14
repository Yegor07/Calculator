package calculator;



import calculator.DataBase.DBProvider;
import calculator.view_controller.RecordView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;


public class RecordListViewCell extends ListCell<RecordView> {

    @FXML
    private Button delete;
    @FXML
    private HBox hBox;
    @FXML
    private Label textExpression;



    private FXMLLoader mLLoader;


    @Override
    protected void updateItem(RecordView record, boolean empty) {
        super.updateItem(record, empty);

        if(empty || record == null) {

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
            delete.setOnAction(e->{
                //TODO Can I optimize it?
               record.dbProvider.deleteRecord(record);
            });
            textExpression.setText(record.CurrentExpressionValue+" "+record.lastOperation+" "+record.getCurrentNumber+" = "+record.value+"    time: "+record.time+"ms.  ");
            setText(null);
            setGraphic(hBox);


        }

    }
}
