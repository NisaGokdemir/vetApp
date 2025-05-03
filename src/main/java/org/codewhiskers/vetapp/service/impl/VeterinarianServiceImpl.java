package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.Veterinarian.request.VeterinarianRequestDTO;
import org.codewhiskers.vetapp.dto.Veterinarian.response.VeterinarianResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Specialization;
import org.codewhiskers.vetapp.entity.User;
import org.codewhiskers.vetapp.entity.Veterinarian;
import org.codewhiskers.vetapp.mapper.VeterinarianMapper;
import org.codewhiskers.vetapp.repository.ClinicRepository;
import org.codewhiskers.vetapp.repository.SpecializationRepository;
import org.codewhiskers.vetapp.repository.UserRepository;
import org.codewhiskers.vetapp.repository.VeterinarianRepository;
import org.codewhiskers.vetapp.service.IVeterinarianService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeterinarianServiceImpl implements IVeterinarianService {

    private final VeterinarianRepository veterinarianRepository;
    private final VeterinarianMapper veterinarianMapper;
    private final UserRepository userRepository;
    private final ClinicRepository clinicRepository;
    private final SpecializationRepository specializationRepository;

    private Veterinarian findVeterinarianById(Long id) {
        return veterinarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinarian not found"));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Clinic findClinicById(Long id) {
        return clinicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clinic not found"));
    }

    private Specialization findSpecializationById(Long id) {
        return specializationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialization not found"));
    }

    @Override
    public VeterinarianResponseDTO getVeterinarianById(Long id) {
        Veterinarian veterinarian = findVeterinarianById(id);
        return veterinarianMapper.toResponseDTO(veterinarian);
    }


    @Override
    public VeterinarianResponseDTO createVeterinarian(VeterinarianRequestDTO veterinarianRequestDTO) {
        if (veterinarianRequestDTO.getUserId() == null) {
            throw new RuntimeException("User ID boş olamaz");
        }

        User user = findUserById(veterinarianRequestDTO.getUserId());
        Clinic clinic = findClinicById(veterinarianRequestDTO.getClinicId());
        Specialization specialization = findSpecializationById(veterinarianRequestDTO.getSpecializationId());

        Veterinarian veterinarian = veterinarianMapper.toEntity(veterinarianRequestDTO);
        veterinarian.setUser(user);
        veterinarian.setClinic(clinic);
        veterinarian.setSpecialization(specialization);

        veterinarian = veterinarianRepository.save(veterinarian);
        return veterinarianMapper.toResponseDTO(veterinarian);
    }

    @Override
    public VeterinarianResponseDTO updateVeterinarian(Long id, VeterinarianRequestDTO veterinarianRequestDTO) {
        Veterinarian veterinarian = findVeterinarianById(id);
        User user = findUserById(veterinarianRequestDTO.getUserId());
        Clinic clinic = findClinicById(veterinarianRequestDTO.getClinicId());
        Specialization specialization = findSpecializationById(veterinarianRequestDTO.getSpecializationId());
        veterinarian.setClinic(clinic);
        veterinarian.setUser(user);
        veterinarian.setSpecialization(specialization);
        veterinarian = veterinarianRepository.save(veterinarian);
        return veterinarianMapper.toResponseDTO(veterinarian);
    }

    @Override
    public void deleteVeterinarian(Long id) {
        Veterinarian veterinarian = findVeterinarianById(id);
        veterinarianRepository.delete(veterinarian);
    }

    @Override
    public Page<VeterinarianResponseDTO> getAllVeterinarians(int page, int size) {
        Page<Veterinarian> veterinarians = veterinarianRepository.findAll(PageRequest.of(page, size));
        return veterinarians.map(veterinarianMapper::toResponseDTO);
    }
}
