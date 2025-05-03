package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestVaccinationController;
import org.codewhiskers.vetapp.dto.Vaccination.request.VaccinationRequestDTO;
import org.codewhiskers.vetapp.dto.Vaccination.response.VaccinationResponseDTO;
import org.codewhiskers.vetapp.service.IVaccinationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vaccinations")
@RequiredArgsConstructor
public class RestVaccinationController implements IRestVaccinationController {

    private final IVaccinationService service;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VaccinationResponseDTO> create(@Valid @RequestBody VaccinationRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDTO));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<VaccinationResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<VaccinationResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<VaccinationResponseDTO> update(@PathVariable Long id, @Valid @RequestBody VaccinationRequestDTO requestDTO) {
        return ResponseEntity.ok(service.update(id, requestDTO));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
