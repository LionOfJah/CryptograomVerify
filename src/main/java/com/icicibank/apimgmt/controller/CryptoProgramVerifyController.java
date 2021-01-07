package com.icicibank.apimgmt.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.icicibank.apimgmt.model.ResponseModel;
import com.icicibank.apimgmt.service.CryptoProgramVerifyService;

@RestController
@RequestMapping
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CryptoProgramVerifyController {

	public static Logger logger = LoggerFactory.getLogger(CryptoProgramVerifyController.class);
	
	@Autowired
	CryptoProgramVerifyService service;
	
	@Autowired
	ResponseModel responseModel; 
	
	@GetMapping("/api/v0/verifyCrypto")
	public ResponseEntity<ResponseModel> verifyCryptoProgram(@RequestHeader("Cryptogram") String cryptoProgram,@RequestHeader("publicKey")String publicKey){
		
		logger.info("cryptoProgram "+cryptoProgram);
		logger.info("publicKey "+publicKey);
		
		
		try {
			responseModel=service.verifyJwtSignature(cryptoProgram,publicKey);
			
		} catch (IOException e) {
			responseModel.setStatus("Fail");
			responseModel.setErrorMessage(" "+e.getMessage());
		}
		
		return ResponseEntity.ok().body(responseModel);
	}
}
