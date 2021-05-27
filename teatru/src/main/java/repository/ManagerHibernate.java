package repository;


import domain.Manager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ManagerHibernate {

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

    public Manager findOneUsername(String username) {
        this.initialize();
        Manager manager=null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query=session.createQuery("from Manager where username=:username");
            query.setParameter("username",username);
            manager=(Manager) query.uniqueResult();
            session.getTransaction().commit();
            this.close();
        }
        catch (Exception ex) {
            this.close();
        }
        return manager;
    }


}
