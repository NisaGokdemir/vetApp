package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestVaccineListController;
import org.codewhiskers.vetapp.dto.VaccineList.request.VaccineListRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineList.response.VaccineListResponseDTO;
import org.codewhiskers.vetapp.service.IVaccineListService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vaccine-lists")
@RequiredArgsConstructor
public class RestVaccineListController implements IRestVaccineListController {

    private final IVaccineListService service;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VaccineListResponseDTO> create(@Valid @RequestBody VaccineListRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDTO));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<VaccineListResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<VaccineListResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<VaccineListResponseDTO> update(@PathVariable Long id, @Valid @RequestBody VaccineListRequestDTO requestDTO) {
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