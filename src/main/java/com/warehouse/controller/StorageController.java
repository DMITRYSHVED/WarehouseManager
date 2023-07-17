package com.warehouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/storage")
public class StorageController {

    @GetMapping
    public String storage() {
        return "storage";
    }
}
