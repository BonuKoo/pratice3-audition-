package com.example.audition.domain.artist;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "tbl_cust_01")
@Entity
public class Artist {
    //참가번호
    @Id @Column(nullable = false,length = 4)
    private String artist_id;
    //참가자명
    @Column(length = 20)
    private String artist_name;
    //성별
    @Column(length = 1)
    private String artist_gender;
    //생년월일
    @Column(length = 8)
    private String artist_birth;
    //특기
    @Column(length = 1)
    private String talent;
    //소속사
    @Column(length = 20)
    private String agency;

}
