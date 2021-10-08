package cvut.fel.dbs.lib.view;

import cvut.fel.dbs.lib.DatabaseConnection;
import cvut.fel.dbs.lib.model.Cestujici;
import cvut.fel.dbs.lib.model.Jizda;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReadCestujiciForJizdaController implements Initializable {

    public ComboBox<Jizda> dropdownJizdy;
    public Text textField;
    public Button backButton;

    private DatabaseConnection db;

    private Long selectedJizda;

    private void setup() {
        db = DatabaseConnection.getInstance();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();

        db.getEm().getTransaction().begin();
        List<Jizda> jizdy = db.getJizdaDao().findAll();
        db.getEm().getTransaction().commit();

        dropdownJizdy.setItems(FXCollections.observableArrayList(jizdy));

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

        dropdownJizdy.valueProperty().addListener((obs, oldVal, newVal) -> {
            selectedJizda = newVal.getId();

            db.getEm().getTransaction().begin();
            Jizda j = db.getJizdaDao().find(selectedJizda);
            db.getEm().getTransaction().commit();

            this.textField.setText("");

            StringBuilder sb = new StringBuilder();
            for (Cestujici c : j.getCestujici()) {
                System.out.println(c.getEmail());
                sb.append(c.getEmail()).append(" ").append(c.getJmeno()).append(" ").append(c.getPrijmeni()).append(" ").append(c.getTel_cislo()).append("\n");
            }

            this.textField.setText(sb.toString());
        });
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.MENU);
    }
}
