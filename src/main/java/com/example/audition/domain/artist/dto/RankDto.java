package com.example.audition.domain.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class RankDto {

    private String artist_id;
    private String artist_name;
    private String artist_gender;
    private int total_score;
    private double average_score;
    private int rank;

}
