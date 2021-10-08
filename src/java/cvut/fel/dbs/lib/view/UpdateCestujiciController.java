package cvut.fel.dbs.lib.view;

import cvut.fel.dbs.lib.DatabaseConnection;
import cvut.fel.dbs.lib.model.Cestujici;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateCestujiciController implements Initializable {
    public ComboBox<Cestujici> dropdown;
    public TextField email;
    public TextField phone;
    public TextField name;
    public TextField surname;
    public CheckBox premium;
    public Button updateButton;
    public Button backButton;

    DatabaseConnection db;

    private String selected;

    private void setup() {
        db = DatabaseConnection.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();

        db.getEm().getTransaction().begin();
        List<Cestujici> cestujici = db.getCestujiciDao().findAll();
        db.getEm().getTransaction().commit();

        dropdown.setItems(FXCollections.observableArrayList(cestujici));

        dropdown.setConverter(new StringConverter<Cestujici>() {
            @Override
            public String toString(Cestujici c) {
                return c == null ? " " : c.getEmail();
            }

            @Override
            public Cestujici fromString(String s) {
                return null;
            }
        });

        dropdown.valueProperty().addListener((obs, oldVal, newVal) -> selected = newVal.getEmail());
    }

    public void updateCestujici(ActionEvent actionEvent) {
        Cestujici c = new Cestujici();
        c.setEmail(selected);
        c.setTel_cislo(phone.textProperty().get());
        c.setJmeno(name.textProperty().get());
        c.setPrijmeni(surname.textProperty().get());
        c.setPremium(premium.isSelected());

        db.getEm().getTransaction().begin();
        db.getCestujiciDao().merge(c);
        db.getEm().getTransaction().commit();
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.MENU);
    }
}
