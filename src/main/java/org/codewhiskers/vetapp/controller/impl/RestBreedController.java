package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestBreedController;
import org.codewhiskers.vetapp.dto.Breed.request.BreedRequestDTO;
import org.codewhiskers.vetapp.dto.Breed.response.BreedResponseDTO;
import org.codewhiskers.vetapp.service.IBreedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/breeds")
public class RestBreedController implements IRestBreedController {

    private final IBreedService breedService;

    @Override
    @PostMapping
    public ResponseEntity<BreedResponseDTO> createBreed(@RequestBody @Valid BreedRequestDTO breedRequestDTO) {
        BreedResponseDTO response = breedService.createBreed(breedRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BreedResponseDTO> getBreedById(@PathVariable Long id) {
        BreedResponseDTO response = breedService.getBreedById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<BreedResponseDTO>> getAllBreeds(Pageable pageable) {
        Page<BreedResponseDTO> response = breedService.getAllBreeds(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<BreedResponseDTO> updateBreed(@PathVariable Long id,
                                                        @RequestBody @Valid BreedRequestDTO breedRequestDTO) {
        BreedResponseDTO response = breedService.updateBreed(id, breedRequestDTO);
        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBreed(@PathVariable Long id) {
        breedService.deleteBreed(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
