package com.example.audition.domain.controller;

import com.example.audition.domain.artist.dto.ArtistDTO;
import com.example.audition.domain.artist.dto.ArtistRegistForm;
import com.example.audition.domain.artist.dto.RankDto;
import com.example.audition.domain.artist.service.ArtistService;
import com.example.audition.domain.point.dto.MentoDTO;
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

    //오디션등록
    @GetMapping("/regist")
    public String registForm(Model model){

        model.addAttribute("form",new ArtistRegistForm());

        return "regist";
    }

    @PostMapping("/regist")
    public String registerArtist(ArtistRegistForm form){

        String artistBirth = form.getYear()+form.getMonth()+form.getDay();

        //form.setArtist_birth(form.getYear()+form.getMonth()+form.getDay());

        artistService.saveArtist(form);
        return "index";
    }
    //참가자목록조회
    @GetMapping("/artist")
    public String listArtists(Model model) {
        List<ArtistDTO> artists = artistService.findAllArtists();
        model.addAttribute("list", artists);
        return "artist";
    }
    //멘토접수조회
    @GetMapping("/mento")
    public String listMentos(Model model) {
        List<MentoDTO> mentos = pointService.findAllPoints();
        model.addAttribute("list", mentos);
        return "mento";
    }

    //참가자등수조회
    @GetMapping("/point")
    public String listPoints(Model model) {
        List<RankDto> points = artistService.getArtistRanks();
        model.addAttribute("list", points);
        return "point";
    }

}
