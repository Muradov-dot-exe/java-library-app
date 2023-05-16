package com.muradov.analytics.controller;

import com.muradov.analytics.model.LibraryApp;
import com.muradov.analytics.service.LibraryAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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

//    @GetMapping("/{id}")
//    public ResponseEntity<LibraryApp>get(@PathVariable Integer id){
//        try {
//        LibraryApp libraryApp=libraryAppService
//        }catch (NoSuchElementException e){
//            return new ResponseEntity<LibraryApp>(HttpStatus.NOT_FOUND)
//        }
//    }
}
