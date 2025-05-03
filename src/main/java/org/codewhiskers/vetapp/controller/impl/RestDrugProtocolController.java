package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestDrugProtocolController;
import org.codewhiskers.vetapp.dto.DrugProtocol.request.DrugProtocolRequestDTO;
import org.codewhiskers.vetapp.dto.DrugProtocol.response.DrugProtocolResponseDTO;
import org.codewhiskers.vetapp.service.IDrugProtocolService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/drug-protocols")
@RequiredArgsConstructor
public class RestDrugProtocolController implements IRestDrugProtocolController {

    private final IDrugProtocolService service;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DrugProtocolResponseDTO> create(@Valid @RequestBody DrugProtocolRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDTO));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DrugProtocolResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<DrugProtocolResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DrugProtocolResponseDTO> update(@PathVariable Long id, @Valid @RequestBody DrugProtocolRequestDTO requestDTO) {
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