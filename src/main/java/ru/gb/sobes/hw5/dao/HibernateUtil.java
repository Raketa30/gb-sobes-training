package ru.gb.sobes.hw5.dao;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {
    private HibernateUtil() {
    }

    private static SessionFactory sessionFactory;

    static {
        final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();

            Flyway flyway = Flyway.configure()
                    .dataSource("jdbc:postgresql://localhost:5432/hw5", "postgres", "postgres").load();
            flyway.migrate();

        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }


    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
