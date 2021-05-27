package controller;

import domain.Client;
import domain.Reservation;
import domain.Show;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import service.Service;

public class ReservationController {
    public Label numeLabel;
    public Label prenumeLabel;
    public Label nrBileteLabel;
    public Label spectacolLabel;
    public Label SumaPlatitaLabel;
    public Label discountLabel;
    public Button deleteButton;
    private Client client;
    private Show show;
    private Service service;
    private Reservation reservation;

    public void init(Service service, Client client, Show show) {
        this.service = service;
        this.client = client;
        this.show = show;
        this.reservation = this.service.findOne(client.getId());
        this.numeLabel.setText(this.numeLabel.getText()+this.client.getlName());
        this.prenumeLabel.setText(this.prenumeLabel.getText()+this.client.getfName());
        this.nrBileteLabel.setText(this.nrBileteLabel.getText()+this.reservation.getNumberOfTickets());
        this.SumaPlatitaLabel.setText(this.SumaPlatitaLabel.getText()+this.reservation.getPrice());
        this.discountLabel.setText(this.discountLabel.getText()+this.reservation.getDiscount());
        this.spectacolLabel.setText(this.spectacolLabel.getText() + this.show.getTitle());
    }


    public void delete(MouseEvent mouseEvent) {
        this.service.deleteReservation(this.reservation);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Reservation Cancelled", ButtonType.OK);
        alert.showAndWait();
        Stage stage1 = (Stage) this.numeLabel.getScene().getWindow();
        stage1.fireEvent(
                new WindowEvent(
                        stage1,
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
        );
    }

}
