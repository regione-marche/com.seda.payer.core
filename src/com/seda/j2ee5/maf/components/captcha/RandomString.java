package com.seda.j2ee5.maf.components.captcha;

import java.util.Random;
/**
 * 
 * @author f.ricci
 *
 */
public class RandomString {
    
    private static Random rd = new Random();
        
    public String getString(int count) {
        return getString(count, null);
    }
    
    public String getString(int count, String exclude) {
        if (exclude == null) {
            exclude = "";
        }
        if (count <= 0) {
            throw new IllegalArgumentException("Character length must be > 0");
        }
        int start = ' ';
        int end = 'z'+1;
        int num = end-start;
        StringBuffer buf = new StringBuffer();
        char c;
        while (count-- != 0) {
            c = (char)rd.nextInt(num);
            if (Character.isLetterOrDigit(c) && exclude.indexOf((int)c)<0) {
                buf.append(c);
            } else {
                count++;
            }
        }
        return buf.toString();
    }
    
    public String getStringfromLong() {
        String token = Long.toString(Math.abs(rd.nextLong()), 36);
        return token;
    }
    
}
