package cvut.fel.dbs.lib;

import cvut.fel.dbs.lib.view.StateManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Launcher extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Launches application
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Autobusove nadrazi");

        LOG.info("Setting up database connection");
        DatabaseConnection db = DatabaseConnection.getInstance();

        StateManager sm = StateManager.getInstance(stage, null);
        sm.update();

        stage.show();
    }
}
