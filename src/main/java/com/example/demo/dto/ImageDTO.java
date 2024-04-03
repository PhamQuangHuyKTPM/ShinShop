package com.example.demo.dto;

import java.nio.file.Path;
import java.util.stream.Stream;

public class ImageDTO {
    private Integer id;
    private String imageName;

    private Stream<Path> loadAllImage;

    public ImageDTO() {
    }

    public ImageDTO(Integer id, String imageName, Stream<Path> loadAllImage) {
        this.id = id;
        this.imageName = imageName;
        this.loadAllImage = loadAllImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Stream<Path> getLoadAllImage() {
        return loadAllImage;
    }

    public void setLoadAllImage(Stream<Path> loadAllImage) {
        this.loadAllImage = loadAllImage;
    }
}
