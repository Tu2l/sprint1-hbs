package com.hbs.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hbs.exceptions.InvalidImageFormatException;

public class FileUploadUtil {
	private static final String ROOT_DIR = "uploads/";

	private FileUploadUtil() {
	}

	public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(ROOT_DIR + uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}

		return ROOT_DIR + uploadDir + "/" + fileName;
	}

	public static String prepareImage(MultipartFile image, String uploadDir)
			throws InvalidImageFormatException, IOException {

		String name = null;

		name = image.getOriginalFilename();

		if (name != null)
			name = StringUtils.cleanPath(name);

		StringBuilder sb = new StringBuilder(name);
		String ext = sb.substring(sb.lastIndexOf(".") + 1);

		if (!ValidationUtil.validateImageExtension(ext))
			throw new InvalidImageFormatException("Invalid Image file");

		return saveFile(uploadDir, name, image);

	}

	public static boolean deleteImage(String path) {
		if (path == null)
			return false;
		try {
			return Files.deleteIfExists(Paths.get(path));
		} catch (IOException e) {
			return false;
		}
	}
}