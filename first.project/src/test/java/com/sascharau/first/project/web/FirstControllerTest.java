package com.sascharau.first.project.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sascharau.first.project.Application;
import com.sascharau.first.project.model.Photo;
import com.sascharau.first.project.service.PhotoService;
import com.sascharau.first.project.web.FirstController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes= Application.class)
@WebMvcTest(FirstController.class)
class FirstControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PhotoService service;


    @Test
    void helloWorld() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/hello");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("Hello World", result.getResponse().getContentAsString());
    }

    @Test
    void getOnePhotos() throws Exception {
        ArrayList test = new ArrayList<Photo>();
        test.add(new Photo());
        when(service.get()).thenReturn(test);
        RequestBuilder getRequest = MockMvcRequestBuilders.get("/photo");
        MvcResult result = mvc.perform(getRequest).andReturn();
        ObjectMapper mapper = new ObjectMapper();
        List<Photo> photos = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Photo>>(){});
        assertEquals(1, photos.size());
    }

    @Test
    void getNoPhotos() throws Exception {
        ArrayList<Photo> test = new ArrayList<Photo>();
        when(service.get()).thenReturn(test);
        RequestBuilder getRequest = MockMvcRequestBuilders.get("/photo");
        MvcResult result = mvc.perform(getRequest).andReturn();
        ObjectMapper mapper = new ObjectMapper();
        List<Photo> photos = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Photo>>(){});
        assertEquals(0, photos.size());
        Mockito.verify(service, times(1)).get();
    }
}