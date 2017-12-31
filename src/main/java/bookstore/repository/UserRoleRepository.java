package bookstore.repository;

import com.sougata.bookstore.domain.Userrole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<Userrole, Long>{
    Userrole findByUserid(Long userid);
}
