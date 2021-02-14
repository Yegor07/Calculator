package calculator.core;

import calculator.DataBase.DBProvider;
import calculator.model_impl.BaseModelRealize;
import calculator.model_impl.HistoryModelRealize;
import calculator.model_impl.MainModelRealize;
import calculator.model_impl.MenuModelRealize;
import calculator.view_controller.BaseViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static calculator.core.ViewConstants.*;

public class Main extends Application implements ViewManager {

    private Stage stage;

    private final Parent[] views= new Parent[3];
    private final BaseViewController[] viewControllers = new BaseViewController[3];



    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        initMVC();
        initStage();
    }

    private void initMVC() throws IOException {
        DBProvider DB = new DBProvider();
        this.views[MAIN_VIEW_ID] = InitView("../layouts/MainLayout.fxml", new MainModelRealize(DB));
        this.views[HISTORY_VIEW_ID] = InitView("../layouts/HistoryLayout.fxml", new HistoryModelRealize(DB));
        this.views[MENU_VIEW_ID] = InitView("../layouts/MenuLayout.fxml", new MenuModelRealize());
    }

    private <M extends BaseModelRealize> Parent InitView(String name, M model) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
        Parent view = loader.load();
        BaseViewController<M> vc = loader.getController();
        viewControllers[vc.getViewId()]=vc;

        model.setView(vc);
        vc.setModel(model);
        vc.setViewManager(this);
        return view;
    }


    private void initStage() {
        stage.setTitle("Calculator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../calculator-icon.png")));
        stage.setScene(new Scene(views[MENU_VIEW_ID], 425, 380));
        stage.show();

    }


    public void changeView(int value) {
        Platform.runLater(() -> stage.getScene().setRoot(views[value]));
        viewControllers[value].refresh();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
