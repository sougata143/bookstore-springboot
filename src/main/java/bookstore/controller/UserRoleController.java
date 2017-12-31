package bookstore.controller;

import com.sougata.bookstore.domain.Userrole;
import com.sougata.bookstore.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user/role")
public class UserRoleController {
    private UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/")
    public Iterable<Userrole> getAllRoles(){
        return userRoleService.list();
    }

    @GetMapping("/{id}")
    public Iterable<Userrole> getRolesById(@PathVariable(value = "id") long id){
        return userRoleService.list(id);
    }
}
