package repository;
import domain.Show;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class ShowHibernate {
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

    public Show findOne(Long id) {
        this.initialize();
        Show show=null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query=session.createQuery("from Show where id=:idShow");
            query.setParameter("idShow",id);
            show=(Show) query.uniqueResult();
            session.getTransaction().commit();
            this.close();
        }
        catch (Exception ex) {
            this.close();
        }
        return show;
    }

    public Iterable<Show> findAll() {
        this.initialize();
        Show show=null;
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT a FROM Show a", Show.class).getResultList();
        }
        catch (Exception ex) {
            this.close();
        }
        return null;
    }

    public void add(Show entity) {
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

    public void delete(Show entity) {
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

    public void update(Show entity) {
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
