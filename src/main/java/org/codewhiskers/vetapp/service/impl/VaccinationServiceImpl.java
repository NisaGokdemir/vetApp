package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Vaccination.request.VaccinationRequestDTO;
import org.codewhiskers.vetapp.dto.Vaccination.response.VaccinationResponseDTO;
import org.codewhiskers.vetapp.entity.Species;
import org.codewhiskers.vetapp.entity.Vaccination;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.VaccinationMapper;
import org.codewhiskers.vetapp.repository.VaccinationRepository;
import org.codewhiskers.vetapp.service.IVaccinationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VaccinationServiceImpl implements IVaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final VaccinationMapper vaccinationMapper;

    private Vaccination findVaccinationById(Long id) {
        return vaccinationRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Vaccination ID: " + id))
                );
    }

    @Override
    public VaccinationResponseDTO create(VaccinationRequestDTO vaccinationRequestDTO) {
        Vaccination vaccination = vaccinationMapper.toEntity(vaccinationRequestDTO);
        Vaccination savedVaccination = vaccinationRepository.save(vaccination);
        return vaccinationMapper.toDto(savedVaccination);
    }

    @Override
    public VaccinationResponseDTO getById(Long id) {
        Vaccination vaccination = findVaccinationById(id);
        return vaccinationMapper.toDto(vaccination);
    }

    @Override
    public Page<VaccinationResponseDTO> getAll(Pageable pageable) {
        return vaccinationRepository.findAll(pageable)
                .map(vaccinationMapper::toDto);
    }

    @Override
    public VaccinationResponseDTO update(Long id, VaccinationRequestDTO vaccinationRequestDTO) {
        Vaccination existingVaccination = findVaccinationById(id);
        vaccinationMapper.toUpdateEntity(vaccinationRequestDTO, existingVaccination);
        Vaccination updatedVaccination = vaccinationRepository.save(existingVaccination);
        return vaccinationMapper.toDto(updatedVaccination);
    }

    @Override
    public void delete(Long id) {
        Vaccination vaccination = findVaccinationById(id);
        vaccinationRepository.delete(vaccination);
    }
}
