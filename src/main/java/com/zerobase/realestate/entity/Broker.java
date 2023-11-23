package com.zerobase.realestate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Broker {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long brokerKey;

  private String id;

  private String password;

  private String name;

  private String phoneNumber;

  private String address;

}