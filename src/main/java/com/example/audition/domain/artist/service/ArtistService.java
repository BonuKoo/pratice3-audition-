package com.example.audition.domain.artist.service;

import com.example.audition.domain.artist.Artist;
import com.example.audition.domain.artist.dto.ArtistDTO;
import com.example.audition.domain.artist.dto.ArtistRegistForm;
import com.example.audition.domain.artist.dto.RankDto;
import com.example.audition.domain.artist.repository.ArtistRepository;
import com.example.audition.domain.point.Point;
import com.example.audition.domain.point.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private PointRepository pointRepository;

    public void saveArtist(ArtistRegistForm form){

        Artist artist = new Artist(
                form.getArtist_id(),
                form.getArtist_name(),
                form.getArtist_gender(),
                form.getArtist_birth(),
                form.getTalent(),
                form.getAgency()
        );
        artistRepository.save(artist);
    }

    public List<ArtistDTO> findAllArtists() {
        return artistRepository.findAll()
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<RankDto> getArtistRanks() {
        List<Artist> artists = artistRepository.findAll();
        Map<String, List<Point>> pointsMap = pointRepository.findAll().stream()
                .collect(Collectors.groupingBy(point -> point.getArtist().getArtist_id()));

        List<RankDto> rankDtos = artists.stream().map(artist -> {
            List<Point> points = pointsMap.get(artist.getArtist_id());
            int totalScore = points.stream().mapToInt(Point::getPoint).sum();
            double averageScore = points.isEmpty() ? 0 : (double) totalScore / points.size();
            return new RankDto(
                    artist.getArtist_id(),
                    artist.getArtist_name(),
                    artist.getArtist_gender().equals("M") ? "남" : "여",
                    totalScore,
                    Math.round(averageScore * 100.0) / 100.0,
                    0
            );
        }).collect(Collectors.toList());

        rankDtos.sort(Comparator.comparingDouble(RankDto::getAverage_score).reversed());

        for (int i = 0; i < rankDtos.size(); i++) {
            rankDtos.get(i).setRank(i + 1);
        }

        return rankDtos;
    }

    private ArtistDTO convertToDTO(Artist artist) {
        String formattedBirth = String.format("%s년%s월%s일", artist.getArtist_birth().substring(0, 4), artist.getArtist_birth().substring(4, 6), artist.getArtist_birth().substring(6, 8));
        String gender = artist.getArtist_gender().equals("M") ? "남" : "여";
        String talent = switch (artist.getTalent()) {
            case "1" -> "댄스";
            case "2" -> "노래";
            case "3" -> "랩";
            default -> "";
        };
        return new ArtistDTO(
                artist.getArtist_id(),
                artist.getArtist_name(),
                formattedBirth,
                gender,
                talent,
                artist.getAgency()
        );
    }
}
