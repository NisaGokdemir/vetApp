package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.dto.RadiologicImage.request.RadiologicImageRequestDTO;
import org.codewhiskers.vetapp.dto.RadiologicImage.response.RadiologicImageResponseDTO;
import org.codewhiskers.vetapp.entity.Clinic;
import org.codewhiskers.vetapp.entity.Patient;
import org.codewhiskers.vetapp.entity.RadiologicImage;
import org.codewhiskers.vetapp.mapper.RadiologicImageMapper;
import org.codewhiskers.vetapp.repository.ClinicRepository;
import org.codewhiskers.vetapp.repository.PatientRepository;
import org.codewhiskers.vetapp.repository.RadiologicImageRepository;
import org.codewhiskers.vetapp.service.IFileStorageService;
import org.codewhiskers.vetapp.service.IRadiologicImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RadiologicImageServiceImpl implements IRadiologicImageService {

    private final RadiologicImageRepository radiologicImageRepository;
    private final RadiologicImageMapper radiologicImageMapper;
    private final PatientRepository patientRepository;
    private final ClinicRepository clinicRepository;
    private final IFileStorageService fileStorageService;

    private static final String RADIOLOGIC_IMAGES_DIR = "radiologic-images";

    @Override
    @Transactional
    public RadiologicImageResponseDTO addRadiologicImage(RadiologicImageRequestDTO radiologicImageRequestDTO, MultipartFile imageFile) {
        try {
            // Dosyayı kaydet
            String filePath = fileStorageService.storeFile(imageFile, RADIOLOGIC_IMAGES_DIR);

            // Entity oluştur
            RadiologicImage radiologicImage = radiologicImageMapper.requestDTOToRadiologicImage(radiologicImageRequestDTO);
            
            // İlişkili nesneleri ayarla
            setRelatedEntities(radiologicImage, radiologicImageRequestDTO);
            
            // Dosya bilgilerini ayarla
            radiologicImage.setFileName(imageFile.getOriginalFilename());
            radiologicImage.setFilePath(filePath);

            // Veritabanına kaydet
            RadiologicImage savedImage = radiologicImageRepository.save(radiologicImage);
            
            // Yanıt dönüştür
            return radiologicImageMapper.radiologicImageToResponseDTO(savedImage);
        } catch (IOException e) {
            throw new RuntimeException("Dosya kaydedilemedi: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public RadiologicImageResponseDTO updateRadiologicImage(Long id, RadiologicImageRequestDTO radiologicImageRequestDTO, MultipartFile imageFile) {
        RadiologicImage radiologicImage = radiologicImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Radyolojik görüntü bulunamadı: " + id));

        // Varlık bilgilerini güncelle
        radiologicImageMapper.updateRadiologicImageFromRequestDTO(radiologicImageRequestDTO, radiologicImage);
        
        // İlişkili nesneleri ayarla
        setRelatedEntities(radiologicImage, radiologicImageRequestDTO);

        // Eğer yeni bir dosya yüklendiyse
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                // Eski dosyayı sil
                fileStorageService.deleteFile(radiologicImage.getFilePath());
                
                // Yeni dosyayı kaydet
                String filePath = fileStorageService.storeFile(imageFile, RADIOLOGIC_IMAGES_DIR);
                
                // Dosya bilgilerini güncelle
                radiologicImage.setFileName(imageFile.getOriginalFilename());
                radiologicImage.setFilePath(filePath);
            } catch (IOException e) {
                throw new RuntimeException("Dosya güncellenemedi: " + e.getMessage());
            }
        }

        // Veritabanına kaydet
        RadiologicImage updatedImage = radiologicImageRepository.save(radiologicImage);
        
        // Yanıt dönüştür
        return radiologicImageMapper.radiologicImageToResponseDTO(updatedImage);
    }

    @Override
    public RadiologicImageResponseDTO getRadiologicImageById(Long id) {
        RadiologicImage radiologicImage = radiologicImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Radyolojik görüntü bulunamadı: " + id));
        
        return radiologicImageMapper.radiologicImageToResponseDTO(radiologicImage);
    }

    @Override
    public List<RadiologicImageResponseDTO> getAllRadiologicImages() {
        return radiologicImageRepository.findAll().stream()
                .map(radiologicImageMapper::radiologicImageToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteRadiologicImage(Long id) {
        RadiologicImage radiologicImage = radiologicImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Radyolojik görüntü bulunamadı: " + id));
        
        // Dosyayı sil
        fileStorageService.deleteFile(radiologicImage.getFilePath());
        
        // Veritabanı kaydını sil
        radiologicImageRepository.delete(radiologicImage);
    }

    @Override
    public List<RadiologicImageResponseDTO> getRadiologicImagesByPatientId(Long patientId) {
        // Hasta kontrolü
        patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Hasta bulunamadı: " + patientId));
        
        // Repository'nin yeni metodunu kullan
        return radiologicImageRepository.findByPatientId(patientId).stream()
                .map(radiologicImageMapper::radiologicImageToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * İlişkili nesneleri ayarlar
     * @param radiologicImage düzenlenecek radyolojik görüntü
     * @param requestDTO istek verisi
     */
    private void setRelatedEntities(RadiologicImage radiologicImage, RadiologicImageRequestDTO requestDTO) {
        // Hasta bilgisini ayarla
        if (requestDTO.getPatientId() != null) {
            Patient patient = patientRepository.findById(requestDTO.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Hasta bulunamadı: " + requestDTO.getPatientId()));
            radiologicImage.setPatient(patient);
        }
        
        // Klinik bilgisini ayarla
        if (requestDTO.getClinicId() != null) {
            Clinic clinic = clinicRepository.findById(requestDTO.getClinicId())
                    .orElseThrow(() -> new RuntimeException("Klinik bulunamadı: " + requestDTO.getClinicId()));
            radiologicImage.setClinic(clinic);
        }
    }
} 