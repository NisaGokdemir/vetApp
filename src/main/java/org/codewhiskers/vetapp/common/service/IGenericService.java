package org.codewhiskers.vetapp.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGenericService<ReqDTO, ResDTO, ID> {

    ResDTO create(ReqDTO dto);

    ResDTO getById(ID id);

    Page<ResDTO> getAll(Pageable pageable);

    ResDTO update(ID id, ReqDTO dto);

    void delete(ID id);
}