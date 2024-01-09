package com.seda.j2ee5.maf.components.captcha;

import java.awt.image.RGBImageFilter;

/**
 * 
 * @author f.ricci
 *
 */
public class CaptchaFilter extends RGBImageFilter {
    
    /** Creates a new instance of BlueFilter */
    public CaptchaFilter() {
        canFilterIndexColorModel = true;
    }
    @Override 
    public int filterRGB(int x, int y, int rgb) {
        return (rgb | 0x000000FF);
    }
    
}
