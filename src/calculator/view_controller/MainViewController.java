package calculator.view_controller;


import calculator.model.MainModel;
import calculator.view_interfaces.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


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

    //region ControllerMethods
    @FXML
    private void clickNumberButton(ActionEvent event) {
        //Controller
        Button button = (Button) event.getSource();
        model.updateCurrentNumber(button.getId());

        //View
        //currentNumberTextView.setText(model.getCurrentNumber());
    }

    @FXML
    private void clickOperationButton(ActionEvent event) {
        Button operation = (Button) event.getSource();
        model.calculationDigit(operation.getId());

    }

    @FXML
    private  void clickCLButton(ActionEvent event){
        model.cleanLastDigit();
    }

    @FXML
    private void clickCAButton(ActionEvent event){
        model.cleanAllDigit();
    }
    //endregion ControllerMethods

    //region ViewMethods
    @Override
    public void showCurrentNumber(String number) {
        currentNumberTextView.setText(number);

    }

    @Override
    public void showCurrentExpressionValue(String value) {
        //TODO implement me
        currentExpressionValueTextView.setText(value);
    }

    @Override
    public void showCurrentOperation(String operation){
        switch (operation)
        {
            case "plus":
                operation="+";
                break;
            case "minus":
                operation="-";
                break;
            case "divide":
                operation="/";
                break;
            case "multiply":
                operation="*";
                break;
            case "equally":
                operation="=";
                break;
        }
        currentOperationTextView.setText(operation);
    }

    //endregion ViewMethods
}
