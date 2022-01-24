package com.company.name.googledrivemanager.controller;

import com.company.name.googledrivemanager.manager.MainManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/excel")
public class MainManagerController {

    private final MainManager mainManager;

    public MainManagerController(MainManager mainManager) {
        this.mainManager = mainManager;
    }

    @PostMapping("/generate/{orderDate}")
    public void generateExcel(@Valid @PathVariable("orderDate") String orderDate){
        mainManager.generateDailyExcel(orderDate);
    }
}
