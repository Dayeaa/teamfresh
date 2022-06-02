package com.example.demo.voc.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.demo.penalty.domain.Penalty;
import com.example.demo.reparation.domain.Reparation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Voc {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Date created_at;

  private String imputation;
  private String voc_detail;
  private String imputation_detail;


  //관계설정
  @OneToOne //하나의 VOC에 하나의 배상
  @JoinColumn(name = "reparation_id")
  private Reparation reparation;
  
  @OneToMany //하나의 VOC에 여러 패널티
  @JoinColumn(name = "penalty_id")
  private Penalty penalty;

  @Builder
  public Voc(Long id, Reparation reparation, Penalty penalty) {
    this.id = id;
    this.reparation = reparation;
    this.penalty = penalty;
  }

  
}
