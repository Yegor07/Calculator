package calculator.view_controller;

import calculator.model.MenuModel;
import calculator.view_interfaces.MenuView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


import static calculator.core.ViewConstants.*;

public class MenuViewController extends BaseViewController<MenuModel> implements MenuView {

    @Override
    public int getViewId() {
        return MENU_VIEW_ID;
    }

    @FXML
    private Button historyMenuButton;

    @FXML
    private Button historyCalculatorButton;

    @FXML
    private void clickHistoryMenuButton(ActionEvent event) {
        viewManager.changeView(HISTORY_VIEW_ID);
    }

    @FXML
    private void clickCalculatorMenuButton(ActionEvent event) {
        viewManager.changeView(MAIN_VIEW_ID);
    }
}
