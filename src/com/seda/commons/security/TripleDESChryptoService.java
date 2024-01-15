package com.seda.commons.security;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
/**
 * @author SEDA Labs
 *
 */

public class TripleDESChryptoService implements ChryptoServiceHandler {

	private final String ALGORITHM = "DESede";
	private final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";
	
	private byte[] key = null;
	private byte[] iv = null;	
	
	private DESedeKeySpec deSedeKeySpec = null;
	private IvParameterSpec ivParameterSpec = null;
	private SecretKey secretKey = null;
	private Cipher cipher = null;	

	/* ======================================= */
	// Private Methods 
	/* ======================================= */
	private boolean hasKey() {
		return key==null?false:true;
	}	
	private boolean hasIv() {
		return iv==null?false:true;
	}
	private void validate(byte[] value) throws ChryptoServiceException {
		if (value==null) 
			throw new ChryptoServiceException(Messages.VALUE_NULL.format());		
		if (!hasKey()) {
			throw new ChryptoServiceException(Messages.SECURITY_KEY.format());
		}
	}
	
	/* ======================================= */
	// Public Methods 
	/* ======================================= */	
	public void setKey(byte[] key) {
		this.key = key;
	}	
	
	public void setKeyValue(String key) throws UnsupportedEncodingException {
		setKey(key.getBytes());
	}
	
	public void setIv(byte[] iv) {
		this.iv = iv;
	}	
	
	public void setIv(String iv) throws UnsupportedEncodingException {
		setIv(iv.getBytes());
	}
	/* ======================================= */
	// Constructors 
	/* ======================================= */	
	public TripleDESChryptoService() {}
	
	public TripleDESChryptoService(String key) throws UnsupportedEncodingException {
		setKeyValue(key);
	}
	/* ======================================= */
	// Public security actions 
	/* ======================================= */	
	public byte[] decrypt(String value) throws ChryptoServiceException {
		return decrypt(value.getBytes());
	}

	public byte[] decrypt(byte[] value) throws ChryptoServiceException {
		validate(value);
		byte[] decrypted = null;		
		try {
			deSedeKeySpec = new DESedeKeySpec(key);
			secretKey = SecretKeyFactory.getInstance(ALGORITHM).generateSecret(deSedeKeySpec);		
			if (hasIv()) {
				ivParameterSpec = new IvParameterSpec(iv);
				cipher = Cipher.getInstance(TRANSFORMATION);
				cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
			} else {
				cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.DECRYPT_MODE, secretKey);			
			}
			decrypted = cipher.doFinal(value);
		} catch (InvalidKeyException e) {
			throw new ChryptoServiceException(e);
		} catch (InvalidKeySpecException e) {
			throw new ChryptoServiceException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new ChryptoServiceException(e);
		} catch (NoSuchPaddingException e) {
			throw new ChryptoServiceException(e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new ChryptoServiceException(e);
		} catch (IllegalBlockSizeException e) {
			throw new ChryptoServiceException(e);
		} catch (BadPaddingException e) {
			throw new ChryptoServiceException(e);
		} finally {
			nullService();
		}
		return decrypted;		
	}
	
	public String decryptBase64(String value) throws ChryptoServiceException {
		byte[] input=null;
		try {
			input = Base64Codec.decode(value);
		} catch (IllegalArgumentException e) {
			throw new ChryptoServiceException(e);
		}
		return new String(decrypt(input));			
	}
	
	public String decryptBASE64(String value) throws ChryptoServiceException {
		return decryptBASE64(Charset.defaultCharset(), value);
	}	
	
	public String decryptBASE64(Charset charset, String value) throws ChryptoServiceException {
		return decryptBASE64(charset.displayName(), value);
	}	
	
	public String decryptBASE64(String charset, String value) throws ChryptoServiceException {
//		Base64 codec = new Base64();
		byte[] decoded;
		try {
			decoded = Base64.decodeBase64(value.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			throw new ChryptoServiceException(e);
		}
		try {
			return new String(decrypt(decoded),charset);
		} catch (UnsupportedEncodingException e) {
			throw new ChryptoServiceException(e);
		}
	}
	
	public byte[] encrypt(String value) throws ChryptoServiceException {
		return encrypt(value.getBytes());
	}
	
	public byte[] encrypt(byte[] value) throws ChryptoServiceException {
		validate(value);		
		byte[] encrypted = null;		
		try {
			deSedeKeySpec = new DESedeKeySpec(key);
			secretKey = SecretKeyFactory.getInstance(ALGORITHM).generateSecret(deSedeKeySpec);		
			if (hasIv()) {
				ivParameterSpec = new IvParameterSpec(iv);
				cipher = Cipher.getInstance(TRANSFORMATION);
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
			} else {
				cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);			
			}
			encrypted = cipher.doFinal(value);
		} catch (InvalidKeyException e) {
			throw new ChryptoServiceException(e);
		} catch (InvalidKeySpecException e) {
			throw new ChryptoServiceException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new ChryptoServiceException(e);
		} catch (NoSuchPaddingException e) {
			throw new ChryptoServiceException(e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new ChryptoServiceException(e);
		} catch (IllegalBlockSizeException e) {
			throw new ChryptoServiceException(e);
		} catch (BadPaddingException e) {
			throw new ChryptoServiceException(e);
		} finally {
			nullService();			
		}
		return encrypted;
	}	
	
	public String encryptBase64(String value) throws ChryptoServiceException {
		return new String(Base64Codec.encode(encrypt(value)));			
	}
	
	public String encryptBASE64(String value) throws ChryptoServiceException {
//		Base64 codec = new Base64();
		byte[] encoded = Base64.encodeBase64(encrypt(value));
		return new String(encoded); 
	}
	
	public void nullService() {
		deSedeKeySpec=null;
		ivParameterSpec=null;
		secretKey=null;
		cipher=null;		
	}	
	
	public void destroy() {
		key = null;
		iv = null;	
		nullService();	
	}
	
	protected void finalize() throws Throwable {
		try {
	        destroy();        // null all resources
	    } finally {
	        super.finalize();
	    }
	}
}
