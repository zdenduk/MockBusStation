package cvut.fel.dbs.lib.view;

import cvut.fel.dbs.lib.DatabaseConnection;
import cvut.fel.dbs.lib.model.Cestujici;
import cvut.fel.dbs.lib.model.Jizda;
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

public class AddCestujiciForJizdaController implements Initializable {
    public ComboBox<Cestujici> dropdownCestujici;
    public ComboBox<Jizda> dropdownJizdy;
    public Button add;

    private Long selectedJizda;
    private String selectedCestujici;

    private DatabaseConnection db;

    private void setup() {
        db = DatabaseConnection.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();

        db.getEm().getTransaction().begin();
        List<Cestujici> cestujici = db.getCestujiciDao().findAll();
        List<Jizda> jizdy = db.getJizdaDao().findAll();
        db.getEm().getTransaction().commit();

        dropdownCestujici.setItems(FXCollections.observableArrayList(cestujici));
        dropdownJizdy.setItems(FXCollections.observableArrayList(jizdy));

        dropdownCestujici.setConverter(new StringConverter<Cestujici>() {
            @Override
            public String toString(Cestujici c) {
                return c == null ? " " : c.getEmail();
            }

            @Override
            public Cestujici fromString(String s) {
                return null;
            }
        });

        dropdownJizdy.setConverter(new StringConverter<Jizda>() {
            @Override
            public String toString(Jizda j) {
                return j == null ? " " : j.getSpolecnost() + " " + j.getLinka();
            }

            @Override
            public Jizda fromString(String s) {
                return null;
            }
        });

        dropdownCestujici.valueProperty().addListener((obs, oldVal, newVal) -> selectedCestujici = newVal.getEmail());

        dropdownJizdy.valueProperty().addListener((obs, oldVal, newVal) -> selectedJizda = newVal.getId());
    }

    /**
     * Adds Cestujici with @selectedCestujici email to database
     *
     * @param mouseEvent
     */
    public void addCestujici(ActionEvent mouseEvent) {
        db.getEm().getTransaction().begin();
        Cestujici c = db.getCestujiciDao().find(selectedCestujici);
        Jizda j = db.getJizdaDao().find(selectedJizda);
        c.addJizda(j);
        db.getCestujiciDao().merge(c);
        db.getEm().getTransaction().commit();

        db.getEm().getTransaction().begin();
        db.getEm().flush();
        db.getEm().getTransaction().commit();
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.MENU);
    }

}
