package org.codewhiskers.vetapp.service.impl;

import org.codewhiskers.vetapp.common.service.GenericService;
import org.codewhiskers.vetapp.dto.Species.request.SpeciesRequestDTO;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.codewhiskers.vetapp.entity.Species;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.SpeciesMapper;
import org.codewhiskers.vetapp.repository.SpeciesRepository;
import org.codewhiskers.vetapp.service.ISpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpeciesServiceImpl extends GenericService<Species, Long, SpeciesRepository> implements ISpeciesService {

    private final SpeciesMapper speciesMapper;

    @Autowired
    public SpeciesServiceImpl(SpeciesRepository speciesRepository, SpeciesMapper speciesMapper) {
        super(speciesRepository);
        this.speciesMapper = speciesMapper;
    }

    @Override
    public SpeciesResponseDTO createSpecies(SpeciesRequestDTO speciesRequestDTO) {
        try {
            Species species = speciesMapper.toSpeciesEntity(speciesRequestDTO);
            species = create(species);
            return speciesMapper.toSpeciesResponseDto(species);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS, e.getMessage()));
        }
    }

    @Override
    public SpeciesResponseDTO getSpeciesById(Long id) {
        Species species = getById(id)
                .orElseThrow(() ->
                        new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Species ID: " + id)));
        return speciesMapper.toSpeciesResponseDto(species);
    }

    @Override
    public Page<SpeciesResponseDTO> getAllSpecies(Pageable pageable) {
        Page<Species> page = getAll(pageable);
        if (page.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND, "Species"));
        }
        return page.map(speciesMapper::toSpeciesResponseDto);
    }

    // @Transactional annotation'ını GenericService'ye koymamızın sebebi:
    // Spring AOP (Aspect-Oriented Programming), @Transactional gibi anotasyonları proxy üzerinden yönetir. Ancak bir sınıf kendi içindeki bir metodunu çağırırsa (yani self-invocation olursa), proxy araya giremez. Bu da @Transactional'ın devreye girmemesine neden olur.
    @Transactional
    @Override
    public SpeciesResponseDTO updateSpecies(Long id, SpeciesRequestDTO speciesRequestDTO) {
        try {
            Species species = speciesMapper.toSpeciesEntity(speciesRequestDTO);
            species.setId(id);
            species = update(id, species);
            return speciesMapper.toSpeciesResponseDto(species);
        } catch (IllegalArgumentException e) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Species ID: " + id));
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_UPDATE_UNSUCCESS, e.getMessage()));
        }
    }

/*
🎯 Sonuç:
Silme işlemleri küçük görünse de aslında:

Birden fazla veriye etki edebilir (cascade),

Lazy ilişkiler tetikleyebilir,

Hatalı durumda geri alınmaları gerekir.

Bu yüzden:

Silme işlemleri de transactional olmalı.
*/
    @Transactional
    @Override
    public void deleteSpecies(Long id) {
        try {
            delete(id);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_DELETE_UNSUCCESS, "Species ID: " + id));
        }
    }
}
