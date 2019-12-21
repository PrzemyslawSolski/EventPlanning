package pl.coderslab.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

//    private final UserDao userDao;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
//        this.userDao = userDao;
        this.userRepository = userRepository;
    }

    public User getFirstByEmail(String email){
        return userRepository.findFirstByEmail(email);
    }

    public void create(User user){
        userRepository.save(user);
    }

    public void update(User user){
        userRepository.save(user);
    }

    public User findOne(long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void delete(Long id){
//        userDao.deleteVenueRelations(id);
        userRepository.deleteById(id);
    }
}
