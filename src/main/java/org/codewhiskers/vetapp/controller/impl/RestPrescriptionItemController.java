package org.codewhiskers.vetapp.controller.impl;


import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestPrescriptionItemController;
import org.codewhiskers.vetapp.dto.PrescriptionItem.request.PrescriptionItemRequestDTO;
import org.codewhiskers.vetapp.dto.PrescriptionItem.response.PrescriptionItemResponseDTO;
import org.codewhiskers.vetapp.entity.PrescriptionItem;
import org.codewhiskers.vetapp.service.IPrescriptionItemService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prescription-items")
public class RestPrescriptionItemController implements IRestPrescriptionItemController {

    private final IPrescriptionItemService prescriptionItemService;

    @GetMapping("/all")
    @Override
    public ResponseEntity<Page<PrescriptionItemResponseDTO>> getAllPrescriptionItems(Pageable pageable) {
        Page<PrescriptionItemResponseDTO> page = prescriptionItemService.getAllPrescriptionItems(pageable);
        if (page.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<PrescriptionItemResponseDTO> getPrescriptionItemById(@PathVariable Long id) {
        PrescriptionItemResponseDTO prescriptionItem = prescriptionItemService.getPrescriptionItemById(id);
        if (prescriptionItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prescriptionItem);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<PrescriptionItemResponseDTO> createPrescriptionItem(@RequestBody PrescriptionItemRequestDTO dto) {
        PrescriptionItemResponseDTO createdPrescriptionItem = prescriptionItemService.createPrescriptionItem(dto);
        if (createdPrescriptionItem == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdPrescriptionItem);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<PrescriptionItemResponseDTO> updatePrescriptionItem(@PathVariable Long id, @RequestBody PrescriptionItemRequestDTO dto) {
        PrescriptionItemResponseDTO updatedPrescriptionItem = prescriptionItemService.updatePrescriptionItem(id, dto);
        if (updatedPrescriptionItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPrescriptionItem);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deletePrescriptionItem(@PathVariable Long id) {
        prescriptionItemService.deletePrescriptionItem(id);
        ResponseEntity.ok().build();
    }

    @GetMapping("/prescription/{prescriptionId}")
    @Override
    public ResponseEntity<Page<PrescriptionItem>> findByPrescriptionId(@PathVariable Long prescriptionId, Pageable pageable) {
        Page<PrescriptionItem> page = prescriptionItemService.findByPrescriptionId(prescriptionId, pageable);
        if (page.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(page);
    }
}
