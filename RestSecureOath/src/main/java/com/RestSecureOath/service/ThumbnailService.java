package com.RestSecureOath.service;

import org.springframework.web.multipart.MultipartFile;

public interface ThumbnailService {

	byte[] resize(MultipartFile file, int maxLongSide);

	byte[] resize(String file, int maxLongSide);

}
