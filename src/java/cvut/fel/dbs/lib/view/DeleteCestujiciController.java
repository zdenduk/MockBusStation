package cvut.fel.dbs.lib.view;

import cvut.fel.dbs.lib.DatabaseConnection;
import cvut.fel.dbs.lib.model.Cestujici;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteCestujiciController implements Initializable {
    public ComboBox<Cestujici> dropdown;
    public Button backButton;
    public Button deleteButton;

    private DatabaseConnection db;

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

        dropdown.valueProperty().addListener((obs, oldVal, newVal) -> selected = newVal != null ? newVal.getEmail() : null);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.MENU);
    }

    /**
     * Deletes Cestujici with @selected email from database
     *
     * @param actionEvent
     */
    public void delete(ActionEvent actionEvent) {
        db.getEm().getTransaction().begin();
        Cestujici c = db.getCestujiciDao().find(selected);
        db.getCestujiciDao().delete(c);
        db.getEm().getTransaction().commit();

        db.getEm().getTransaction().begin();
        List<Cestujici> cestujici = db.getCestujiciDao().findAll();
        db.getEm().getTransaction().commit();

        dropdown.setItems(FXCollections.observableArrayList(cestujici));
    }
}
