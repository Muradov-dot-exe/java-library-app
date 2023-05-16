package com.muradov.analytics.service;

import com.muradov.analytics.model.LibraryApp;
import com.muradov.analytics.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryAppImpl implements LibraryAppService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
public LibraryApp saveLibrary(LibraryApp libraryApp) {
        return studentRepository.save(libraryApp);
    }

    @Override
    public List<LibraryApp> getAllBooks() {
        return studentRepository.findAll();
    }
}
