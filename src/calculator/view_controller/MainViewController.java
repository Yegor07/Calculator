package calculator.view_controller;


import calculator.model.MainModel;
import calculator.view_interfaces.MainView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.util.Map;

import static calculator.core.Constants.*;


public class MainViewController extends BaseViewController<MainModel> implements MainView {

    @FXML
    private Label currentNumberTextView;
    @FXML
    private Label currentExpressionValueTextView;
    @FXML
    private Label currentOperationTextView;
    @FXML
    private Button historyButton;
    @FXML
    private TextArea historyArea;
    @FXML
    private Group groupUI;
    @FXML
    private ImageView loading;
    @FXML
    private ImageView alertGirl;

    private Boolean isUIActive = true;


    @Override
    public void enableUI() {
        isUIActive = true;
        loading.setVisible(false);
    }

    @Override
    public void disableUI() {
        isUIActive = false;
        loading.setVisible(true);
    }

    //region ControllerMethods


    @FXML
    private void clickNumberButton(ActionEvent event) {
        if (isUIActive) {
            Button button = (Button) event.getSource();
            model.updateCurrentNumber(button.getId());
        }
    }

    @FXML
    private void clickOperationButton(ActionEvent event) {
        if (isUIActive) {
            Button operation = (Button) event.getSource();
            model.calculateExpression(operation.getId());
        }
    }

    @Override
    public void disableExecute() {
        model.shutDownExecute();
    }

    @Override
    public int getViewId() {
        return MAIN_VIEW_ID;
    }

    @Override
    public void refresh(Map<String, String> bundle) {
        if (isUIActive) {
            if (bundle != null) {
                String current = bundle.get(CURRENT_EXPRESSION);
                model.setCurrentExpressionValue(current);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LOADING...");
            alert.setContentText("You can't use it!");
            alertGirl.setVisible(true);
            alert.setGraphic(alertGirl);
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }

    @FXML
    private void clickNegativeButton(ActionEvent event) {
        if (isUIActive) {
            model.negateValue();
        }
    }

    @FXML
    private void clickCLButton(ActionEvent event) {
        if (isUIActive) {
            model.cleanLastDigit();
        }
    }

    @FXML
    private void clickCAButton(ActionEvent event) {
        if (isUIActive) {
            model.cleanAll();
        }
    }

    @FXML
    private void clickHistoryButton(ActionEvent event) {
        viewManager.changeView(HISTORY_VIEW_ID);
    }
    //endregion ControllerMethods

    //region ViewMethods
    @Override
    public void showCurrentNumber(String number) {
        Platform.runLater(() -> currentNumberTextView.setText(number));

    }

    @Override
    public void showCurrentExpressionValue(String value) {
        Platform.runLater(() -> currentExpressionValueTextView.setText(value));
    }

    @Override
    public void showCurrentOperation(String operation) {
        Platform.runLater(() -> currentOperationTextView.setText(switchOperation(operation)));
    }


    @Override
    public String switchOperation(String operation) {
        switch (operation) {
            case "plus":
                operation = "+";
                break;
            case "minus":
                operation = "-";
                break;
            case "divide":
                operation = "/";
                break;
            case "multiply":
                operation = "*";
                break;
            case "percent":
                operation = "÷";
                break;
            case "power":
                operation = "^";
                break;
            case "equally":
                operation = "=";
                break;
            case "tetration":
                operation = "tet";
                break;
            default:
                operation = "";
                break;
        }
        return operation;
    }
    //endregion ViewMethods
}
