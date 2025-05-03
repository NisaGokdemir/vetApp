package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Disease.request.DiseaseRequestDTO;
import org.codewhiskers.vetapp.dto.Disease.response.DiseaseResponseDTO;
import org.codewhiskers.vetapp.entity.Disease;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DiseaseMapper {

    /**
     * Disease entity'sini DiseaseResponseDTO'ya dönüştürür
     * @param disease kaynak entity
     * @return response DTO
     */
    DiseaseResponseDTO diseaseToResponseDTO(Disease disease);
    
    /**
     * DiseaseRequestDTO'yu Disease entity'sine dönüştürür
     * @param diseaseRequestDTO kaynak request DTO
     * @return disease entity
     */
    Disease requestDTOToDisease(DiseaseRequestDTO diseaseRequestDTO);
    
    /**
     * DiseaseRequestDTO ile mevcut Disease entity'sini günceller
     * @param diseaseRequestDTO güncellenecek veriler
     * @param disease güncellenecek entity
     */
    void updateDiseaseFromRequestDTO(DiseaseRequestDTO diseaseRequestDTO, @MappingTarget Disease disease);
} 