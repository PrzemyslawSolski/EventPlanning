package pl.coderslab.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void create(User user){
        userDao.create(user);
    }

    public void update(User user){
        userDao.update(user);
    }

    public User findOne(long id){
        return userDao.findOne(id);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public void delete(Long id){
//        userDao.deleteVenueRelations(id);
        userDao.delete(id);
    }
}
