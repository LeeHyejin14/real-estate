package com.zerobase.realestate.service;

import com.zerobase.realestate.dto.ProductDto.request;
import com.zerobase.realestate.entity.Product;
import com.zerobase.realestate.repository.ProductRepository;
import com.zerobase.realestate.util.RandomStringMaker;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;
  private final S3Uploader s3Uploader;
  private final String s3BucketFolderName = "product-images/";

  public void addProduct(request request, MultipartFile imageFile) {

    Product product = Product.builder()
        .name(request.getName())
        .information(request.getInformation())
        .address(request.getAddress())
        .latitude(request.getLatitude())
        .longitude(request.getLongitude())
        .type(request.getType())
        .area(request.getArea())
        .price(request.getPrice())
        .build();

    uploadImage(product, imageFile);

    productRepository.save(product);
  }

  public void updateProduct(Long productKey, request request, MultipartFile imageFile) {
    Product product = productRepository.findById(productKey)
        .orElseThrow(() -> new RuntimeException("해당 매물이 존재하지 않습니다."));

    if (product.getDeletionDate() != null) {
      throw new RuntimeException("해당 매물은 삭제되었습니다.");
    }

    product.setName(request.getName());
    product.setInformation(request.getInformation());
    product.setAddress(request.getAddress());
    product.setLatitude(request.getLatitude());
    product.setLongitude(request.getLongitude());
    product.setType(request.getType());
    product.setArea(request.getArea());
    product.setPrice(request.getPrice());

    uploadImage(product, imageFile);

    productRepository.save(product);
  }

  @Transactional
  public void deleteProduct(Long productKey) {
    Product product = productRepository.findById(productKey)
        .orElseThrow(() -> new RuntimeException("해당 매물이 존재하지 않습니다."));

    if (product.getDeletionDate() != null) {
      throw new RuntimeException("삭제된 매물입니다.");
    }

    product.setDeletionDate(LocalDateTime.now());

    productRepository.save(product);
  }

  private void uploadImage(Product product, MultipartFile imageFile){
    try{
      product.setStorageUrl(
          s3Uploader.uploadAndGenerateUrl(
              imageFile,
              s3BucketFolderName +
                  RandomStringMaker.randomStringMaker())
      );
    } catch (IOException e){
      throw new RuntimeException("이미지를 업로드하는데 실패했습니다.");
    }
  }

}
