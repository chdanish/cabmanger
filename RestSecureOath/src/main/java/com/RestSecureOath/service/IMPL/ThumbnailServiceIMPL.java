package com.RestSecureOath.service.IMPL;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.RestSecureOath.exception.StorageException;
import com.RestSecureOath.service.ThumbnailService;

@Service
public class ThumbnailServiceIMPL implements ThumbnailService{
	
	private static final ImageObserver DUMMY_OBSERVER = (img, infoflags, x, y, width, height) -> true;
	
	private final int maxLongSide = 140;
	
	@Override
    public byte[] resize(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            BufferedImage imgIn = ImageIO.read(file.getInputStream());
            double scale;
            if (imgIn.getWidth() >= imgIn.getHeight()) {
              // horizontal or square image
              scale = Math.min(maxLongSide, imgIn.getWidth()) / (double) imgIn.getWidth();
            } else {
              // vertical image
              scale = Math.min(maxLongSide, imgIn.getHeight()) / (double) imgIn.getHeight();
            }
            
            BufferedImage thumbnailOut = new BufferedImage((int) (scale * imgIn.getWidth()),
                                                           (int) (scale * imgIn.getHeight()),
                                                           imgIn.getType());
            Graphics2D g = thumbnailOut.createGraphics();

            AffineTransform transform = AffineTransform.getScaleInstance(scale, scale);
            g.drawImage(imgIn, transform, DUMMY_OBSERVER);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	ImageIO.write( thumbnailOut, "jpeg", baos );
        	baos.flush();
        	byte[] imageInByte = baos.toByteArray();
        	baos.close();
        	return imageInByte;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
	

}
