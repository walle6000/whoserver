package io.swagger.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.model.FileUpload;
import io.swagger.utils.DateUtil;
import io.swagger.utils.MD5Util;

@Service
public class FileUploadService {
	
	private Logger logger = LoggerFactory.getLogger(FileUploadService.class);
	
	//设置缩略图的宽高  
    private int thumbnailWidth = 150; 
    
    private int thumbnailHeight = 100;
    
    private String uploadRootPath;
    
    private String visitRootUrl;
    
    public List<FileUpload> uploadImgFile(MultipartFile... multipartFile) throws IOException{
    	
    	List<FileUpload> fileUploadEntities = new ArrayList<FileUpload>();
    	
    	if(multipartFile!=null){
    		for(MultipartFile tempFile : multipartFile){
    			if(!tempFile.isEmpty()){
    				String checkSum = MD5Util.getMD5(tempFile.getInputStream());  
    				String fileExtension = getFileExtension(tempFile.getOriginalFilename()).toLowerCase();  
                    String preName = getPreName(false).toLowerCase();  
                    String blobName = checkSum + fileExtension; 
                    logger.info("FileUploadService - uploadImgFile blobName:" + preName+blobName);
                    String visitUrl = uploadFile(preName,blobName,tempFile);
                    
                    //生成缩略图
                    BufferedImage img = new BufferedImage(thumbnailWidth, thumbnailHeight, BufferedImage.TYPE_INT_RGB);  
                    img.createGraphics().drawImage(ImageIO.read(tempFile.getInputStream()).getScaledInstance(thumbnailWidth, thumbnailHeight, Image.SCALE_SMOOTH),0,0,null);  
                    ByteArrayOutputStream thumbnailStream = new ByteArrayOutputStream();  
                    ImageIO.write(img, "jpg", thumbnailStream);  
                    InputStream inputStream = new ByteArrayInputStream(thumbnailStream.toByteArray());  
                    String thumbnailPreName = getPreName(true).toLowerCase();  
                    String thumbnailCheckSum = MD5Util.getMD5(new ByteArrayInputStream(thumbnailStream.toByteArray()));  
                    String blobThumbnail = thumbnailCheckSum + ".jpg";  
                    logger.info("FileUploadService - uploadImgFile blobThumbnail:" + preName+thumbnailCheckSum+".jpg");
                    String visitThumbUrl = uploadFile(thumbnailPreName,blobThumbnail,inputStream);
                    
                  //将上传后的图片URL返回  
                    FileUpload blobUploadEntity = new FileUpload();  
                    blobUploadEntity.setFileName(tempFile.getOriginalFilename());  
                    blobUploadEntity.setFileUrl(visitUrl);  
                    blobUploadEntity.setThumbnailUrl(visitThumbUrl);  
                      
                    fileUploadEntities.add(blobUploadEntity);  
    			}
    		}
    	}
    	
    	return fileUploadEntities;
    }
    
    private String uploadFile(String uploadPath,String uploadFileName,MultipartFile uploadFile){
    	File file = new File(new File(uploadPath), uploadFileName);  
        if (file.exists()) {  
        	return visitRootUrl+uploadFileName;
        }else {  
            File dir = new File(uploadPath);  
            if (!(dir.exists()))  
                dir.mkdirs();  
            //上传文件  
            try {  
                FileUtils.copyInputStreamToFile(uploadFile.getInputStream(), file);  
                return visitRootUrl+uploadFileName;
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            return "";
        }  
    }
    
    private String uploadFile(String uploadPath,String uploadFileName,InputStream uploadInputStream){
    	File file = new File(new File(uploadPath), uploadFileName);  
        if (file.exists()) {  
        	return visitRootUrl+uploadFileName;
        }else {  
            File dir = new File(uploadPath);  
            if (!(dir.exists()))  
                dir.mkdirs();  
            //上传文件  
            try {  
                FileUtils.copyInputStreamToFile(uploadInputStream, file);  
                return visitRootUrl+uploadFileName;
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            return "";
        }  
    }
    
    private String getFileExtension(String fileName){  
        int position = fileName.indexOf('.');  
        if (position > 0)  
        {  
            String temp = fileName.substring(position);  
            return temp;  
        }  
        return "";  
    }  
    
    private String getPreName(Boolean thumbnail){  
    	String uploadPath = uploadRootPath + File.separator + DateUtil.strToDate() + File.separator;
    	
        String afterName = "";  
        if (thumbnail){  
            afterName = "thumbnail/";  
        }  
        
        return uploadPath + afterName;
        
    }  

}
