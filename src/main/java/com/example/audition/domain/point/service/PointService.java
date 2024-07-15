package com.example.audition.domain.point.service;

import com.example.audition.domain.point.Point;
import com.example.audition.domain.point.dto.PointDto;
import com.example.audition.domain.point.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    public List<PointDto> findAllPoints() {
        List<Point> points = pointRepository.findAll();
        return points.stream().map(point -> {
            String formattedBirth = formatBirthDate(point.getArtist().getArtist_birth());
            String grade = calculateGrade(point.getPoint());

            return new PointDto(
                    point.getSerial_no(),
                    point.getArtist().getArtist_id(),
                    point.getArtist().getArtist_name(),
                    formattedBirth,
                    point.getPoint(),
                    grade,
                    point.getMento().getMento_name()
            );
        }).collect(Collectors.toList());
    }

    private String formatBirthDate(String birth) {
        return String.format("%s년 %s월 %s일", birth.substring(0, 4), birth.substring(4, 6), birth.substring(6, 8));
    }

    private String calculateGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
}
