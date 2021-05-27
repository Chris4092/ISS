package service;

import domain.*;
import repository.ClientHibernate;
import repository.ManagerHibernate;
import repository.ReservationHibernate;
import repository.ShowHibernate;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class Service {
    private ClientHibernate repoClient;
    private ManagerHibernate repoManager;
    private ReservationHibernate repoReservation;
    private ShowHibernate repoShow;


    public Service(ClientHibernate repoClient, ManagerHibernate repoManager, ReservationHibernate repoReservation, ShowHibernate repoShow) {
        this.repoClient = repoClient;
        this.repoManager = repoManager;
        this.repoReservation = repoReservation;
        this.repoShow = repoShow;
    }

    public Show getTodayShow(){
        Date date = new Date();
        AtomicReference<Show> todayShow = new AtomicReference<>();
        this.repoShow.findAll().forEach(x-> {
            System.out.println(date.getDate() + "-" + (date.getMonth()+1) + "-" + (date.getYear()+1900));
            System.out.println(x.getDate());
            System.out.println(x.getDate().equals(date.getDate() + "-" + (date.getMonth()+1) + "-" + (date.getYear()+1900)));
            if(x.getDate().equals(date.getDate() + "-" + (date.getMonth()+1) + "-" + (date.getYear()+1900)))
            {
                todayShow.set(x);
            }
        });
        return todayShow.get();
    }

    public User login(String username, String password) {
        int a = 0;
        User user = repoClient.findOneUsername(username);
        if(user == null)
        {
            a = 1;
        }
        User user2 = repoManager.findOneUsername(username);
        if(user2 == null && user == null)
        {
            a = 2;
        }
        if(a == 1)
        {
            return user2;
        }
        else
        {
            return user;
        }
    }

    public void updateShow(Show show)
    {
        this.repoShow.update(show);
    }

    public void addReservation(Reservation reservation) {
        this.repoReservation.add(reservation);
    }

    public void updateClient(Client client) {
        this.repoClient.update(client);
    }

    public Reservation findOne(long id) {
        return this.repoReservation.findOne(id);
    }

    public void deleteReservation(Reservation reservation) {
        this.repoReservation.delete(reservation);
    }

    public Show getShow(String text) {
        this.repoShow.findAll();
        AtomicReference<Show> show = new AtomicReference<>();
        this.repoShow.findAll().forEach(x-> {
            if(x.getDate().equals(text))
            {
                show.set(x);
            }
        });
        return show.get();
    }

    public void addShow(Show show) {
        this.repoShow.add(show);
    }

    public void deleteShow(Show show)
    {
        this.repoShow.delete(show);
    }
}
