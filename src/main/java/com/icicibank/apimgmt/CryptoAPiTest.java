package com.icicibank.apimgmt;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class CryptoAPiTest {
	
	

	private static Logger logger = LoggerFactory.getLogger(CryptoAPiTest.class);
	
	@Value("${app.publickey.filePath}")
	 String publicKeyPath;
	

	 String privateKeyPath="C:\\Users\\jitendra_rawat\\Downloads\\Icici_proj_docs\\IBANA_Pro\\EFTS_2019\\EFTS_2019.pfx";
	
	
	public static void main(String[] args) {
		CryptoAPiTest cry=new CryptoAPiTest();
		logger.info("privateKey in main method "+cry.privateKeyPath);
		PrivateKeyEntry privateKeyEntry=cry.getPrivateKeyEntry(cry.privateKeyPath);

		logger.info(privateKeyEntry.getPrivateKey().toString());
	}

	public PublicKey getPublicKey(String file) throws Exception
	{
	
	    CertificateFactory certFact 	= CertificateFactory.getInstance("x.509");
		FileInputStream fi 			= new FileInputStream(file);
		
		X509Certificate certificate = (X509Certificate) certFact.generateCertificate(fi);
		PublicKey publicKey 					= certificate.getPublicKey();
		
		return publicKey;
	}
	
	public PrivateKeyEntry getPrivateKeyEntry(String privateKeyPath)	
	{
		FileInputStream keyStoreInputStream = null;
		PrivateKeyEntry privateKeyEntry = null;
		
		logger.info("privateKey "+privateKeyPath);
		
		try 
		{
			KeyStore ks 			 = KeyStore.getInstance("PKCS12");
			String keyStoreFile 	 = privateKeyPath;
			char[] ketStrorePwdArray = "efts@2019".toCharArray();
			keyStoreInputStream 	 = new FileInputStream(keyStoreFile);
			ks.load(keyStoreInputStream, ketStrorePwdArray);

			String alias 	= null;
			alias 		 	= (String) ks.aliases().nextElement();

			privateKeyEntry = (PrivateKeyEntry) ks.getEntry(alias, 
								new KeyStore.PasswordProtection(ketStrorePwdArray));
		} 
		catch (Exception e) 
		{
			logger.error("Exception Caught While get Private Key Entry");
			logger.error( "Exception", e );
		} 
		finally 
		{
			if (keyStoreInputStream != null) 
			{
				try 
				{
					keyStoreInputStream.close();
				} 
				catch (Exception e) 
				{
					logger.error("Exception in keyStoreInputStream");
					logger.error( "Exception", e );
				}
			}
		}
		
		//return privateKeyEntry.getPrivateKey();
		return privateKeyEntry;
	}
}
