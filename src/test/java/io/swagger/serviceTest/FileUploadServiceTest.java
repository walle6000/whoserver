package io.swagger.serviceTest;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.Swagger2SpringBoot;
import io.swagger.common.FileType;
import io.swagger.model.FileUpload;
import io.swagger.service.common.FileUploadService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Swagger2SpringBoot.class)
@WebAppConfiguration
public class FileUploadServiceTest {

	@Autowired
	FileUploadService fileUploadService;
	
	@Test
	public void testFileUpload() throws IOException{
		final String fileName = "Koala.jpg";
		final byte[] content = this.imageToByteArray("D:/Koala.jpg"); 
		MockMultipartFile file = new MockMultipartFile("file", fileName, "image/jpeg", content);
		List<FileUpload> fileUploads = fileUploadService.uploadImgFile(FileType.UserheadIcon,file);
		assertNotNull(fileUploads);
		assertTrue(fileUploads.size()>0);
		assertEquals("Koala.jpg", fileUploads.get(0).getFileName());
	}
	
	public byte[] imageToByteArray(String imgPath) {
		BufferedInputStream in;
		try {
		    in = new BufferedInputStream(new FileInputStream(imgPath));
		    ByteArrayOutputStream out = new ByteArrayOutputStream();
		    int size = 0;
		    byte[] temp = new byte[1024];
		    while((size = in.read(temp))!=-1) {
		        out.write(temp, 0, size);
		    }
		    in.close();
		    return out.toByteArray();
		    } catch (IOException e) {
		           e.printStackTrace();
		           return null;
		    }
		}
}
