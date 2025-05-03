package org.codewhiskers.vetapp.service;

import org.codewhiskers.vetapp.dto.RadiologicImage.request.RadiologicImageRequestDTO;
import org.codewhiskers.vetapp.dto.RadiologicImage.response.RadiologicImageResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRadiologicImageService {
    /**
     * Yeni radyolojik görüntü ekler
     * @param radiologicImageRequestDTO radyolojik görüntü bilgileri
     * @param imageFile görüntü dosyası
     * @return eklenen radyolojik görüntü
     */
    RadiologicImageResponseDTO addRadiologicImage(RadiologicImageRequestDTO radiologicImageRequestDTO, MultipartFile imageFile);

    /**
     * Radyolojik görüntüyü günceller
     * @param id güncellenecek görüntü id'si
     * @param radiologicImageRequestDTO güncellenmiş görüntü bilgileri
     * @param imageFile yeni görüntü dosyası (opsiyonel)
     * @return güncellenen radyolojik görüntü
     */
    RadiologicImageResponseDTO updateRadiologicImage(Long id, RadiologicImageRequestDTO radiologicImageRequestDTO, MultipartFile imageFile);

    /**
     * ID'ye göre radyolojik görüntüyü getirir
     * @param id radyolojik görüntü id'si
     * @return bulunan radyolojik görüntü
     */
    RadiologicImageResponseDTO getRadiologicImageById(Long id);

    /**
     * Tüm radyolojik görüntüleri listeler
     * @return radyolojik görüntü listesi
     */
    List<RadiologicImageResponseDTO> getAllRadiologicImages();

    /**
     * Radyolojik görüntüyü siler
     * @param id silinecek görüntü id'si
     */
    void deleteRadiologicImage(Long id);

    /**
     * Hasta ID'sine göre radyolojik görüntüleri getirir
     * @param patientId hasta id'si
     * @return hastaya ait radyolojik görüntü listesi
     */
    List<RadiologicImageResponseDTO> getRadiologicImagesByPatientId(Long patientId);
} 