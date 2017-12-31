package bookstore.service.Impl;

import com.sougata.bookstore.domain.User;
import com.sougata.bookstore.repository.UserRepository;
import com.sougata.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userReository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userReository = userRepository;
    }

    @Override
    public Iterable<User> list() {
        return userReository.findAll();
    }

    @Override
    public User save(User user) {
        return userReository.save(user);
    }

    @Override
    public User getById(long id) {
        return userReository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        userReository.delete(id);
    }

    @Override
    public void update(User user) {
        userReository.save(user);
    }
}
