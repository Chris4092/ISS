import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import repository.ClientHibernate;
import repository.ManagerHibernate;
import repository.ReservationHibernate;
import repository.ShowHibernate;
import service.Service;


public class MainFX extends Application {
    public static void startApp(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            ClientHibernate repoClient = new ClientHibernate();
            repoClient.findOneUsername("sal");
            ManagerHibernate repoManager = new ManagerHibernate();
            ReservationHibernate repoReservation = new ReservationHibernate();
            ShowHibernate repoShow = new ShowHibernate();
            Service service = new Service(repoClient, repoManager, repoReservation, repoShow);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
            Parent root = loader.load();
            LoginController controller = loader.getController();
            controller.setService(service);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Log in");
            primaryStage.show();
        }
        catch (Exception ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error while starting the app" + ex);
            alert.showAndWait();
        }
    }
}
