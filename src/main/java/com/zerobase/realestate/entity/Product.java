package com.zerobase.realestate.entity;

import com.zerobase.realestate.enums.Type;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productKey;

  @ManyToOne
  private User user;

  private String name;

  private String information;

  private String address;

  private String latitude;

  private String longitude;

  @Enumerated(EnumType.STRING)
  private Type type;

  private int area;

  private int price;

  private String storageUrl;

  @CreatedDate
  private LocalDateTime registrationDate;

  private LocalDateTime revisedDate;

  private LocalDateTime deletionDate;

}
