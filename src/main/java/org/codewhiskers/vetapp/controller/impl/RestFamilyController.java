package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestFamilyController;
import org.codewhiskers.vetapp.dto.Family.request.FamilyRequestDTO;
import org.codewhiskers.vetapp.dto.Family.response.FamilyResponseDTO;
import org.codewhiskers.vetapp.service.IFamilyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/families")
@RequiredArgsConstructor
public class RestFamilyController implements IRestFamilyController {

    private final IFamilyService familyService;


    @PostMapping("/create")
    @Override
    public ResponseEntity<FamilyResponseDTO> createFamily(@RequestBody FamilyRequestDTO familyRequestDTO) {
        FamilyResponseDTO familyResponseDTO = familyService.createFamily(familyRequestDTO);
        if (familyResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(familyResponseDTO);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<FamilyResponseDTO> getFamilyById(@PathVariable Long id) {
        FamilyResponseDTO familyResponseDTO = familyService.getFamilyById(id);
        if (familyResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(familyResponseDTO);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<FamilyResponseDTO> updateFamily(@PathVariable Long id, @RequestBody FamilyRequestDTO familyRequestDTO) {
        FamilyResponseDTO familyResponseDTO = familyService.updateFamily(id, familyRequestDTO);
        if (familyResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(familyResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Override
    public void deleteFamily(@PathVariable Long id) {
        familyService.deleteFamily(id);
        if (familyService.getFamilyById(id) != null) {
            ResponseEntity.notFound().build();
        }
        ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    @Override
    public ResponseEntity<Page<FamilyResponseDTO>> getAllFamilies(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        Page<FamilyResponseDTO> familyResponseDTOPage = familyService.getAllFamilies(page, size);
        if (familyResponseDTOPage == null || !familyResponseDTOPage.hasContent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(familyResponseDTOPage);
    }
}
