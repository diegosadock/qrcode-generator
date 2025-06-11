package com.sadock.qrcode_generator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sadock.qrcode_generator.dto.QrCodeRequestDTO;
import com.sadock.qrcode_generator.dto.QrCodeResponseDTO;
import com.sadock.qrcode_generator.service.qrcode.QrCodeGeneratorService;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
	
	private final QrCodeGeneratorService qrCodeService;
	
	public QrCodeController(QrCodeGeneratorService qrCodeService) {
		this.qrCodeService = qrCodeService;
		
	}
	
	@PostMapping
	public ResponseEntity<QrCodeResponseDTO> generate(@RequestBody QrCodeRequestDTO request) {
		
		try {
			QrCodeResponseDTO response = this.qrCodeService.generateAndUploadQrCode(request.texto());
			return ResponseEntity.ok(response);
		}
		catch (Exception ex) {
			System.out.println(ex);
			return ResponseEntity.internalServerError().build();
		}
		
		
		
	}
	
	

}
