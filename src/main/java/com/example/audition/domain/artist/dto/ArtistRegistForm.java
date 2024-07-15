package com.example.audition.domain.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ArtistRegistForm {
    
    //참가번호
    private String artist_id;
    //참가자명
    private String artist_name;
    
    //생년월일
    private String year;
    private String month;
    private String day;
    //성별
    private String artist_gender;
    //생년월일
    private String artist_birth;
    //특기
    private String talent;
    //소속사
    private String agency;

}
