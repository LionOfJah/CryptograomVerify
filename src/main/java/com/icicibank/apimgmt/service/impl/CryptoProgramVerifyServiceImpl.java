package com.icicibank.apimgmt.service.impl;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.icicibank.apimgmt.model.ResponseModel;
import com.icicibank.apimgmt.service.CryptoProgramVerifyService;
import com.icicibank.apimgmt.util.PemUtils;

@Service
public class CryptoProgramVerifyServiceImpl implements CryptoProgramVerifyService {

	public static Logger logger = LoggerFactory.getLogger(CryptoProgramVerifyServiceImpl.class);
	@Value("${app.publickey.filePath}")
	String RSA_PUBLIC_KEY;
	
	@Value("${app.privatekey.filePath}")
	String RSA_PRIVATE_KEY;
	
	@Autowired
	ResponseModel responseModel;
	
	
	@Override
	public ResponseModel verifyJwtSignature(String token) throws IOException {
		
		RSAPublicKey publicKey=(RSAPublicKey) PemUtils.readPublicKeyFromFile(RSA_PUBLIC_KEY, "RSA");
		
		RSAPrivateKey privateKey=(RSAPrivateKey) PemUtils.readPrivateKeyFromFile(RSA_PRIVATE_KEY, "RSA");
		
		try {
		    Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
		    // leeway= ChronoUnit.MILLIS.between(, temporal2Exclusive)
		    JWTVerifier verifier = JWT.require(algorithm).acceptLeeway(19805)
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    
		    responseModel.setStatus("Success");
		    responseModel.setIban(" "+jwt.getClaim("iban").asString());
		    logger.info(" "+jwt.getIssuedAt());
		    
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
			responseModel.setStatus("Fail");
		    //responseModel.setIban(" "+jwt.getClaim("iban"));
			logger.info("error message "+exception.getMessage());
			responseModel.setErrorMessage(exception.getMessage());
			return responseModel;
		}
		
		return responseModel;
	}

}
