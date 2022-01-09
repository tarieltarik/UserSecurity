package user.crud.dao;


import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import user.crud.model.Role;
import user.crud.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Repository
public class RoleDAOImpl implements RoleDAO{
    @PersistenceContext
    private EntityManager entityManager;

    public RoleDAOImpl() {
    }

    @Override
    public List<Role> getRolesList() {
        try{
            List<Role> roles = entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
            return roles;
        } catch (NoResultException ex){
            return null;
        }
    }

    @Override
    public Role getRoleById(Long id) {
        try{
            Role role = entityManager.createQuery("SELECT r FROM Role r where r.id = :id", Role.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return role;
        } catch (NoResultException ex){
            return null;
        }
    }
}