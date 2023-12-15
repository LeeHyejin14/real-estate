package com.zerobase.realestate.dto;

import com.zerobase.realestate.entity.Product;
import com.zerobase.realestate.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {

  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class request {
    private String name;
    private String information;
    private String address;
    private String latitude;
    private String longitude;
    private Type type;
    private int area;
    private int price;
    private String storageUrl;
  }

  @Getter
  public static class SearchResponse {
    private Long productKey;
    private String name;
    private String information;
    private String address;
    private String latitude;
    private String longitude;
    private Type type;
    private int area;
    private int price;
    private String storageUrl;

    public SearchResponse(Product product) {
      this.productKey = product.getProductKey();
      this.name = product.getName();
      this.information = product.getInformation();
      this.address = product.getAddress();
      this.latitude = product.getLatitude();
      this.longitude = product.getLongitude();
      this.type = product.getType();
      this.area = product.getArea();
      this.price = product.getPrice();
      this.storageUrl = product.getStorageUrl();
    }
  }

}
