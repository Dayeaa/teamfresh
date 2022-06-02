package com.example.demo.penalty.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.delivery.domain.Delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Penalty {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Delivery delivery;
  private String state;
  private Date updated_at;
  private int money;

  
  
}
