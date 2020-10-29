import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PointDao {

    private final SessionFactory factory;

    public PointDao(SessionFactory factory) {
        this.factory = factory;
    }

    public void create(Point point) {
        try (final Session session = factory.openSession()) {

            session.beginTransaction();  //открытие транзакции

            session.save(point);  // пихание объекта, хотя я бы сделал по другому

            session.getTransaction().commit(); //сохранение изменений
        }
    }

    public void delete(Point point) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.delete(point);

            session.flush(); //вообще хз зачем нижние эти строки
            session.refresh(point);

            session.getTransaction().commit();

        } catch (Exception e) {
            factory.getCurrentSession().getTransaction().rollback();
        }
    }

    public PointDao getPointDao(){
        return this;
    }

    public SessionFactory getFactory(){
        return factory;
    }

}
