package com.warehouse.manager;

import com.warehouse.dao.RoleDao;
import com.warehouse.dao.UserDao;
import com.warehouse.entity.Role;
import com.warehouse.entity.User;
import com.warehouse.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleDao.getById(2, Role.class));
        user.setApproved(false);
        userDao.save(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public User getById(int id) {
        return userDao.getById(id, User.class);
    }

    public User findByLogin(String login) {
        return userDao.getByLogin(login);
    }

    public User findByEmail(String email) {
        return userDao.getByEmail(email);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return userDao.getList();
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();

        user.setLogin(userDTO.getLogin());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public void autoLogin(String login, String password) {

    }

    public void editUser(User user) {
        userDao.update(user);
    }
}
