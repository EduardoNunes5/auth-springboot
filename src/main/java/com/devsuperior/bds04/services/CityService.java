package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public CityDTO save(CityDTO cityDTO){
        City entity = new City();
        entity.setName(cityDTO.getName());
        entity = cityRepository.save(entity);
        return new CityDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<CityDTO> findAllPaged(Pageable pageable){
        PageRequest pr = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name"));
        return cityRepository.findAll(pr)
                .stream()
                .map(CityDTO::new)
                .collect(Collectors.toList());
    }

    public City getOne(Long cityId) {
        return cityRepository.getOne(cityId);
    }
}
