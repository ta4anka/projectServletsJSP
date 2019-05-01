package repository.hibernate;


import model.Customer;

import org.hibernate.Session;
import repository.CustomerRepository;
import util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class HibernateCustomerRepositoryImpl implements CustomerRepository {

    public void save(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(customer);
        session.getTransaction().commit();
        session.close();

    }

    public List<Customer> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Customer";
        Query query = session.createQuery(hql);
        List customers = query.getResultList();
        session.close();
        return customers;
    }

    public void update(Customer customer) {
        int id = customer.getId();
        String newName = customer.getName();
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Customer customerToUpdate = session.get(Customer.class,id);
        customerToUpdate.setName(newName);

        session.update(customerToUpdate);
        session.getTransaction().commit();
        session.close();

    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Customer customer = session.get(Customer.class,id);

        session.delete(customer);
        session.getTransaction().commit();
        session.close();

    }

    public Customer getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Customer customer = session.get(Customer.class,id);
        session.close();
        return customer;
    }
}

