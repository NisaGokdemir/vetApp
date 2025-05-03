package org.codewhiskers.vetapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IFileStorageService {
    /**
     * Dosyayı belirtilen klasöre kaydeder
     * @param file kaydedilecek dosya
     * @param directory kaydedilecek dizin
     * @return kaydedilen dosyanın yolu
     * @throws IOException dosya işlemi hatası durumunda
     */
    String storeFile(MultipartFile file, String directory) throws IOException;

    /**
     * Dosyayı siler
     * @param filePath silinecek dosyanın yolu
     * @return işlem başarılı ise true, değilse false
     */
    boolean deleteFile(String filePath);

    /**
     * Dosya yolunu döndürür
     * @param fileName dosya adı
     * @param directory dizin
     * @return dosyanın tam yolu
     */
    Path getFilePath(String fileName, String directory);
} 