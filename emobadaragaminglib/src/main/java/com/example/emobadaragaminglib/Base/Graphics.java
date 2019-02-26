package com.example.emobadaragaminglib.Base;

import android.content.res.Resources;
import android.graphics.Paint;

public interface Graphics {
    public static enum ImageFormat {
        ARGB8888, ARGB4444, RGB565
    }

    public Image newImage(int id, ImageFormat format,Resources res);

    public void clearScreen(int color);

    public void drawLine(int x, int y, int x2, int y2, int color);

    public void drawRect(int x, int y, int width, int height, int color);

    public void drawImage(Image image, int x, int y, int srcX, int srcY,
                          int srcWidth, int srcHeight);

    public void drawImage(Image image, int x, int y);
    public void drawImage(Image image, int x, int y,int width,int height);

    void drawString(String text, int x, int y, Paint paint);

    public int getWidth();

    public int getHeight();

    public void drawARGB(int a, int r, int g, int b);

}