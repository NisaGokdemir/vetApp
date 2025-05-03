package org.codewhiskers.vetapp.mapper;

import org.codewhiskers.vetapp.dto.Medication.request.MedicationRequestDTO;
import org.codewhiskers.vetapp.dto.Medication.response.MedicationResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Drug;
import org.codewhiskers.vetapp.entity.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {DrugMapper.class, ClinicMapper.class})
public interface MedicationMapper {

    MedicationResponseDTO toDto(Medication medication);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "drug", source = "drug")
    @Mapping(target = "clinic", source = "clinic")
    Medication toEntity(MedicationRequestDTO dto, Drug drug, Clinic clinic);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "drug", source = "drug")
    @Mapping(target = "clinic", source = "clinic")
    void toUpdateEntity(MedicationRequestDTO dto, @MappingTarget Medication medication, Drug drug, Clinic clinic);
}
