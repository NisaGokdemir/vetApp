package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Clinic.request.ClinicRequestDTO;
import org.codewhiskers.vetapp.dto.Clinic.response.ClinicResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.mapper.ClinicMapper;
import org.codewhiskers.vetapp.repository.ClinicRepository;
import org.codewhiskers.vetapp.service.IClinicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements IClinicService {

    private final ClinicRepository clinicRepository;

    private final ClinicMapper clinicMapper;

    private Clinic findClinicById(Long id) {
        return clinicRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "ID: " + id))
                );
    }

    @Override
    public Page<ClinicResponseDTO> getAllClinics(int page, int size) {
        Page<Clinic> clinics = clinicRepository.findAll(PageRequest.of(page, size));
        if (!clinics.hasContent()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORDS_NOT_FOUND, ""));
        }
        return clinics.map(clinicMapper::clinicToResponseDTO);
    }

    @Override
    public ClinicResponseDTO getClinicById(Long id) {
        Clinic clinic = findClinicById(id);
        return clinicMapper.clinicToResponseDTO(clinic);
    }

    @Override
    public ClinicResponseDTO createClinic(ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic = clinicMapper.toClinicEntity(clinicRequestDTO);
        if (clinic.getName() == null || clinic.getName().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_CREATE_UNSUCCESS, ""));
        }
        clinic = clinicRepository.save(clinic);
        return clinicMapper.clinicToResponseDTO(clinic);
    }

    @Override
    public ClinicResponseDTO updateClinic(Long id, ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic = findClinicById(id);
        clinicMapper.updateClinicEntity(clinicRequestDTO, clinic);
        clinic = clinicRepository.save(clinic);
        if (clinic.getName() == null || clinic.getName().isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_UPDATE_UNSUCCESS, id.toString()));
        }
        return clinicMapper.clinicToResponseDTO(clinic);
    }

    @Override
    public void deleteClinic(Long id) {
        Clinic clinic = findClinicById(id); // Kayıt var mı kontrolü
        clinicRepository.delete(clinic); // Silme işlemi
    }
}
