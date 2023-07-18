package com.warehouse.manager;

import com.warehouse.dao.UserDao;
import com.warehouse.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AdminManager {

    @Autowired
    Helper helper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    public List<String> getUsersLog () {
        File file = new File("myApp.log");
        ArrayList<String> logs = new ArrayList<>();
        String line;
        BufferedReader bufferedReader = null;

        try {
           Reader reader = new FileReader(file);
            try (reader) {
                bufferedReader = new BufferedReader(reader);
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.isEmpty()) {
                        continue;
                    }
                    if (line.contains("c.w.controller.UserController")||line.contains("c.w.controller.AdminController")) {
                        logs.add(helper.logParse(line));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader!=null){
                    try {
                        bufferedReader.close();
                    } catch (IOException exception) {
                        log.error(exception.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException exception) {
            log.error(exception.getMessage());
        }
        return logs;
    }

    public boolean isAdmin(String password, String login) {
        if (passwordEncoder.encode(password).equals(userDao.getByLogin(login).getPassword())){
            return true;
        }
         return false;
    }
}
