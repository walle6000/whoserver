package io.swagger.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeUtil {
    public static BufferedImage QREncode(String content,int imgSize){
        BitMatrix bitMatrix = null;
		try {
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
	        
			bitMatrix = new MultiFormatWriter().encode(content,  
			        BarcodeFormat.QR_CODE, imgSize, imgSize, hints);
		} catch (WriterException e) {
			e.printStackTrace();
		}
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        return image;
    }
    
    public static void QREncode(String content,int imgSize,OutputStream output){
        BitMatrix bitMatrix = null;
		try {
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
	        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
	        
			bitMatrix = new MultiFormatWriter().encode(content,  
			        BarcodeFormat.QR_CODE, imgSize, imgSize, hints);
			
			MatrixToImageWriter.writeToStream(bitMatrix, "png", output);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
        
    }
}
