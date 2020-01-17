package com.benwon.shop.service;

import com.benwon.shop.configuration.properties.BenwonProperties;
import com.benwon.shop.entity.ImagesUpload;
import com.benwon.shop.repository.ImagesUploadRepository;
import com.google.cloud.storage.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.downgoon.snowflake.Snowflake;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final Snowflake snowflake;
    private final BenwonProperties benwonProperties;
    private final Storage storage;
    private final ImagesUploadRepository imagesUploadRepository;

    public ImagesUpload create(String uploadChannel, MultipartFile multipartFile) throws IOException, IllegalAccessException {
        String contentType = multipartFile.getContentType();
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        Long imageID = snowflake.nextId();
        String fileExt = this.getFileExtension(multipartFile.getOriginalFilename());
        String bucketName = benwonProperties.getGcp().getBucket().getProduct();
        String bucketUrlPrefix = benwonProperties.getGcp().getBucketUrlPrefix();
        // 先儲存原始檔
        ImagesUpload imagesUpload_original = this.create(imageID, bucketName, imageID + fileExt + "/org",
                1, 0, null, uploadChannel, 0, bufferedImage.getWidth(), contentType,
                bufferedImage, bucketUrlPrefix);
        // 建立預設圖片(手機大小)
        BufferedImage bufferedImage_default = this.resize(bufferedImage, benwonProperties.getImagesDefaultSize());
        ImagesUpload imagesUpload_default = this.create(snowflake.nextId(), bucketName, imageID + fileExt,
                0, 1, imageID, uploadChannel, 1, bufferedImage_default.getWidth(), contentType,
                bufferedImage_default, bucketUrlPrefix);
        // 建立不同大小
        for (int i = 0; i < benwonProperties.getImagesSize().size(); i++) {
            Integer imageWidth = benwonProperties.getImagesSize().get(i);
            BufferedImage bufferedImage_resize = this.resize(bufferedImage, imageWidth);
            ImagesUpload imagesUpload_resize = this.create(snowflake.nextId(), bucketName, imageID + fileExt + "/" + (i + 2) + "x",
                    0, 0, imageID, uploadChannel, i + 2, bufferedImage_resize.getWidth(), contentType,
                    bufferedImage_resize, bucketUrlPrefix);
        }
        return imagesUpload_default;
    }


    public BufferedImage resize(BufferedImage inputImage, Integer fixWidth) {
//        log.info("Width=" + inputImage.getWidth() + ", Height=" + inputImage.getHeight() + ", fixWidth=" + fixWidth);
        Double percent = (double) fixWidth / (double) inputImage.getWidth();
//        log.info("percent=" + percent);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        Image tmp = inputImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

//    public void createFlux(String uploadChannel, FilePart filePart) throws IOException, IllegalAccessException {
//        String contentType = filePart.headers().getContentType().toString();
//        Path tempFile = Files.createTempFile("Image", filePart.filename());
//        filePart.transferTo(tempFile.toFile());
//        byte[] bytesData = Files.readAllBytes(tempFile);
//        Long imageID = snowflake.nextId();
//        String fileName = imageID + this.getFileExtension(filePart.filename());
//        String resourceUrl = this.create(benwonProperties.getGcp().getBucket().getProduct(), fileName, contentType, bytesData, benwonProperties.getGcp().getBucketUrlPrefix());
//        ImagesUpload imagesUpload = new ImagesUpload();
//        imagesUpload.setId(imageID);
//        imagesUpload.setBucketName(benwonProperties.getGcp().getBucket().getProduct());
//        imagesUpload.setFileName(fileName);
//        imagesUpload.setResourceType(0);
//        imagesUpload.setResourceUrl(resourceUrl);
//        imagesUpload.setUploadChannel(uploadChannel);
//        imagesUpload.setImageSize(Long.valueOf(bytesData.length));
//        imagesUpload.setCreatedBy("");
//        imagesUpload.setCreatedDate(LocalDateTime.now());
//        imagesUpload.setLastModifiedBy("");
//        imagesUpload.setLastModifiedDate(LocalDateTime.now());
//        imagesUploadRepository.save(imagesUpload);
//
//    }

    private String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return "." + fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    private ImagesUpload create(Long imageID, String bucketName, String fileName, Integer isOriginal, Integer isDefaultSize, Long refImageId,
                                String uploadChannel, Integer imageResizeDef, Integer imageWidth, String contentType, BufferedImage bufferedImage,
                                String bucketUrlPrefix) throws IllegalAccessException, IOException {
        String resourceUrl = null;
        List<Acl> acls = new ArrayList<>();
        //acls.add(Acl.of(Acl.User.ofAllAuthenticatedUsers(), Acl.Role.OWNER));
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        //檢查檔案存在
        BlobId blobId = BlobId.of(bucketName, fileName);
        Blob blob = this.storage.get(blobId);
        if (blob != null) {
            System.err.println("檔案 " + fileName + " 已存在");
            throw new IllegalAccessException("檔案已存在");
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);

        blob = storage.create(
                BlobInfo.newBuilder(bucketName, fileName).setAcl(acls).setContentType(contentType).setCacheControl("public, max-age=86400").build(),
                baos.toByteArray());
        resourceUrl = String.format("%s/%s/%s", bucketUrlPrefix, bucketName, fileName);
        // blob.getSelfLink(); => https://www.googleapis.com/storage/v1/b/benwon-product-images-dev/o/test2.jpg
        // blob.getMediaLink(); => https://www.googleapis.com/download/storage/v1/b/benwon-product-images-dev/o/test2.jpg?generation=1561360536124715&alt=media
        // resourceUrl => https://storage.googleapis.com/benwon-product-images-dev/test2.jpg
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        ImagesUpload imagesUpload = new ImagesUpload();
        imagesUpload.setId(imageID);
        imagesUpload.setBucketName(bucketName);
        imagesUpload.setFileName(fileName);
        imagesUpload.setIsOriginal(isOriginal);
        imagesUpload.setIsDefaultSize(isDefaultSize);
        imagesUpload.setRefImageId(refImageId);
        imagesUpload.setUploadChannel(uploadChannel);
        imagesUpload.setImageResizeDef(imageResizeDef);
        imagesUpload.setImageWidth(imageWidth);
        imagesUpload.setImageSizeKb(Double.valueOf(decimalFormat.format(baos.size() / 1024.0)));
        imagesUpload.setResourceUrl(resourceUrl);
        imagesUpload.setCreatedBy("");
        imagesUpload.setCreatedDate(LocalDateTime.now());
        imagesUpload.setLastModifiedBy("");
        imagesUpload.setLastModifiedDate(LocalDateTime.now());
        imagesUploadRepository.save(imagesUpload);
        return imagesUpload;
    }
}
