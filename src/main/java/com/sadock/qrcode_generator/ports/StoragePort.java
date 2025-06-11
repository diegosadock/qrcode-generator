package com.sadock.qrcode_generator.ports;

public interface StoragePort {
	
	public String uploadFile(byte[] fileData, String fileName, String contentType);

}
