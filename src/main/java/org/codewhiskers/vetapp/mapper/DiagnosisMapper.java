package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Diagnosis.request.DiagnosisRequestDTO;
import org.codewhiskers.vetapp.dto.Diagnosis.response.DiagnosisResponseDTO;
import org.codewhiskers.vetapp.entity.Diagnosis;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {VeterinarianMapper.class, PatientMapper.class, ClinicMapper.class, DiseaseMapper.class})
public interface DiagnosisMapper {

    Diagnosis requestDTOToDiagnosis(DiagnosisRequestDTO diagnosis);
    DiagnosisResponseDTO diagnosisToResponseDTO(Diagnosis diagnosis);
    void updateDiagnosisFromRequestDTO(DiagnosisRequestDTO diagnosisRequestDTO, @MappingTarget Diagnosis diagnosis);
}
