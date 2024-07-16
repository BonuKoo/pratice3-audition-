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

    //오디션 등록
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
    
    //참가자 등수 조회
    public List<RankDto> getArtistRanks() {

        //1. artist list를 가져온다.
        List<Artist> artists = artistRepository.findAll();

        //2. point list를  가져온 다음
        Map<String, List<Point>> pointsMap = pointRepository
                //3. list에 담고
                .findAll()
                .stream()
                //4. 각 point를 artist_Id를 기준으로 그룹화하여, pointsMap에 저장한다.
                //artist_Id는 ? A001, A002, A003, A004, A005
                //5. artist_Id가 key, 아티스트에 대한 pointList를 값으로 가지게 되는 Map
                //그럼 각 A001에 해당하는 점수를 그룹으로 묶고서 Map에 담으면, Map은 순서를 갖지 않고 걍 담긴다.
                .collect(Collectors.groupingBy(point -> point.getArtist().getArtist_id()));



        List<RankDto> rankDtos = artists
                .stream().map(artist -> {
            //위에서 정의해 둔 pointsMap에는 artist의 Id에 값에 맞는 pointList가 그룹화 되어있다.
            //그 point 값을 Point를 갖는 List에 담는다. -> List 배열의 0번째, 1번째, 2번째에는 각각 Artist_id에 해당하는 point의 주소값이 담기게 될 것
            List<Point> points = pointsMap.get(artist.getArtist_id());
            //배열에는 int 값이 담겨있으므로, map에서 더하고서 변수 totalScore에 담는다.
            int totalScore = points.stream().mapToInt(Point::getPoint).sum();
            //만약 points 배열이 비어있으면, 0을 반환 (0 / 0 을 통해서 null값이나 오류를 예방), 0이 아니면 총합/points 길이
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
