package user.crud.dao;
import  user.crud.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getRolesList();
    Role getRoleById(Long id);
}
