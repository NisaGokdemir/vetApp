package org.codewhiskers.vetapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload.root-dir:uploads}")
    private String uploadDir;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("*")
                .allowCredentials(true);
    }

    /**
     * Yüklenen dosyalara erişim için kaynak yollarını yapılandırır
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Radyolojik görüntüler için kaynak yolunu ekle
        Path radiologicImagesUploadDir = Paths.get(uploadDir, "radiologic-images");
        String radiologicImagesPath = radiologicImagesUploadDir.toFile().getAbsolutePath();
        
        registry.addResourceHandler("/uploads/radiologic-images/**")
                .addResourceLocations("file:" + radiologicImagesPath + "/");
                
        // Diğer dosya türleri için de benzer şekilde eklemeler yapılabilir
    }
}