package com.example.audition.domain.artist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDTO {
    private String artist_id;
    private String artist_name;
    private String artist_birth;
    private String artist_gender;
    private String talent;
    private String agency;
}