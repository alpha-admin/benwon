package com.benwon.shop.repository;

import com.benwon.shop.entity.ImagesUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ImagesUploadRepository extends JpaRepository<ImagesUpload, Long> {
}
