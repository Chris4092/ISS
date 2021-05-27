package controller;

import domain.Manager;
import domain.Show;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import service.Service;

public class ManagerController {
    public TextField titleTextField;
    public TextField descriptionTextField;
    public TextField dateTextField;
    public TextField timeTextField;
    public TextField priceTextField;
    public Button addButton;
    public Button deleteButton;
    private Manager manager;
    private Service service;

    public void setService(Service service, Manager user) {
        this.service = service;
        this.manager = user;
    }

    public void addShow(MouseEvent mouseEvent) {
        if(titleTextField.getText().equals("") || descriptionTextField.getText().equals("") || dateTextField.getText().equals("") || timeTextField.getText().equals("") || priceTextField.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nu se pot lasa campuri vide", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if(this.service.getShow(this.dateTextField.getText())!=null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Exista un show in acea zi!", ButtonType.OK);
            alert.showAndWait();
        }
        else{
            Show show = new Show(dateTextField.getText(),timeTextField.getText(),Integer.parseInt(priceTextField.getText()),titleTextField.getText(),descriptionTextField.getText(),"111213141516171819212223242526272829");
            this.service.addShow(show);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Spectacol adaugat cu succes!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void deleteShow(MouseEvent mouseEvent) {
        if(dateTextField.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nu se pot lasa campuri vide", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if(this.service.getShow(this.dateTextField.getText())==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nu exista un show in acea zi!", ButtonType.OK);
            alert.showAndWait();
        }
        else
        {
            this.service.deleteShow(this.service.getShow(dateTextField.getText()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Spectacol sters cu succes!", ButtonType.OK);
            alert.showAndWait();

        }
    }
}
