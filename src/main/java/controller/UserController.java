package controller;

import model.User;
import repository.UserRepository;
import repository.hibernate.HibernateUserRepositoryImpl;

import java.util.List;

public class UserController{
    UserRepository userRepository = new HibernateUserRepositoryImpl();


    public void save(User user){
        if(user == null){
            throw new IllegalArgumentException();
        }else {
            userRepository.save(user);
        }
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void update(User user){
        if(user == null){
            throw  new IllegalArgumentException();
        }else userRepository.update(user);
    }


    public void delete(Integer id){
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {
            userRepository.delete(id);
        }
    }

    public User getById(Integer id){
        if(id == null || id < 0){
            throw new IllegalArgumentException();
        }else {

        }return userRepository.getById(id);
    }

}

