package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    SessionFactory factory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = factory.getCurrentSession()) {
            String query = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT, name  VARCHAR(255),lastName  VARCHAR(255),age TINYINT UNSIGNED, PRIMARY KEY (id))";
            session.beginTransaction();
            session.createSQLQuery(query).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка создания таблицы");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = factory.getCurrentSession()) {
            String query = "DROP TABLE IF EXISTS users";
            session.beginTransaction();
            session.createSQLQuery(query).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления таблицы");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            System.out.println("User c именем - " + name + " добавлен в базу данных");
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка сохранения пользователя " + name);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления пользователя " + id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> user = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            user = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            for(User u:user) {
                System.out.println(u);
            }
        } catch (Exception e) {
            System.out.println("Ошибка получения списка всех пользователей");
        }
        return user;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления всех пользователей");
        }
    }
}
