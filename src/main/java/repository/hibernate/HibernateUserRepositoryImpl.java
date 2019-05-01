package repository.hibernate;

import model.User;
import org.hibernate.Session;
import repository.UserRepository;
import util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class HibernateUserRepositoryImpl implements UserRepository {

    public void save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(user);
        session.getTransaction().commit();
        session.close();

    }

    public List<User> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select distinct u from User u left join fetch u.skills s"; // insteaf of using hibernate.enable_lazy_load_no_trans
        List <User> users = session.createQuery(hql,User.class).getResultList();
        session.close();
        return users;
    }

    public void update(User user) {
        int id = user.getId();
        String newFistName = user.getFirstName();
        String newLastName = user.getLastName();
        String newSpecialty = user.getSpecialty();
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        User userToUpdate = session.get(User.class,id);
        userToUpdate.setFirstName(newFistName);
        userToUpdate.setLastName(newLastName);
        userToUpdate.setSpecialty(newSpecialty);

        session.update(userToUpdate);
        session.getTransaction().commit();
        session.close();

    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        User user = session.get(User.class,id);

        session.delete(user);
        session.getTransaction().commit();
        session.close();

    }

    public User getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class,id);
        session.close();
        return user;
    }
}

