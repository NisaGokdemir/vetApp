package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Species.request.SpeciesRequestDTO;
import org.codewhiskers.vetapp.dto.Species.response.SpeciesResponseDTO;
import org.codewhiskers.vetapp.entity.Species;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.SpeciesMapper;
import org.codewhiskers.vetapp.repository.SpeciesRepository;
import org.codewhiskers.vetapp.service.ISpeciesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpeciesServiceImpl implements ISpeciesService {

    private final SpeciesRepository speciesRepository;
    private final SpeciesMapper speciesMapper;

    private Species findSpeciesById(Long id) {
        return speciesRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Species ID: " + id))
                );
    }

    @Override
    public SpeciesResponseDTO createSpecies(SpeciesRequestDTO speciesRequestDTO) {
        Species species = speciesMapper.toSpeciesEntity(speciesRequestDTO);
        species = speciesRepository.save(species);
        return speciesMapper.toSpeciesResponseDto(species);
    }

    @Override
    public SpeciesResponseDTO getSpeciesById(Long id) {
        return speciesMapper.toSpeciesResponseDto(findSpeciesById(id));
    }

    @Override
    public Page<SpeciesResponseDTO> getAllSpecies(Pageable pageable) {
        Page<Species> page = speciesRepository.findAll(pageable);
        if (page.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND, "Species"));
        }
        return page.map(speciesMapper::toSpeciesResponseDto);
    }

    @Transactional
    @Override
    public SpeciesResponseDTO updateSpecies(Long id, SpeciesRequestDTO speciesRequestDTO) {
        Species species = findSpeciesById(id);
        speciesMapper.updateSpeciesEntity(speciesRequestDTO, species);
        species = speciesRepository.save(species);
        return speciesMapper.toSpeciesResponseDto(species);
    }

    @Transactional
    @Override
    public void deleteSpecies(Long id) {
        Species species = findSpeciesById(id); // kayıt var mı kontrolü burada
        speciesRepository.delete(species);
    }
}
