package com.seda.j2ee5.maf.components.captcha;


/**
 * Singleton facility to create the captcha image
 * @author f.ricci
 */
public class CaptchaSingleton {
    
    private static BaseCaptcha instance = new BaseCaptcha();
    
    public static BaseCaptcha getInstance() {
        return instance;
    }
    
}

