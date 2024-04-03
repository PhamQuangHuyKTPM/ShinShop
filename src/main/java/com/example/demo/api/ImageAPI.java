package com.example.demo.api;

import com.example.demo.controller.web.HomeWebController;
import com.example.demo.dto.ImageDTO;
import com.example.demo.model.ImageEntity;
import com.example.demo.model.ProductEntity;
import com.example.demo.service.ImageService;
import com.example.demo.service.ProductService;
import com.example.demo.service.impl.FileSystemStorageService;
import lombok.Data;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ImageAPI {

    @Autowired
    private FileSystemStorageService storageService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    private Logger logger = LoggerFactory.getLogger(ImageAPI.class);

    @PostMapping("/upload-files")
    public ResponseEntity<?> uplooadMultipleFiles(@RequestParam("files") MultipartFile[] files){

        for(MultipartFile file : files){
            ImageEntity img = new ImageEntity();
            img.setImageName(StringUtils.cleanPath(file.getOriginalFilename()));


            storageService.store(file);

            if (!imageService.existsByImageName(file.getOriginalFilename())) {
                // Tệp không tồn tại, lưu tệp và thêm tên tệp vào cơ sở dữ liệu
                ImageEntity image = new ImageEntity();

                image.setImageName(file.getOriginalFilename());
                imageService.save(image);
            }
        }

        this.logger.info("{} number of files uploaded", files.length);
        return ResponseEntity.ok("uploaded file");
    }

    @GetMapping("/upload-files")
    public ResponseEntity<Map<String, Object>> getAllData() {
        Set<ImageEntity> images = imageService.findAllByOrderByIdDesc();
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("dataList", images);

        List<String> loadImages =  storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(HomeWebController.class,"serveFile",
                path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList());

        responseData.put("loadImages", loadImages);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/gallery-product/{id}")
    public ResponseEntity<List<Object[]>> getGalleryByProductId(@PathVariable("id") Integer id){
        List<Object[]> gallery = productService.findGalleryByProductId(id);
        return new ResponseEntity<>(gallery, HttpStatus.OK);
    }

    @PostMapping("/gallery-product")
    public ResponseEntity<?> updateGelleryByProductID(@RequestParam("files") MultipartFile[] files){

        Set<ImageEntity> images = new HashSet<>();
        for(MultipartFile file : files){
            ImageEntity img = new ImageEntity();
            img.setImageName(StringUtils.cleanPath(file.getOriginalFilename()));


            storageService.store(file);

            if (!imageService.existsByImageName(file.getOriginalFilename())) {
                // Tệp không tồn tại, lưu tệp và thêm tên tệp vào cơ sở dữ liệu
                ImageEntity image = new ImageEntity();

                image.setImageName(file.getOriginalFilename());
                img = imageService.saveImage(image);
                images.add(img);
            }else{
                img = imageService.findByImageName(StringUtils.cleanPath(file.getOriginalFilename()));
                if(img != null){
                    images.add(img);
                }
            }
        }

        this.logger.info("{} number of files uploaded", files.length);
        return ResponseEntity.ok(images);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
