package com.warehouse.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AdminManager {

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
                        logs.add(line);
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
}
