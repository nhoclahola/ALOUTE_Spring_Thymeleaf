package com.nhoclahola.socialnetworkv1.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{
    @GetMapping("/home")
    public String homeControllerHandler()
    {
        return "Hello world";
    }

}
