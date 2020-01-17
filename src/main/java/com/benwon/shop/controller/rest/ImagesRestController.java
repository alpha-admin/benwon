package com.benwon.shop.controller.rest;

import com.benwon.shop.entity.ImagesUpload;
import com.benwon.shop.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/images")
public class ImagesRestController {

    private final ImageService imageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ImagesUpload create(@RequestParam(name = "uploadChannel", required = false) String uploadChannel,
                               @RequestParam(name = "file") MultipartFile multipartFile) throws IOException, IllegalAccessException {
        if (uploadChannel == null) {
            uploadChannel = "none";
        }
        ImagesUpload imagesUpload = imageService.create(uploadChannel, multipartFile);
        return imagesUpload;
    }
}
