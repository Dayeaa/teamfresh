package com.example.demo.reparation.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reparation {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  
}
