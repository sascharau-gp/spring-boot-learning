package com.sascharau.first.project.web;

import com.sascharau.first.project.service.PhotoService;
import com.sascharau.first.project.model.Photo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
public class FirstController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/photo")
    public Iterable<Photo> getPhotos() {
        return photoService.get();
    }

    @GetMapping("/photo/{id}")
    public Photo getPhoto(@PathVariable Integer id) {
        Photo photo = photoService.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photoService.get(id);
    }

    @PostMapping("/photometa")
    public void createPhoto(@RequestBody @Valid Photo photo) {
        photoService.put(photo);
    }

    @PostMapping("photo")
    public Photo uploadPhoto(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setFileName(file.getOriginalFilename());
        photo.setContentType(file.getContentType());
        photo.setData(file.getBytes());
        photoService.put(photo);
        return photo;
    }

    @DeleteMapping("/photo/{id}")
    public void deletePhoto(@PathVariable Integer id) {
        photoService.remove(id);
    }


}
