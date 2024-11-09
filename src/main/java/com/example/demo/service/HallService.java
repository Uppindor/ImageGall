package com.example.demo.service;


import com.example.demo.dao.HallRepository;
import com.example.demo.models.Hall;
import com.example.demo.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HallService {


    @Autowired
    private HallRepository hallRepository;

    public Hall getHall(Long id) throws IOException {
        return  hallRepository.getReferenceById(id);
    }

    public void saveHall(Hall hall) throws IOException {

        hallRepository.save(hall);
    }
    public List<Hall> getAll()
    {
        return hallRepository.findAll();
    }

    public void deleteHall(Long id) {hallRepository.deleteById(id);}
}
