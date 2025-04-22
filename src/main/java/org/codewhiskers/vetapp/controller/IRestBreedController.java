package org.codewhiskers.vetapp.controller;

import jakarta.validation.Valid;
import org.codewhiskers.vetapp.dto.Breed.request.BreedRequestDTO;
import org.codewhiskers.vetapp.dto.Breed.response.BreedResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface IRestBreedController {

    ResponseEntity<BreedResponseDTO> createBreed(@Valid BreedRequestDTO breedRequestDTO);

    ResponseEntity<BreedResponseDTO> getBreedById(Long id);

    ResponseEntity<Page<BreedResponseDTO>> getAllBreeds(Pageable pageable);

    ResponseEntity<BreedResponseDTO> updateBreed(Long id, @Valid BreedRequestDTO breedRequestDTO);

    ResponseEntity<Void> deleteBreed(Long id);
}
