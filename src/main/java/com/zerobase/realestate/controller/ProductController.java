package com.zerobase.realestate.controller;

import com.zerobase.realestate.dto.ProductDto.request;
import com.zerobase.realestate.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  // 매물 등록
  @PostMapping(value = "/add")
  public ResponseEntity<Void> addProduct(@RequestPart request request,
      @RequestPart(value = "file", required = false) MultipartFile imageFile) {
    productService.addProduct(request, imageFile);
    return ResponseEntity.ok().build();
  }

  // 매물 수정
  @PutMapping(value = "/{productKey}")
  public ResponseEntity<Void> updateProduct(@PathVariable Long productKey, @RequestPart request request,
      @RequestPart(value = "file", required = false) MultipartFile imageFile) {
    productService.updateProduct(productKey, request, imageFile);
    return ResponseEntity.ok().build();
  }

  // 매물 삭제
  @DeleteMapping("/{productKey}")
  public ResponseEntity<Void> deleteGroup(@PathVariable Long productKey) {
    productService.deleteProduct(productKey);
    return ResponseEntity.ok().build();
  }

}
