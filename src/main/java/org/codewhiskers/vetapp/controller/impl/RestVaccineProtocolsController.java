package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestVaccineProtocolsController;
import org.codewhiskers.vetapp.dto.VaccineProtocols.request.VaccineProtocolsRequestDTO;
import org.codewhiskers.vetapp.dto.VaccineProtocols.response.VaccineProtocolsResponseDTO;
import org.codewhiskers.vetapp.service.IVaccineProtocolsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vaccine-protocols")
@RequiredArgsConstructor
public class RestVaccineProtocolsController implements IRestVaccineProtocolsController {

    private final IVaccineProtocolsService service;

    @PostMapping
    @Override
    public ResponseEntity<VaccineProtocolsResponseDTO> create(@RequestBody @Valid VaccineProtocolsRequestDTO requestDTO) {
        VaccineProtocolsResponseDTO createdProtocol = service.create(requestDTO);
        return new ResponseEntity<>(createdProtocol, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<VaccineProtocolsResponseDTO> getById(@PathVariable Long id) {
        VaccineProtocolsResponseDTO protocol = service.getById(id);
        return ResponseEntity.ok(protocol);
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<VaccineProtocolsResponseDTO>> getAll(Pageable pageable) {
        Page<VaccineProtocolsResponseDTO> protocols = service.getAll(pageable);
        return ResponseEntity.ok(protocols);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<VaccineProtocolsResponseDTO> update(@PathVariable Long id, @RequestBody @Valid VaccineProtocolsRequestDTO requestDTO) {
        VaccineProtocolsResponseDTO updatedProtocol = service.update(id, requestDTO);
        return ResponseEntity.ok(updatedProtocol);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
} 