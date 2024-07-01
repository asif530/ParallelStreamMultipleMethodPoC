package com.example.demo.Repository;


import com.example.demo.Model.ImageTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageTable,Long> {
    Boolean existsByImageLocationAndPropertyId(String location,Long propertyId);

    ImageTable getByImageLocationAndPropertyId(String location,Long propertyId);
}
