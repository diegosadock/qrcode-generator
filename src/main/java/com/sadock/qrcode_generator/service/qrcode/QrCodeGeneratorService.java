package com.sadock.qrcode_generator.service.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sadock.qrcode_generator.dto.QrCodeResponseDTO;
import com.sadock.qrcode_generator.ports.StoragePort;

@Service
public class QrCodeGeneratorService {
	
	private final StoragePort storage;
	
	public QrCodeGeneratorService(StoragePort storage) {
		this.storage = storage;
	}
	
	public QrCodeResponseDTO generateAndUploadQrCode(String text) throws WriterException, IOException {
		
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, com.google.zxing.BarcodeFormat.QR_CODE, 200, 200);
		
		ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
		byte[] pngQrCodeData = pngOutputStream.toByteArray();
		
		String url = storage.uploadFile(pngQrCodeData, UUID.randomUUID().toString(), "image/png");
		
		return new QrCodeResponseDTO(url);
	}

}
