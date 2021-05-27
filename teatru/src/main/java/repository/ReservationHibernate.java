package repository;
import domain.Reservation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class ReservationHibernate {
    private SessionFactory sessionFactory;

    private void initialize(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from agentie.persistance.hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exceptie "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    private void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    public Reservation findOne(Long id) {
        this.initialize();
        Reservation show=null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query=session.createQuery("from Reservation where idClient=:idReservation");
            query.setParameter("idReservation",id);
            show=(Reservation) query.uniqueResult();
            session.getTransaction().commit();
            this.close();
        }
        catch (Exception ex) {
            this.close();
        }
        return show;
    }


    public void add(Reservation entity) {
        this.initialize();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
    }

    public void delete(Reservation entity) {
        this.initialize();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
    }

    public void update(Reservation entity) {
        this.initialize();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            this.close();
        } catch (Exception ex) {
            this.close();
        }
    }
}
