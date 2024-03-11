package kr.ezen.yni_project.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_no")
    private int no; // 회원 일련 번호

    @Column(nullable = false, length = 50)
    private String shelName; //보호소 이름

    @Column(nullable = false)
    private String shelArea; //지역

    @Column(length = 100)
    private String petSpecies; // 동물 종 분류

    @Column(nullable = false)
    private String petName; //견/묘종

    @Column(nullable = false, length = 100)
    private String petAge; // 펫나이

    @Column(nullable = false)
    private String petGender; //펫 성별

    @Column(nullable = false)
    private String petNeutering; // 중성화 여부

    @Column(nullable = false)
    private String petInoculation; //접종 여부

    @Column(nullable = false)
    private String petPlace; //발견된 장소

    @Column(nullable = false)
    private String petRemarks; //특이사항

    @Column(nullable = false)
    private String petImage; //이미지

    @Column(nullable = false)
    private String petAdopt;

    @CreationTimestamp
    private Timestamp petSysdate; // 등록날짜

    @UpdateTimestamp
    private Timestamp petUpdate; // 업데이트 날짜










}
