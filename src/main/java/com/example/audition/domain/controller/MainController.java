package com.example.audition.domain.controller;

import com.example.audition.domain.artist.Artist;
import com.example.audition.domain.artist.dto.ArtistDTO;
import com.example.audition.domain.artist.dto.ArtistRegistForm;
import com.example.audition.domain.artist.service.ArtistService;
import com.example.audition.domain.point.dto.PointDto;
import com.example.audition.domain.point.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ArtistService artistService;
    @Autowired
    private PointService pointService;

    @GetMapping("/regist")
    public String registForm(Model model){

        model.addAttribute("form",new ArtistRegistForm());

        return "regist";
    }

    @PostMapping("/regist")
    public String registerArtist(ArtistRegistForm form){
        String artistBirth = form.getYear()+form.getMonth()+form.getDay();

        form.setArtist_birth(artistBirth);
        artistService.saveArtist(form);
        return "index";
    }

    @GetMapping("/artist")
    public String listArtists(Model model) {
        List<ArtistDTO> artists = artistService.findAllArtists();
        model.addAttribute("list", artists);
        return "artist";
    }

    @GetMapping("/mento")
    public String listMentos(Model model) {
        List<ArtistDTO> artists = artistService.findAllArtists();
        model.addAttribute("list", artists);
        return "mento";
    }

    @GetMapping("/point")
    public String listPoints(Model model) {
        List<PointDto> points = pointService.findAllPoints();
        model.addAttribute("list", points);
        return "point";
    }

}
