package cvut.fel.dbs.lib.view;

import cvut.fel.dbs.lib.DatabaseConnection;
import cvut.fel.dbs.lib.model.Cestujici;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateCestujiciController implements Initializable {
    public TextField email;
    public TextField phone;
    public TextField name;
    public TextField surname;
    public CheckBox premium;
    public Button back;

    DatabaseConnection db;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();
    }


    private void setup() {
        db = DatabaseConnection.getInstance();
    }

    /**
     * Creates cestujici with params specified in Textfields
     *
     * @param actionEvent
     */
    public void createCestujici(ActionEvent actionEvent) {
        Cestujici c = new Cestujici();
        c.setEmail(email.textProperty().get());
        c.setTel_cislo(phone.textProperty().get());
        c.setJmeno(name.textProperty().get());
        c.setPrijmeni(surname.textProperty().get());
        c.setPremium(premium.isSelected());

        db.getEm().getTransaction().begin();
        db.getCestujiciDao().create(c);
        db.getEm().getTransaction().commit();
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.MENU);
    }
}
