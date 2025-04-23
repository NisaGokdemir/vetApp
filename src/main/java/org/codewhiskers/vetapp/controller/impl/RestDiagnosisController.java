package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestDiagnosisController;
import org.codewhiskers.vetapp.dto.Diagnosis.request.DiagnosisRequestDTO;
import org.codewhiskers.vetapp.dto.Diagnosis.response.DiagnosisResponseDTO;
import org.codewhiskers.vetapp.service.IDiagnosisService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diagnosis")
public class RestDiagnosisController implements IRestDiagnosisController {

    private final IDiagnosisService diagnosisService;

    @GetMapping("/all")
    @Override
    public ResponseEntity<Page<DiagnosisResponseDTO>> getAllDiagnoses(Pageable pageable) {
        Page<DiagnosisResponseDTO> diagnoses = diagnosisService.getAllDiagnoses(pageable);
        if(!diagnoses.hasContent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(diagnoses);
    }

    @GetMapping("/find/{id}")
    @Override
    public ResponseEntity<DiagnosisResponseDTO> getDiagnosisById(@PathVariable Long id) {
        DiagnosisResponseDTO diagnosis = diagnosisService.getDiagnosisById(id);
        if(diagnosis == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diagnosis);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<DiagnosisResponseDTO> createDiagnosis(@RequestBody DiagnosisRequestDTO diagnosisRequestDTO) {
        DiagnosisResponseDTO diagnosis = diagnosisService.createDiagnosis(diagnosisRequestDTO);
        if(diagnosis == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(diagnosis);
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<DiagnosisResponseDTO> updateDiagnosis(@PathVariable Long id, @RequestBody DiagnosisRequestDTO diagnosisRequestDTO) {
        DiagnosisResponseDTO diagnosis = diagnosisService.updateDiagnosis(id, diagnosisRequestDTO);
        if(diagnosis == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diagnosis);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteDiagnosis(@PathVariable Long id) {
        DiagnosisResponseDTO diagnosis = diagnosisService.getDiagnosisById(id);
        if(diagnosis == null) {
            ResponseEntity.notFound().build();
        }
        diagnosisService.deleteDiagnosis(id);
        ResponseEntity.ok().build();
    }
}
