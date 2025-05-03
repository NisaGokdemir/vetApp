package org.codewhiskers.vetapp.common.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IGenericRestController<ReqDTO, ResDTO, ID> {

    ResponseEntity<ResDTO> create(@Valid ReqDTO dto);

    ResponseEntity<ResDTO> getById(ID id);

    ResponseEntity<Page<ResDTO>> getAll(Pageable pageable);

    ResponseEntity<ResDTO> update(ID id, @Valid ReqDTO dto);

    ResponseEntity<Void> delete(ID id);
}