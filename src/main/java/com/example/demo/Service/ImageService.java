package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    ImageServiceHelper imageServiceHelper;
    public void callImage(List<Long> propertyIds){
        //imageServiceHelper.processImage(101L);
        // Map<Integer,Integer> a =
        Map<Integer, Integer> resultMap = propertyIds.parallelStream()
                .collect(Collectors.toMap(
                        Math::toIntExact,
                        propertyId -> imageServiceHelper.processImage(propertyId)
                ));
    }
}
