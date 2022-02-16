package com.devsuperior.bds04.resources;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityResource {

    @Autowired
    private CityService cityService;

    @PostMapping
    public ResponseEntity<CityDTO> create(@Valid @RequestBody CityDTO eventDTO){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(eventDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(cityService.save(eventDTO));
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAllPaged(Pageable pageable){
        return ResponseEntity.ok().body(cityService.findAllPaged(pageable));
    }
}
