package pro.fontoura.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pro.fontoura.cursomc.services.exceptions.FileException;

@Service
public class ImageService {

	/**
	 * @param uploadedFile
	 * @return
	 */
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equalsIgnoreCase(ext) && !"jpg".equalsIgnoreCase(ext)) {
			throw new FileException("Somente imagens '*.png' e '*.jpg' são permitidas");
		}

		try {
			BufferedImage image = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equalsIgnoreCase(ext)) {
				image = pngToJpg(image);
			}
			return image;

		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}

	}

	/**
	 * @param image
	 * @return
	 */
	public BufferedImage pngToJpg(BufferedImage image) {
		BufferedImage jpgImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);

		return jpgImage;
	}

	/**
	 * @param image
	 * @param extetion
	 * @return
	 */
	public InputStream getInputStream(BufferedImage image, String extetion) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, extetion, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}
	
	
	/**
	 * @param sourceImage
	 * @return
	 */
	public BufferedImage cropSquare(BufferedImage sourceImage) {
		int min = (sourceImage.getHeight() <= sourceImage.getWidth() ? sourceImage.getHeight() : sourceImage.getWidth());
		
		return Scalr.crop(sourceImage, 
				(sourceImage.getWidth()/2) - (min/2), 
				(sourceImage.getHeight()/2) - (min/2), 
				min, min);
	}
	
	/**
	 * @param sourceImage
	 * @param size
	 * @return
	 */
	public BufferedImage resize(BufferedImage sourceImage, int size) {
		return Scalr.resize(sourceImage, Scalr.Method.ULTRA_QUALITY, size);
	}
}
