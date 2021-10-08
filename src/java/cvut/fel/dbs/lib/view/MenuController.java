package cvut.fel.dbs.lib.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    public Button create;
    public Button read;
    public Button update;
    public Button delete;
    public Button jizda_add;
    public Button jizda_read;
    public Button jizda_delete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void goToCreateCestujici(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.CREATE_CESTUJICI);
    }

    public void goToReadCestujici(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.READ_CESTUJICI);
    }

    public void goToUpdateCestujici(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.UPDATE_CESTUJICI);
    }

    public void goToDeleteCestujici(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.DELETE_CESTUJICI);
    }

    public void goToAddCestujiciForJizda(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.ADD_CESTUJICI_FOR_JIZDA);
    }

    public void goToReadCestujiciForJizda(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.READ_CESTUJICI_FOR_JIZDA);
    }

    public void goToDeleteCestujiciForJizda(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.DELETE_CESTUJICI_FOR_JIZDA);
    }
}
