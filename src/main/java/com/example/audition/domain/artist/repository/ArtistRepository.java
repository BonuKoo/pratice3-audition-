package com.example.audition.domain.artist.repository;

import com.example.audition.domain.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,String > {
}
