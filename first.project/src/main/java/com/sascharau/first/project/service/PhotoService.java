package com.sascharau.first.project.service;

import com.sascharau.first.project.model.Photo;
import com.sascharau.first.project.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

    @Autowired(required = true)
    private final PhotoRepo repo;

    public PhotoService() {
        this.repo = null;
    }

    public Iterable<Photo> get() {
        return repo.findAll();
    }

    public Photo get(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void put(Photo photo) {
        repo.save(photo);
    }

    public void remove(Integer id) {
        repo.deleteById(id);
    }
}
