package com.icicibank.apimgmt.service;

import java.io.IOException;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.icicibank.apimgmt.model.ResponseModel;

@Service
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface CryptoProgramVerifyService {
	
	public ResponseModel verifyJwtSignature(String token,String publicKey) throws IOException ;

}
