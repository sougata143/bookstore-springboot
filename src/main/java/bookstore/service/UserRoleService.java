package bookstore.service;

import com.sougata.bookstore.domain.Userrole;

import java.util.List;

public interface UserRoleService {
    List<Userrole> list();
    List<Userrole>list(long id);
}
