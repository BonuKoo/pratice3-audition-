package com.example.audition.domain.point;

import com.example.audition.domain.artist.Artist;
import com.example.audition.domain.mento.Mento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "tbl_point_01")
@Entity
public class Point {

    //채점번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Column(length = 8,nullable = false)
    private Long serial_no;

    //지원자번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id",referencedColumnName = "artist_id")
    private Artist artist;

    //멘토번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mento_id",referencedColumnName = "mento_id")
    private Mento mento;

    //점수
    @Column(length = 3)
    private Integer point;

}
