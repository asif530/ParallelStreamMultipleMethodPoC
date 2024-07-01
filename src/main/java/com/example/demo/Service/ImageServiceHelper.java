package com.example.demo.Service;

import com.example.demo.Model.ImageTable;
import com.example.demo.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceHelper {
    @Autowired
    ImageRepository imageRepository;

    public int processImage(Long propertyId){
        saveImageLocal(propertyId);
        saveImageInDB(propertyId);
        saveImageInS3(propertyId);
        updateImageLocation();
        return 1;
    }
    private void saveImageLocal(Long propertyId){
        Path folderPath = Path.of("D:\\Interview 27112023\\Spring MVC+Boot\\Springboot CRUD with parallet Stream\\Images");
        createFolder(folderPath);

        System.out.println("==================="+"saveImageLocal_"+propertyId+"==================="+Thread.currentThread().getName()+"==============================");

        Path folderPath1 = folderPath.resolve(propertyId.toString());
        createFolder(folderPath1);
    }
    
    private void saveImageInDB(Long propertyId){
        Path folderPath = Path.of("D:\\Interview 27112023\\Spring MVC+Boot\\Springboot CRUD with parallet Stream\\Images");
        createFolder(folderPath);

        Path folderPath1 = folderPath.resolve(propertyId.toString());
        createFolder(folderPath1);
        for (int i = 0; i < 5; i++) {
            System.out.println("==================="+"saveImageInDB_"+propertyId+"==================="+Thread.currentThread().getName()+"==============================");
            if(!imageRepository.existsByImageLocationAndPropertyId(folderPath1 +"\\"+i,propertyId)){
                ImageTable imageTable = new ImageTable();
                imageTable.setPropertyId(propertyId);
                imageTable.setImageLocation(folderPath1 +"\\"+i);
                imageRepository.save(imageTable);
            }
            System.out.println("==================="+"saveImageInDB_"+propertyId+"==================="+Thread.currentThread().getName()+"==============================");
        }
         
    }
    private void saveImageInS3(Long propertyId){
        Path folderPath = Path.of("D:\\Interview 27112023\\Spring MVC+Boot\\Springboot CRUD with parallet Stream\\Images");
        Path folderPath1 = folderPath.resolve(propertyId.toString());

        for (int i = 0; i < 5; i++) {
            System.out.println("==================="+"saveImageInS3_"+propertyId+"==================="+Thread.currentThread().getName()+"==============================");
            if(imageRepository.existsByImageLocationAndPropertyId(folderPath1 +"\\"+i,propertyId)){
                ImageTable imageTable = imageRepository.getByImageLocationAndPropertyId (folderPath1 +"\\"+i,propertyId);
                imageTable.setImageLocationS3(folderPath1 +"\\"+i+"\\");
                imageRepository.save(imageTable);
            }
            System.out.println("==================="+"saveImageInS3_"+propertyId+"==================="+Thread.currentThread().getName()+"==============================");
        }
    }
    private void updateImageLocation(){
        System.out.println("Image location Updated!!!!!!!!!!!!!!!!!!!!!!!");
    }

    private static void createFolder(Path folderPath) {
        if (Files.notExists(folderPath)) {
            try {
                Files.createDirectories(folderPath); //Folder created successfully!
            } catch (Exception e) {
                System.err.println("Failed to create folder: " + e.getMessage());
            }
        } else {
            System.out.println("Folder already exists."+folderPath);
        }
    }
}
