package controller;

import domain.Client;
import domain.Reservation;
import domain.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;


public class MainController {
    public Button buyButton;
    public Button buyBonusButton;
    public Button reservationDetailsButton;
    public Button bonusPointsButton;
    public Label titleLabel;
    public Label timeLabel;
    public Label descriptionLabel;
    public Label priceLabel;
    public ListView<String> listView;
    private Service service;
    private Client client;
    private Show todayshow;
    ObservableList<String> items = FXCollections.observableArrayList ();
    private Integer previousPoints;


    public void init(){

        Show show = this.service.getTodayShow();
        this.titleLabel.setText(this.titleLabel.getText() + show.getTitle());
        this.descriptionLabel.setText(this.descriptionLabel.getText() + show.getDescription());
        this.timeLabel.setText(this.timeLabel.getText() + show.getTime());
        this.priceLabel.setText(this.priceLabel.getText() + show.getTicketPrice());
        show.decode(show.getFreeSeatListString());
        show.getFreeSeatList().forEach(x -> {
            items.add("Randul: " + x.get(0) + "Locul: " + x.get(1));
        });
        listView.setItems(items);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        todayshow = show;
    }

    public void setService(Service service, Client client) {
        this.service = service;
        this.client = client;
        this.init();
    }

    public void buyTickets(MouseEvent mouseEvent) {
        if(this.listView.getSelectionModel().getSelectedItems().size() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Selecteaza macar un bilet!",ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if(this.service.findOne(this.client.getId()) != null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Rezervarea exista deja!",ButtonType.OK);
            alert.showAndWait();
        }
        else
        {
            Reservation reservation = new Reservation(client.getId(),todayshow.getId(),this.listView.getSelectionModel().getSelectedItems().size(),0,todayshow.getTicketPrice());
            ObservableList<Integer> indices = this.listView.getSelectionModel().getSelectedIndices();
            AtomicReference<Integer> i = new AtomicReference<>(0);
            indices.forEach(x->{
                this.todayshow.getFreeSeatList().remove((int)(x - i.get()));
                i.set(i.get()+1);
            });
            this.items.clear();
            todayshow.getFreeSeatList().forEach(x -> {
                items.add("Randul: " + x.get(0) + "Locul: " + x.get(1));
            });
            todayshow.encode();
            this.service.updateShow(todayshow);
            this.service.addReservation(reservation);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Achizitie cu succes!",ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void buyTicketsUsingBonus(MouseEvent mouseEvent) throws IOException {
        if(this.listView.getSelectionModel().getSelectedItems().size() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Selecteaza macar un bilet!",ButtonType.OK);
            alert.showAndWait();
            return;
        }
        if(this.service.findOne(this.client.getId()) != null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Rezervarea exista deja!",ButtonType.OK);
            alert.showAndWait();
        }
        else
        {
            previousPoints = this.client.getPoints();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/purchase.fxml"));
            Parent root = loader.load();
            PurchaseController purchaseController = loader.getController();

            Stage parent = (Stage) this.bonusPointsButton.getScene().getWindow();
            purchaseController.init(service, this.client, parent);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Main");
            stage.setOnCloseRequest(event1 -> {
                stage.close();
                parent.show();
                this.continuef();
            });
            stage.show();
            parent.hide();


        }
    }

    private void continuef() {
        Integer current_points = this.client.getPoints();
        if(previousPoints - current_points != 0)
        {
            this.service.updateClient(this.client);
            Reservation reservation = new Reservation(client.getId(),todayshow.getId(),this.listView.getSelectionModel().getSelectedItems().size(),(previousPoints - current_points)/5,todayshow.getTicketPrice());
            ObservableList<Integer> indices = this.listView.getSelectionModel().getSelectedIndices();
            AtomicReference<Integer> i = new AtomicReference<>(0);
            indices.forEach(x->{
                this.todayshow.getFreeSeatList().remove((int)(x - i.get()));
                i.set(i.get()+1);
            });
            this.items.clear();
            todayshow.getFreeSeatList().forEach(x -> {
                items.add("Randul: " + x.get(0) + "Locul: " + x.get(1));
            });
            todayshow.encode();
            this.service.updateShow(todayshow);
            this.service.addReservation(reservation);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Achizitie cu succes!",ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void visualiseReservationDetails(MouseEvent mouseEvent) throws IOException {

        if(this.service.findOne(client.getId()) == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Nu exista nicio rezervare la spectacolul de azi pe numele dumneavoastra!",ButtonType.OK);
            alert.showAndWait();
        }
        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/reservation.fxml"));
            Parent root = loader.load();
            ReservationController reservationController = loader.getController();

            Stage parent = (Stage) this.bonusPointsButton.getScene().getWindow();
            reservationController.init(service, this.client, this.todayshow);
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

    }

    public void visualiseBonusPoints(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Aveti " + this.client.getPoints() + " puncte disponibile!",ButtonType.OK);
        alert.showAndWait();
    }
}
