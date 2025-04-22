package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.Breed.request.BreedRequestDTO;
import org.codewhiskers.vetapp.dto.Breed.response.BreedResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBreedService {

    BreedResponseDTO createBreed(BreedRequestDTO breedRequestDTO);

    BreedResponseDTO getBreedById(Long id);

    Page<BreedResponseDTO> getAllBreeds(Pageable pageable);

    BreedResponseDTO updateBreed(Long id, BreedRequestDTO breedRequestDTO);

    void deleteBreed(Long id);
}
