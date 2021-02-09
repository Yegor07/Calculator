package calculator;

import calculator.view_controller.MainViewController;
import calculator.model_impl.MainModelRealize;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;

    private Parent mainView, historyView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        initMVC();
        initStage();
    }

    private void initMVC() throws IOException {
        this.mainView = loadView("CalculatorLayout.fxml", new MainModelRealize());
        //this.historyView = ...
    }

    private Parent loadView(String name, MainModelRealize model) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
        Parent view = loader.load();
        MainViewController viewController = loader.getController();

        model.setView(viewController);
        viewController.setModel(model);
        return view;
    }

    private void initStage() {
        stage.setTitle("Calculator");
        stage.setScene(new Scene(mainView, 425, 380));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
