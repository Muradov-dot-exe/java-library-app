package com.muradov.analytics.controller;

import com.muradov.analytics.model.LibraryApp;
import com.muradov.analytics.service.LibraryAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@CrossOrigin
public class LibraryAppController {
    @Autowired
    private LibraryAppService libraryAppService;
    @PostMapping("/add")
    public String add(@RequestBody LibraryApp libraryApp){
        libraryAppService.saveLibrary(libraryApp);
        return "Добавена е нова книга";
    }
    @GetMapping("/getAll")
    public List<LibraryApp>getAllBooks(){
        return libraryAppService.getAllBooks();
    }
}
