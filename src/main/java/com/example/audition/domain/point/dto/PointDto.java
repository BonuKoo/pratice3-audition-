package com.example.audition.domain.point.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PointDto {
    private Long serial_no;
    private String artist_id;
    private String artist_name;
    private String artist_birth;
    private Integer point;
    private String grade;
    private String mento_name;
}