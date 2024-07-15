package com.example.audition.domain.mento.service;

import com.example.audition.domain.mento.repository.MentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentoService {

    @Autowired
    private MentoRepository mentoRepository;


}
