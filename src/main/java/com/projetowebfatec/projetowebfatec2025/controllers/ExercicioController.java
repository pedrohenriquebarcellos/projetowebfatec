package com.projetowebfatec.projetowebfatec2025.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExercicioController {

    @GetMapping("")
    public String HelloWorld() {
        return "No Param defined";
    }

    @GetMapping("{name}")
    public String GetRouteName(@PathVariable Optional<String> name) {
        return name.get();
    }
}
