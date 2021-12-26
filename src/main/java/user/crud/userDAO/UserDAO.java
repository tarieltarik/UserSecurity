package user.crud.userDAO;

import user.crud.model.User;

import java.util.List;

public interface UserDAO {
    List<User> allUsers();
    void save(User user);
    void delete(User user);
    User getById(Long id);
}
