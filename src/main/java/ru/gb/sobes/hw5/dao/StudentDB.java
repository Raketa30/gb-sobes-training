package ru.gb.sobes.hw5.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.gb.sobes.hw5.domain.Student;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDB {
    private final SessionFactory sessionFactory;

    public StudentDB() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public boolean delete(Student student) {
        Transaction tx;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(student);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(Long id) {
        Transaction tx;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Student> criteriaDelete = criteriaBuilder.createCriteriaDelete(Student.class);
            Root<Student> root = criteriaDelete.from(Student.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
            session.createQuery(criteriaDelete).executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getTableRowsCount() {
        Transaction tx;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            long count = (Long)session.createQuery("select count(*) from Student").uniqueResult();
            tx.commit();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public Optional<Student> findById(Long id) {
        Optional<Student> optionalProduct;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Student as s where s.id = :id");
            query.setParameter("id", id);
            optionalProduct = Optional.of((Student) query.getSingleResult());
            return optionalProduct;
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("select s from Student s");
            return (List<Student>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean save(Student student) {
        Transaction tx;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Student student) {
        Transaction tx;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(student);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
