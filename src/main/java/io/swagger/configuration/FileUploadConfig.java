package io.swagger.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

//@Configuration
public class FileUploadConfig {

	@Value("${fileUpload.maxUploadSize}")  
	private long maxUploadSize;
	
	@Value("${fileUpload.maxInMemorySize}")  
	private int maxInMemorySize;
	
	@Value("${fileUpload.maxUploadSizePerFile}")    
	private long maxUploadSizePerFile;
	
	@Value("${fileUpload.defaultEncoding}")    
	private String defaultEncoding;
	
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(maxUploadSize); //1Mb
        multipartResolver.setMaxInMemorySize(maxInMemorySize);
        multipartResolver.setMaxUploadSizePerFile(maxUploadSizePerFile);
        multipartResolver.setDefaultEncoding(defaultEncoding);
        return multipartResolver;
    }
}
