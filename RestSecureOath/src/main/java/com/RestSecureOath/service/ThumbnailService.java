package com.RestSecureOath.service;

import org.springframework.web.multipart.MultipartFile;

public interface ThumbnailService {

	byte[] resize(MultipartFile file);

}
