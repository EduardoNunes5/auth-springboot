package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import com.devsuperior.bds04.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityService cityService;

    @Transactional
    public EventDTO save(EventDTO eventDTO){
        try {
            City city = cityService.getOne(eventDTO.getCityId());
            Event entity = new Event();
            entity.setDate(eventDTO.getDate());
            entity.setName(eventDTO.getName());
            entity.setUrl(eventDTO.getUrl());
            entity.setCity(city);
            entity = eventRepository.save(entity);
            return new EventDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPaged(Pageable pageable){
        return eventRepository.findAll(pageable)
                .map(EventDTO::new);
    }

}
