package user.crud.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import user.crud.model.User;

import javax.persistence.*;
import java.util.List;

@Component
@Repository
public class UserDAOImpl implements UserDAO {
    public UserDAOImpl() {
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        List<User> resultList = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        return resultList;
    }


    @Override
    public void save(User user) {
        User u = entityManager.merge(user);
        entityManager.persist(u);
    }


    @Override
    public void delete(User user) {
        User u = entityManager.merge(user);
        entityManager.remove(u);
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String username) {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u where u.name = :name", User.class)
                    .setParameter("name", username)
                    .getSingleResult();
            return user;
        } catch (NoResultException ex) {
            return null;
        }
    }
}