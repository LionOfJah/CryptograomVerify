package com.icicibank.apimgmt.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.icicibank.apimgmt.model.ResponseModel;

@Service
public interface CryptoProgramVerifyService {
	
	public ResponseModel verifyJwtSignature(String token) throws IOException ;

}
