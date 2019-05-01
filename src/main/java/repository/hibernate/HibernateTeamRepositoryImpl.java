package repository.hibernate;



import model.Team;
import org.hibernate.Session;
import repository.TeamRepository;
import util.HibernateUtil;

import javax.persistence.Query;
import java.util.List;

public class HibernateTeamRepositoryImpl implements TeamRepository {

    public void save(Team team) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(team);
        session.getTransaction().commit();
        session.close();

    }

    public List<Team> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Team";
        Query query = session.createQuery(hql);
        List Teams = query.getResultList();
        session.close();
        return Teams;
    }

    public void update(Team team) {
        int id = team.getId();
        String newName = team.getName();

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Team teamToUpdate = session.get(Team.class,id);
        teamToUpdate.setName(newName);

        session.update(teamToUpdate);
        session.getTransaction().commit();
        session.close();

    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Team team = session.get(Team.class,id);

        session.delete(team);
        session.getTransaction().commit();
        session.close();

    }

    public Team getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Team team = session.get(Team.class,id);
        session.close();
        return team;
    }
}
