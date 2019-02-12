//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lsl.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

public class VerifyCodeUtils {
    public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static Random random = new Random();

    public VerifyCodeUtils() {
    }

    public static String generateVerifyCode(int verifySize) {
        return generateVerifyCode(verifySize, "23456789ABCDEFGHJKLMNPQRSTUVWXYZ");
    }

    public static String generateVerifyCode(int verifySize, String sources) {
        if(sources == null || sources.length() == 0) {
            sources = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
        }

        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);

        for(int i = 0; i < verifySize; ++i) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }

        return verifyCode.toString();
    }

    public static String outputVerifyImage(int w, int h, File outputFile, int verifySize) throws IOException {
        String verifyCode = generateVerifyCode(verifySize);
        outputImage(w, h, outputFile, verifyCode);
        return verifyCode;
    }

    public static String outputVerifyImage(int w, int h, OutputStream os, int verifySize) throws IOException {
        String verifyCode = generateVerifyCode(verifySize);
        outputImage(w, h, os, verifyCode);
        return verifyCode;
    }

    public static void outputImage(int w, int h, File outputFile, String code) throws IOException {
        if(outputFile != null) {
            File dir = outputFile.getParentFile();
            if(!dir.exists()) {
                dir.mkdirs();
            }

            try {
                outputFile.createNewFile();
                FileOutputStream e = new FileOutputStream(outputFile);
                outputImage(w, h, (OutputStream)e, code);
                e.close();
            } catch (IOException var6) {
                throw var6;
            }
        }
    }

    public static void outputImage(int w, int h, OutputStream os, String code) throws IOException {
        int verifySize = code.length();
        BufferedImage image = new BufferedImage(w, h, 1);
        Random rand = new Random();
        Graphics2D g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW};
        float[] fractions = new float[colors.length];

        for(int c = 0; c < colors.length; ++c) {
            colors[c] = colorSpaces[rand.nextInt(colorSpaces.length)];
            fractions[c] = rand.nextFloat();
        }

        Arrays.sort(fractions);
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, w, h);
        Color var20 = getRandColor(200, 250);
        g2.setColor(var20);
        g2.fillRect(0, 2, w, h - 4);
        Random random = new Random();
        g2.setColor(getRandColor(160, 200));

        int area;
        int fontSize;
        int font;
        int chars;
        for(int yawpRate = 0; yawpRate < 20; ++yawpRate) {
            area = random.nextInt(w - 1);
            fontSize = random.nextInt(h - 1);
            font = random.nextInt(6) + 1;
            chars = random.nextInt(12) + 1;
            g2.drawLine(area, fontSize, area + font + 40, fontSize + chars + 20);
        }

        float var21 = 0.05F;
        area = (int)(var21 * (float)w * (float)h);

        int i;
        for(fontSize = 0; fontSize < area; ++fontSize) {
            font = random.nextInt(w);
            chars = random.nextInt(h);
            i = getRandomIntColor();
            image.setRGB(font, chars, i);
        }

        shear(g2, w, h, var20);
        g2.setColor(getRandColor(100, 160));
        fontSize = h - 4;
        Font var22 = new Font("Algerian", 2, fontSize);
        g2.setFont(var22);
        char[] var23 = code.toCharArray();

        for(i = 0; i < verifySize; ++i) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(0.7853981633974483D * rand.nextDouble() * (double)(rand.nextBoolean()?1:-1), (double)(w / verifySize * i + fontSize / 2), (double)(h / 2));
            g2.setTransform(affine);
            g2.drawChars(var23, i, 1, (w - 10) / verifySize * i + 5, h / 2 + fontSize / 2 - 10);
        }

        g2.dispose();
        ImageIO.write(image, "jpg", os);
    }

    private static Color getRandColor(int fc, int bc) {
        if(fc > 255) {
            fc = 255;
        }

        if(bc > 255) {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        int[] var2 = rgb;
        int var3 = rgb.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int c = var2[var4];
            color <<= 8;
            color |= c;
        }

        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];

        for(int i = 0; i < 3; ++i) {
            rgb[i] = random.nextInt(255);
        }

        return rgb;
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {
        int period = random.nextInt(2);
        boolean borderGap = true;
        byte frames = 1;
        int phase = random.nextInt(2);

        for(int i = 0; i < h1; ++i) {
            double d = (double)(period >> 1) * Math.sin((double)i / (double)period + 6.283185307179586D * (double)phase / (double)frames);
            g.copyArea(0, i, w1, 1, (int)d, 0);
            if(borderGap) {
                g.setColor(color);
                g.drawLine((int)d, i, 0, i);
                g.drawLine((int)d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {
        int period = random.nextInt(40) + 10;
        boolean borderGap = true;
        byte frames = 20;
        byte phase = 7;

        for(int i = 0; i < w1; ++i) {
            double d = (double)(period >> 1) * Math.sin((double)i / (double)period + 6.283185307179586D * (double)phase / (double)frames);
            g.copyArea(i, 0, 1, h1, 0, (int)d);
            if(borderGap) {
                g.setColor(color);
                g.drawLine(i, (int)d, i, 0);
                g.drawLine(i, (int)d + h1, i, h1);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        File dir = new File("F:/verifies");
        short w = 200;
        byte h = 80;

        for(int i = 0; i < 50; ++i) {
            String verifyCode = generateVerifyCode(4);
            File file = new File(dir, verifyCode + ".jpg");
            outputImage(w, h, (File)file, verifyCode);
        }

    }
}
