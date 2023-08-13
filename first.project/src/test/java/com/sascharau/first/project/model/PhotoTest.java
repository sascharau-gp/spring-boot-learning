package com.sascharau.first.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {

    @Test
    void getContentType() {
        Photo photo = new Photo();
        photo.setContentType(MediaType.IMAGE_JPEG_VALUE);
        Assertions.assertEquals(photo.getContentType(), MediaType.IMAGE_JPEG_VALUE);
    }
}