package user.crud.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.crud.dao.RoleDAO;
import user.crud.model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService {
    @Autowired
    RoleDAO roleDao;

    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }
    public List<Role> getRolesList() {
        return roleDao.getRolesList();
    }
}
