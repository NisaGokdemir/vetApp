package org.codewhiskers.vetapp.controller.impl;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestBloodTypeController;
import org.codewhiskers.vetapp.dto.bloodType.request.BloodTypeRequestDTO;
import org.codewhiskers.vetapp.dto.bloodType.response.BloodTypeResponseDTO;
import org.codewhiskers.vetapp.service.IBloodTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bloodTypes")
public class RestBloodTypeController implements IRestBloodTypeController {

    private final IBloodTypeService service;

    @PostMapping
    @Override
    public ResponseEntity<BloodTypeResponseDTO> createBloodType(@RequestBody @Valid BloodTypeRequestDTO bloodTypeRequestDTO) {
        BloodTypeResponseDTO response = service.createBloodType(bloodTypeRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<BloodTypeResponseDTO> getBloodTypeById(@PathVariable Long id) {
        BloodTypeResponseDTO response = service.getBloodTypeById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Override
    public ResponseEntity<Page<BloodTypeResponseDTO>> getAllBloodTypes(Pageable pageable) {
        Page<BloodTypeResponseDTO> response = service.getAllBloodTypes(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<BloodTypeResponseDTO> updateBloodType(@PathVariable Long id, @RequestBody @Valid BloodTypeRequestDTO bloodTypeRequestDTO) {
        BloodTypeResponseDTO response = service.updateBloodType(id, bloodTypeRequestDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteBloodType(@PathVariable Long id) {
        service.deleteBloodType(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
