package org.codewhiskers.vetapp.controller;

import org.codewhiskers.vetapp.dto.RadiologicImage.request.RadiologicImageRequestDTO;
import org.codewhiskers.vetapp.dto.RadiologicImage.response.RadiologicImageResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRestRadiologicImageController {
    
    /**
     * Yeni radyolojik görüntü ekler
     * @param radiologicImageRequestDTO radyolojik görüntü bilgileri
     * @param imageFile görüntü dosyası
     * @return eklenen radyolojik görüntü
     */
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<RadiologicImageResponseDTO> addRadiologicImage(
            @RequestPart("radiologicImage") RadiologicImageRequestDTO radiologicImageRequestDTO,
            @RequestPart("imageFile") MultipartFile imageFile);

    /**
     * Radyolojik görüntüyü günceller
     * @param id güncellenecek görüntü id'si
     * @param radiologicImageRequestDTO güncellenmiş görüntü bilgileri
     * @param imageFile yeni görüntü dosyası (opsiyonel)
     * @return güncellenen radyolojik görüntü
     */
    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<RadiologicImageResponseDTO> updateRadiologicImage(
            @PathVariable Long id,
            @RequestPart("radiologicImage") RadiologicImageRequestDTO radiologicImageRequestDTO,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile);

    /**
     * ID'ye göre radyolojik görüntüyü getirir
     * @param id radyolojik görüntü id'si
     * @return bulunan radyolojik görüntü
     */
    @GetMapping("/{id}")
    ResponseEntity<RadiologicImageResponseDTO> getRadiologicImageById(@PathVariable Long id);

    /**
     * Tüm radyolojik görüntüleri listeler
     * @return radyolojik görüntü listesi
     */
    @GetMapping
    ResponseEntity<List<RadiologicImageResponseDTO>> getAllRadiologicImages();

    /**
     * Radyolojik görüntüyü siler
     * @param id silinecek görüntü id'si
     * @return başarı durumu
     */
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRadiologicImage(@PathVariable Long id);

    /**
     * Hasta ID'sine göre radyolojik görüntüleri getirir
     * @param patientId hasta id'si
     * @return hastaya ait radyolojik görüntü listesi
     */
    @GetMapping("/patient/{patientId}")
    ResponseEntity<List<RadiologicImageResponseDTO>> getRadiologicImagesByPatientId(@PathVariable Long patientId);
} 