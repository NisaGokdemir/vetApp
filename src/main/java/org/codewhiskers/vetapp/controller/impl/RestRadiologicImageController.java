package org.codewhiskers.vetapp.controller.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.controller.IRestRadiologicImageController;
import org.codewhiskers.vetapp.dto.RadiologicImage.request.RadiologicImageRequestDTO;
import org.codewhiskers.vetapp.dto.RadiologicImage.response.RadiologicImageResponseDTO;
import org.codewhiskers.vetapp.service.IRadiologicImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/radiologic-images")
@RequiredArgsConstructor
public class RestRadiologicImageController implements IRestRadiologicImageController {

    private final IRadiologicImageService radiologicImageService;

    /**
     * Yeni radyolojik görüntü ekler
     * @param radiologicImageRequestDTO radyolojik görüntü bilgileri
     * @param imageFile görüntü dosyası
     * @return eklenen radyolojik görüntü
     */
    @Override
    public ResponseEntity<RadiologicImageResponseDTO> addRadiologicImage(
            RadiologicImageRequestDTO radiologicImageRequestDTO,
            MultipartFile imageFile) {
        
        RadiologicImageResponseDTO responseDTO = radiologicImageService.addRadiologicImage(
                radiologicImageRequestDTO, imageFile);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    /**
     * Radyolojik görüntüyü günceller
     * @param id güncellenecek görüntü id'si
     * @param radiologicImageRequestDTO güncellenmiş görüntü bilgileri
     * @param imageFile yeni görüntü dosyası (opsiyonel)
     * @return güncellenen radyolojik görüntü
     */
    @Override
    public ResponseEntity<RadiologicImageResponseDTO> updateRadiologicImage(
            Long id, 
            RadiologicImageRequestDTO radiologicImageRequestDTO,
            MultipartFile imageFile) {
        
        RadiologicImageResponseDTO responseDTO = radiologicImageService.updateRadiologicImage(
                id, radiologicImageRequestDTO, imageFile);
        
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * ID'ye göre radyolojik görüntüyü getirir
     * @param id radyolojik görüntü id'si
     * @return bulunan radyolojik görüntü
     */
    @Override
    public ResponseEntity<RadiologicImageResponseDTO> getRadiologicImageById(Long id) {
        RadiologicImageResponseDTO responseDTO = radiologicImageService.getRadiologicImageById(id);
        return ResponseEntity.ok(responseDTO);
    }

    /**
     * Tüm radyolojik görüntüleri listeler
     * @return radyolojik görüntü listesi
     */
    @Override
    public ResponseEntity<List<RadiologicImageResponseDTO>> getAllRadiologicImages() {
        List<RadiologicImageResponseDTO> responseDTOList = radiologicImageService.getAllRadiologicImages();
        return ResponseEntity.ok(responseDTOList);
    }

    /**
     * Radyolojik görüntüyü siler
     * @param id silinecek görüntü id'si
     * @return başarı durumu
     */
    @Override
    public ResponseEntity<Void> deleteRadiologicImage(Long id) {
        radiologicImageService.deleteRadiologicImage(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Hasta ID'sine göre radyolojik görüntüleri getirir
     * @param patientId hasta id'si
     * @return hastaya ait radyolojik görüntü listesi
     */
    @Override
    public ResponseEntity<List<RadiologicImageResponseDTO>> getRadiologicImagesByPatientId(Long patientId) {
        List<RadiologicImageResponseDTO> responseDTOList = radiologicImageService.getRadiologicImagesByPatientId(patientId);
        return ResponseEntity.ok(responseDTOList);
    }
} 