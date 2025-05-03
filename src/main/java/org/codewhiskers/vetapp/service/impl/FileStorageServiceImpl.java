package org.codewhiskers.vetapp.service.impl;

import org.codewhiskers.vetapp.service.IFileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements IFileStorageService {

    @Value("${file.upload.root-dir:uploads}")
    private String rootDirectory;

    /**
     * Dosyayı belirtilen klasöre kaydeder
     * @param file kaydedilecek dosya
     * @param directory kaydedilecek dizin
     * @return kaydedilen dosyanın yolu
     * @throws IOException dosya işlemi hatası durumunda
     */
    @Override
    public String storeFile(MultipartFile file, String directory) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("Dosya boş olamaz");
        }

        // Dosya adını UUID ile eşsiz hale getirme
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName != null 
                ? originalFileName.substring(originalFileName.lastIndexOf(".")) 
                : "";
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

        // Klasör yolunu oluşturma
        Path directoryPath = Paths.get(rootDirectory, directory).toAbsolutePath().normalize();
        
        // Klasör yoksa oluştur
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Dosya yolu
        Path targetPath = directoryPath.resolve(uniqueFileName);

        // Dosyayı kopyala
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        // Dosya yolunu göreceli olarak döndür
        return directory + "/" + uniqueFileName;
    }

    /**
     * Dosyayı siler
     * @param filePath silinecek dosyanın yolu
     * @return işlem başarılı ise true, değilse false
     */
    @Override
    public boolean deleteFile(String filePath) {
        try {
            if (filePath == null || filePath.isEmpty()) {
                return false;
            }

            Path path = Paths.get(rootDirectory, filePath).toAbsolutePath().normalize();
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Dosya yolunu döndürür
     * @param fileName dosya adı
     * @param directory dizin
     * @return dosyanın tam yolu
     */
    @Override
    public Path getFilePath(String fileName, String directory) {
        return Paths.get(rootDirectory, directory, fileName).toAbsolutePath().normalize();
    }
} 