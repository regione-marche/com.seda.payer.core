package com.seda.j2ee5.maf.components.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.util.Random;

/**
 * 
 * @author f.ricci
 *
 */
public class BaseCaptcha {
    
    private Random rd = null;
    public static final int WIDTH = 200;
    public static final int HEIGHT = 60;
    protected Color background = new Color(Integer.parseInt("c0c0c0", 16));
    
    protected void drawMessage(Graphics g, String message) {
        g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
        g.setColor(Color.GRAY);
        int len = message.length();
        int wgap = WIDTH/len;
        int startX = 10;
        int startY = 20;
        for (int i = 0; i<len; i++) {
            g.drawString(message.substring(i, i+1), startX+(wgap*i), startY+rd.nextInt(40));
        }
    }
    
    protected void drawRandomLine(Graphics g, int count) {
        while (count>0) {
            drawRandomLine(g);
            count--;
        }
    }
    protected void drawRandomLine(Graphics g) {
        int x1 = rd.nextInt(200);
        int y1 = rd.nextInt(60);
        int x2 = rd.nextInt(200);
        int y2 = rd.nextInt(60);
        g.drawLine(x1, y1, x2, y2);
    }
    
    /** Creates a new instance of SimpleCaptcha */
    public BaseCaptcha() {
        this.rd = new Random();
    }
    
    public String generateCaptchaString(int count) {
        RandomString rs = new RandomString();
        return rs.getString(count, "IiOo0");
    }
    
    public BufferedImage getCaptchaImage(String message) {
        return getCaptchaImage(message, WIDTH, HEIGHT);
    }
    
    public BufferedImage getCaptchaImage(String message, int w, int h) {
        BufferedImage bufferImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = null;
        Graphics g2 = null;
        BufferedImage lastBimg = null;
        try {
            g = bufferImg.getGraphics();
            g.setColor(background);
            g.fillRect(0, 0, w, h);
            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
            g.setColor(Color.GRAY);
            drawRandomLine(g, 16);
            drawMessage(g, message);
            //g.drawString(message, 15, 40);
        
            ImageProducer source = bufferImg.getSource();
            ImageFilter filter = new CaptchaFilter();
            ImageProducer producer = new FilteredImageSource(source, filter);
            Image filteredImg = Toolkit.getDefaultToolkit().createImage(producer);
        
            lastBimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            g2 = lastBimg.getGraphics();
            g2.drawImage(filteredImg, 0, 0, null);
        } finally {
            if (g != null) g.dispose();
            if (g2 != null) g2.dispose();
        }
        return lastBimg;
    }    
}
