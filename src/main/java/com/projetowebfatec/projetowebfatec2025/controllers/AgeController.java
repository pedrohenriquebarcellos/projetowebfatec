package com.projetowebfatec.projetowebfatec2025.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AgeController {

    private static final Integer CHILDREN = 12;
    private static final Integer TEENAGER = 18;
    private static final Integer ADULT = 19;
    private static final Integer OLD = 61;
    
    @GetMapping("{age}")
    public String getAge(@RequestParam String age) {
        Integer currentAge = Integer.parseInt(age);

        if (currentAge < CHILDREN) {
            return "Criança";
        } 

        if (currentAge >= CHILDREN && currentAge <= TEENAGER) {
            return "Adolescente";
        }

        if (currentAge > TEENAGER && currentAge < OLD) {
            return "Adulto";
        }
        
        return "Idoso";
    }
    
}