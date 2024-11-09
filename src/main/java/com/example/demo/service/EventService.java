package com.example.demo.service;


import com.example.demo.dao.EventRepository;
import com.example.demo.models.Event;
import com.example.demo.models.Hall;
import com.example.demo.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event getEvent(Long id) throws IOException {
        return  eventRepository.getReferenceById(id);
    }
    public void saveEvent(Event event) throws IOException {
        eventRepository.save(event);
    }

    public List<Event> getAll()
    {
        return eventRepository.findAll();
    }
    public void deleteEvent(Long id) {eventRepository.deleteById(id);}


}
