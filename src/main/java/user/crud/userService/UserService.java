package user.crud.userService;

import user.crud.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    void save(User user);
    void delete(User user);
    void deleteById (Long id);
    User getById(Long id);
    User getUserByName(String username);
}