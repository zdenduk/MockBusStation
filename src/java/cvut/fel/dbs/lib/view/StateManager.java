package cvut.fel.dbs.lib.view;

import cvut.fel.dbs.lib.Launcher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Manager that holds current state of application.
 */
public class StateManager {

    private Stage stage;
    private Parent root;

    private State state;

    private static StateManager instance = null;

    private static final Logger LOG = LoggerFactory.getLogger(Launcher.class);

    /**
     * Creates instance of StateManager.
     *
     * @param stage JavaFX stage
     * @param root  JavaFX parent
     */
    private StateManager(Stage stage, Parent root) {
        this.stage = stage;
        this.root = root;

        this.state = State.MENU;
    }

    public static StateManager getInstance(Stage stage, Parent root) {
        if (instance == null)
            instance = new StateManager(stage, root);
        return instance;
    }

    /**
     * Changes state to another state.
     *
     * @param state state to be changed to
     */
    public void changeState(State state) throws IOException {
        setState(state);
        update();
        LOG.info("State changed to: " + state);
    }

    /**
     * @param state state to be set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Decides which window to show depending on current state.
     */
    public void update() throws IOException {
        switch (state) {
            case MENU:
                root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
                break;
            case CREATE_CESTUJICI:
                root = FXMLLoader.load(getClass().getResource("/fxml/create_cestujici.fxml"));
                break;
            case READ_CESTUJICI:
                root = FXMLLoader.load(getClass().getResource("/fxml/read_cestujici.fxml"));
                break;
            case UPDATE_CESTUJICI:
                root = FXMLLoader.load(getClass().getResource("/fxml/update_cestujici.fxml"));
                break;
            case DELETE_CESTUJICI:
                root = FXMLLoader.load(getClass().getResource("/fxml/delete_cestujici.fxml"));
                break;
            case ADD_CESTUJICI_FOR_JIZDA:
                root = FXMLLoader.load(getClass().getResource("/fxml/add_cestujici_for_jizda.fxml"));
                break;
            case READ_CESTUJICI_FOR_JIZDA:
                root = FXMLLoader.load(getClass().getResource("/fxml/read_cestujici_for_jizda.fxml"));
                break;
            case DELETE_CESTUJICI_FOR_JIZDA:
                root = FXMLLoader.load(getClass().getResource("/fxml/delete_cestujici_for_jizda.fxml"));
        }
        stage.setScene(new Scene(root));
    }
}
