package com.devsuperior.bds04.resources;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/events")
public class EventResource {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<EventDTO> create(@Valid @RequestBody EventDTO eventDTO){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(eventDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(eventService.save(eventDTO));
    }

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAllPaged(Pageable pageable){
        return ResponseEntity.ok().body(eventService.findAllPaged(pageable));
    }
}
