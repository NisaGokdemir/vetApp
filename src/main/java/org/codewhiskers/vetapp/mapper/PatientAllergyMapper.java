package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.PatientAllergy.request.PatientAllergyRequestDTO;
import org.codewhiskers.vetapp.dto.PatientAllergy.response.PatientAllergyResponseDTO;
import org.codewhiskers.vetapp.entity.PatientAllergy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface PatientAllergyMapper {
    
    /**
     * PatientAllergy entity'sini response DTO'ya dönüştürür
     * @param patientAllergy kaynak entity
     * @return dönüştürülmüş DTO
     */
    PatientAllergyResponseDTO patientAllergyToResponseDTO(PatientAllergy patientAllergy);
    
    /**
     * Request DTO'yu PatientAllergy entity'sine dönüştürür
     * @param patientAllergyRequestDTO kaynak DTO
     * @return dönüştürülmüş entity
     */
    PatientAllergy requestDTOToPatientAllergy(PatientAllergyRequestDTO patientAllergyRequestDTO);
    
    /**
     * Request DTO'dan gelen verilerle var olan PatientAllergy entity'sini günceller
     * @param patientAllergyRequestDTO kaynak DTO
     * @param patientAllergy güncellenen entity
     */
    void updatePatientAllergyFromRequestDTO(PatientAllergyRequestDTO patientAllergyRequestDTO, @MappingTarget PatientAllergy patientAllergy);
}
