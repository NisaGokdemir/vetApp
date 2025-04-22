package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Breed.request.BreedRequestDTO;
import org.codewhiskers.vetapp.dto.Breed.response.BreedResponseDTO;
import org.codewhiskers.vetapp.entity.Breed;
import org.codewhiskers.vetapp.entity.Species;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.BreedMapper;
import org.codewhiskers.vetapp.repository.BreedRepository;
import org.codewhiskers.vetapp.repository.SpeciesRepository;
import org.codewhiskers.vetapp.service.IBreedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BreedServiceImpl implements IBreedService {

    private final BreedRepository breedRepository;
    private final SpeciesRepository speciesRepository;
    private final BreedMapper breedMapper;

    private Breed findBreedById(Long id) {
        return breedRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Breed ID: " + id))
                );
    }

    // TODO : speciesRepository kullanmaktansa, speciesService kullanarak speciesService.getSpeciesById(id) kullan.
    private Species findSpeciesById(Long id) {
        return speciesRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Species ID: " + id))
                );
    }

    @Transactional
    @Override
    public BreedResponseDTO createBreed(BreedRequestDTO breedRequestDTO) {
        Species species = findSpeciesById(breedRequestDTO.getSpeciesId());
        Breed breed = breedMapper.toBreedEntity(breedRequestDTO, species);
        breed = breedRepository.save(breed);
        return breedMapper.toBreedResponseDto(breed);
    }

    @Override
    public BreedResponseDTO getBreedById(Long id) {
        Breed breed = findBreedById(id);
        return breedMapper.toBreedResponseDto(breed);
    }

    @Override
    public Page<BreedResponseDTO> getAllBreeds(Pageable pageable) {
        Page<Breed> page = breedRepository.findAll(pageable);
        if (page.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND, "No breeds Found"));
        }
        return page.map(breedMapper::toBreedResponseDto);
    }

    @Transactional
    @Override
    public BreedResponseDTO updateBreed(Long id, BreedRequestDTO breedRequestDTO) {
        Breed breed = findBreedById(id);
        Species species = findSpeciesById(breedRequestDTO.getSpeciesId());
        breedMapper.updateBreedEntity(breedRequestDTO, breed, species);
        breed = breedRepository.save(breed);
        return breedMapper.toBreedResponseDto(breed);
    }

    @Transactional
    @Override
    public void deleteBreed(Long id) {
        Breed breed = findBreedById(id);
        breedRepository.delete(breed);
    }
}
