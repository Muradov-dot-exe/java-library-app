package com.muradov.analytics.service;

import com.muradov.analytics.model.LibraryApp;

import java.util.List;

public interface LibraryAppService {
    public LibraryApp saveLibrary(LibraryApp libraryApp);
    public List<LibraryApp> getAllBooks();
    public LibraryApp getId(Integer id);
    public void delete(Integer id);

}
