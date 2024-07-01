package com.example.demo.Controller;

import com.example.demo.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/transferContent")
public class ContentTransfer {

    @Autowired
    ImageService imageService;

    @GetMapping("/abc")
    public void transferContent(){
        List<Long> propertyIds = new ArrayList<>(Arrays.asList(101L,102L,103L,104L,105L,106L,107L,108L,109L,110L,111L,112L,114L,113L,115L,116L,117L,118L,119L,120L));
        imageService.callImage(propertyIds);
    }
}
