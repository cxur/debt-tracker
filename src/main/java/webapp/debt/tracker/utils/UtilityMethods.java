package webapp.debt.tracker.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

@Component
public class UtilityMethods {
	
	public byte[] ImageToBytes(File debtorProfilePicture) {
		
		BufferedImage bImage;
		byte [] data = null;
		try {
			bImage = ImageIO.read(debtorProfilePicture);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bImage, FilenameUtils.getExtension(debtorProfilePicture.getAbsolutePath()), bos );
			data = bos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	     return data; 
	}

}
