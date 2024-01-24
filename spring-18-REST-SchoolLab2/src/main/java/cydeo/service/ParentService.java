package cydeo.service;


import cydeo.dto.ParentDTO;

import java.util.List;

public interface ParentService {

    List<ParentDTO> findAll();

    ParentDTO findById(Long id) throws Exception;

}