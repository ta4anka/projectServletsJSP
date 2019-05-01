package repository.hibernate;


import model.Skill;
import org.hibernate.Session;

import repository.SkillRepository;
import util.HibernateUtil;


import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateSkillRepositoryImpl implements SkillRepository {

    public void save(Skill skill) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(skill);
        session.getTransaction().commit();
        session.close();

    }

    public List<Skill> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();


        /*by using Criteria API:
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Skill> criteria = builder.createQuery(Skill.class);
        Root <Skill> root = criteria.from(Skill.class);
        criteria.select(root);
        List<Skill> skills = session.createQuery(criteria).getResultList();*/
        // By ising HQL:
        String hql = "from Skill";
        List <Skill> skills = session.createQuery(hql,Skill.class).getResultList();
        session.close();
        return skills;
    }

    public void update(Skill skill) {
        int id = skill.getId();
        String newName = skill.getName();
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Skill skillToUpdate = session.get(Skill.class,id);
        skillToUpdate.setName(newName);

        session.update(skillToUpdate);
        session.getTransaction().commit();
        session.close();

    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Skill skill = session.get(Skill.class,id);

        session.delete(skill);
        session.getTransaction().commit();
        session.close();
    }

    public Skill getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Skill skill = session.get(Skill.class,id);
        session.close();
        return skill;
    }
}
