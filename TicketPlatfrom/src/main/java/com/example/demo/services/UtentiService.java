package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Utenti;
import com.example.demo.repository.UtentiRepository;

@Service
public class UtentiService {
    @Autowired
    private UtentiRepository utentiRepository;

    
    public List<Utenti> getAllUsers() {
        return utentiRepository.findAll();
    }
    
}

