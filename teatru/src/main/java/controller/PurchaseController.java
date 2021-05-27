package controller;

import domain.Client;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import service.Service;

public class PurchaseController {
    public Button purchaseButton;

    public ChoiceBox<String> choiceBox = new ChoiceBox<>();

    private Service service;
    private Client client;
    private Stage stage;

    public void init(Service service, Client client, Stage stage)
    {



        this.service = service;
        this.client = client;
        this.stage= stage;
        ObservableList<String> choiceList = FXCollections.observableArrayList("None");
        int i = 50;
        int j = 1;
        while(this.client.getPoints() >= i)
        {
            String s = 10*j + "%(" + i + " points)";
            choiceList.add(s);
            i+=50;
            if(i == 300)
                break;
            j+=1;
        }
        choiceBox.setItems(choiceList);
        choiceBox.show();

    }

    public void purchase(MouseEvent mouseEvent) {
        String choice = choiceBox.getValue();
        if(!choice.equals("None")) {
            String s = choice.split("%")[0];
            this.client.setPoints(this.client.getPoints() - Integer.parseInt(s) * 5);
            // do what you have to do
            Stage stage1 = (Stage) this.choiceBox.getScene().getWindow();
            stage1.fireEvent(
                    new WindowEvent(
                            stage1,
                            WindowEvent.WINDOW_CLOSE_REQUEST
                    )
            );
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Selecteaza o oferta sau apasa butonul de X!", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
