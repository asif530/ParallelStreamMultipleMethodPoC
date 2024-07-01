package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image_table",uniqueConstraints = {
        @UniqueConstraint(columnNames = "image_path"),
        @UniqueConstraint(columnNames = "s3_image_path"),
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImageTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "property_id")
    private Long propertyId;
    @Column(name = "image_path")
    private String imageLocation;
    @Column(name = "s3_image_path")
    private String imageLocationS3;

}
