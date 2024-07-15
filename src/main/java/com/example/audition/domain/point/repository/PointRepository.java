package com.example.audition.domain.point.repository;

import com.example.audition.domain.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point,Long> {
}
