package controller;

import model.Project;
import repository.ProjectRepository;
import repository.hibernate.HibernateProjectRepositoryImpl;


import java.util.List;

public class ProjectController{
    ProjectRepository projectRepository = new HibernateProjectRepositoryImpl();

    public void save(Project project){
        if(project == null){
            throw new IllegalArgumentException();
        }else {
            projectRepository.save(project);
        }
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }


    public void update(Project project){
        if(project == null){
            throw  new IllegalArgumentException();
        }else projectRepository.update(project);
    }


    public void delete(Integer id){
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {
            projectRepository.delete(id);
        }
    }

    public Project getById(Integer id) {
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {

        }return projectRepository.getById(id);
    }
}

