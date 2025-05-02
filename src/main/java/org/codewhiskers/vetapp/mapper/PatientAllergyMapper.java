package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Patient.request.PatientRequestDTO;
import org.codewhiskers.vetapp.dto.Patient.response.PatientResponseDTO;
import org.codewhiskers.vetapp.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = {PatientMapper.class})
public interface PatientAllergyMapper {
    PatientResponseDTO patientToResponseDTO(Patient patient);
    Patient toPatientEntity(PatientRequestDTO patientRequestDTO);
    void updatePatientFromRequestDTO(PatientRequestDTO patientRequestDTO, @MappingTarget Patient patient);
}
