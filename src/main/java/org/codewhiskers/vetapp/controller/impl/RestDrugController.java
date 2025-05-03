package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestDrugController;
import org.codewhiskers.vetapp.dto.Drug.request.DrugRequestDTO;
import org.codewhiskers.vetapp.dto.Drug.response.DrugResponseDTO;
import org.codewhiskers.vetapp.service.IDrugService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/drugs")
public class RestDrugController implements IRestDrugController {

    private final IDrugService service;

    @PostMapping
    @Override
    public ResponseEntity<DrugResponseDTO> create(@RequestBody @Valid DrugRequestDTO drugRequestDTO) {
        DrugResponseDTO createdDrug = service.create(drugRequestDTO);
        return new ResponseEntity<>(createdDrug, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<DrugResponseDTO> getById(@PathVariable Long id) {
        DrugResponseDTO drug = service.getById(id);
        return ResponseEntity.ok(drug);
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<DrugResponseDTO>> getAll(Pageable pageable) {
        Page<DrugResponseDTO> drugs = service.getAll(pageable);
        return ResponseEntity.ok(drugs);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<DrugResponseDTO> update(@PathVariable Long id, @RequestBody @Valid DrugRequestDTO drugRequestDTO) {
        DrugResponseDTO updatedDrug = service.update(id, drugRequestDTO);
        return ResponseEntity.ok(updatedDrug);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
} 