package cvut.fel.dbs.lib.view;

import cvut.fel.dbs.lib.DatabaseConnection;
import cvut.fel.dbs.lib.model.Cestujici;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReadCestujiciController implements Initializable {
    public Text cestujici;
    public Button back;

    DatabaseConnection db;

    private void setup() {
        db = DatabaseConnection.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();

        db.getEm().getTransaction().begin();
        List<Cestujici> cestujici = db.getCestujiciDao().findAll();
        db.getEm().getTransaction().commit();

        StringBuilder sb = new StringBuilder();
        for (Cestujici c : cestujici) {
            sb.append(c.getEmail()).append(" ").append(c.getJmeno()).append(" ").append(c.getPrijmeni()).append(" ").append(c.getTel_cislo()).append("\n");
        }

        this.cestujici.setText(sb.toString());
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        StateManager sm = StateManager.getInstance(null, null);
        sm.changeState(State.MENU);
    }
}
