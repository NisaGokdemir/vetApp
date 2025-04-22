package org.codewhiskers.vetapp.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestOwnerController;
import org.codewhiskers.vetapp.dto.Owner.request.OwnerRequestDTO;
import org.codewhiskers.vetapp.dto.Owner.response.OwnerResponseDTO;
import org.codewhiskers.vetapp.service.IOwnerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/owners")
public class RestOwnerController implements IRestOwnerController {

    private final IOwnerService ownerService;

    @Override
    @PostMapping
    public ResponseEntity<OwnerResponseDTO> createOwner(@RequestBody @Valid OwnerRequestDTO ownerRequestDTO) {
        OwnerResponseDTO response = ownerService.createOwner(ownerRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<OwnerResponseDTO> getOwnerById(@PathVariable Long id) {
        OwnerResponseDTO response = ownerService.getOwnerById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<OwnerResponseDTO>> getAllOwners(Pageable pageable) {
        Page<OwnerResponseDTO> page = ownerService.getAllOwners(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<OwnerResponseDTO> updateOwner(@PathVariable Long id,
                                                        @RequestBody @Valid OwnerRequestDTO ownerRequestDTO) {
        OwnerResponseDTO response = ownerService.updateOwner(id, ownerRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
