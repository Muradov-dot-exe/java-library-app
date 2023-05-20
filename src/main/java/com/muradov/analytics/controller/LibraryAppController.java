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
    public ResponseEntity<LibraryApp> createBook(@RequestBody LibraryApp libraryApp){
         LibraryApp savedBook =libraryAppService.saveLibrary(libraryApp);

        return new ResponseEntity<>(savedBook,HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public List<LibraryApp>getAllBooks(){
        return libraryAppService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryApp>getId(@PathVariable Integer id){
        try {
        LibraryApp libraryApp=libraryAppService.getId(id);
        return new ResponseEntity<LibraryApp>(libraryApp,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<LibraryApp>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<LibraryApp>update(@RequestBody LibraryApp libraryApp,@PathVariable Integer id){

        try {
            LibraryApp existingBook=libraryAppService.getId(id);
            libraryAppService.saveLibrary(libraryApp);
            return  new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e ){
            return new ResponseEntity<LibraryApp>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        libraryAppService.delete(id);
        return "КНИГА НОМЕР "+id+"Е ИЗТРИТА УСПЕШНО!";
    }
}
