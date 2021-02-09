package calculator.view_controller;


import calculator.model.MainModel;
import calculator.view_interfaces.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class MainViewController implements MainView {

    private MainModel model;

    public void setModel(MainModel model) {
        this.model = model;
    }

    @FXML
    private Label currentNumberTextView;
    @FXML
    private Label currentExpressionValueTextView;
    @FXML
    private Label currentOperationTextView;
    @FXML
    private CheckBox historyCheck;
    @FXML
    private TextArea historyArea;

    //region ControllerMethods
    @FXML
    private void clickNumberButton(ActionEvent event) {
        Button button = (Button) event.getSource();
        model.updateCurrentNumber(button.getId());
    }

    @FXML
    private void clickOperationButton(ActionEvent event) {
        Button operation = (Button) event.getSource();
        model.calculationDigit(operation.getId());
    }

    @FXML
    private void clickNegativeButton(ActionEvent event) {
        model.negativeValue();
    }

    @FXML
    private void clickCLButton(ActionEvent event) {
        model.cleanLastDigit();
    }

    @FXML
    private void clickCAButton(ActionEvent event) {
        model.cleanAll();
    }

    @FXML
    private void clickHistoryButton(ActionEvent event) {
        if (historyCheck.isSelected()) {
            historyArea.setVisible(true);
        } else {
            historyArea.setVisible(false);
        }
    }
    //endregion ControllerMethods

    //region ViewMethods
    @Override
    public void showCurrentNumber(String number) {
        currentNumberTextView.setText(number);

    }

    @Override
    public void showCurrentExpressionValue(String value) {
        currentExpressionValueTextView.setText(value);
    }

    @Override
    public void showCurrentOperation(String operation) {
        currentOperationTextView.setText(switchOperation(operation));
    }

    @Override
    public void addToHistory(String getCurrentExpressionValue, String lastOperation, String getCurrentNumber, String value) {

       historyArea.setText(historyArea.getText() + getCurrentExpressionValue + " " + switchOperation(lastOperation) + " " + getCurrentNumber + " = " + value + "\n");

    }

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
                operation = "%";
                break;
            case "power":
                operation = "^";
                break;
            case "equally":
                operation = "=";
                break;
            default:
                operation = "";
                break;
        }
        return operation;
    }
    //endregion ViewMethods
}
