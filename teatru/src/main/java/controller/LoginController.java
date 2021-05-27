package controller;

import domain.Client;
import domain.Manager;
import domain.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class LoginController {
    public Button loginButton;
    public TextField usernameTextField;
    public PasswordField passwordTextField;
    private Service service;
    public void loginClicked(MouseEvent mouseEvent) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();


        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/views/manager.fxml"));
        Parent root2 = loader2.load();
        ManagerController managerController = loader2.getController();


        if(username.equals("") || password.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nu pot ramane campuri vide!", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            User user = service.login(username, password);
            if(user == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Acest username nu a fost inregistrat!", ButtonType.OK);
                alert.showAndWait();
            }
            else{
                if(user.getPassword().equals(password))
                {
                    if(user instanceof Client) {
                        Stage main = new Stage();
                        Stage parent = (Stage) this.loginButton.getScene().getWindow();
                        mainController.setService(service,(Client) user);
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Main");
                        stage.setOnCloseRequest(event1 -> {
                            stage.close();
                            parent.show();
                        });
                        stage.show();
                        parent.hide();
                    }
                    else
                    {
                        Stage main = new Stage();
                        Stage parent = (Stage) this.loginButton.getScene().getWindow();
                        managerController.setService(service,(Manager) user);
                        Scene scene = new Scene(root2);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Main");
                        stage.setOnCloseRequest(event1 -> {
                            stage.close();
                            parent.show();
                        });
                        stage.show();
                        parent.hide();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Parola Invalida!", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        }
    }
    public void setService(Service service)
    {
        this.service = service;
    }
}
