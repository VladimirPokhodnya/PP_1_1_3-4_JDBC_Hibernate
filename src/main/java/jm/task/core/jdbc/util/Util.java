package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Util {
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/users_db");
        settings.put(Environment.USER, "user");
        settings.put(Environment.PASS, "1234");
        settings.put(Environment.SHOW_SQL, false);
        settings.put(Environment.FORMAT_SQL, true);
        settings.put(Environment.DEFAULT_SCHEMA, "users_db");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

        Configuration configuration = new Configuration();
        configuration.setProperties(settings);
        configuration.addAnnotatedClass(User.class);

        final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        try {
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }

        return sessionFactory;
    }
}
