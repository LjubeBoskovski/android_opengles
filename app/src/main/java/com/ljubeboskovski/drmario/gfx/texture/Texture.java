package com.ljubeboskovski.drmario.gfx.texture;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import android.content.Context;
import android.opengl.GLES30;

import com.ljubeboskovski.drmario.util.TextureHelper;

public class Texture {

    private Context context;
    private int width, height;
    private int handle;

    public Texture(final Context context, final int resourceId) {
        handle = TextureHelper.loadTexture(context, resourceId);
    }

//    private int load(final Context context, final int resourceId) {
//        int[] pixels = null;
//        try {
//            BufferedImage image = ImageIO.read(new FileInputStream(path));
//            width = image.getWidth();
//            height = image.getHeight();
//            pixels = new int[width * height];
//            image.getRGB(0, 0, width, height, pixels, 0, width);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        int[] data = new int[width * height];
//        for (int i = 0; i < width * height; i++) {
//            int a = (pixels[i] & 0xff000000) >> 24;
//            int r = (pixels[i] & 0xff0000) >> 16;
//            int g = (pixels[i] & 0xff00) >> 8;
//            int b = (pixels[i] & 0xff);
//
//            data[i] = a << 24 | b << 16 | g << 8 | r;
//        }
//
//        int result = glGenTextures();
//        glBindTexture(GL10.GL_TEXTURE_2D, result);
//        glTexParameteri(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
//        glTexParameteri(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
//
//        IntBuffer buffer = ByteBuffer.allocateDirect(data.length << 2)
//                .order(ByteOrder.nativeOrder()).asIntBuffer();
//        buffer.put(data).flip();
//
//        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA,
//                GL_UNSIGNED_BYTE, buffer);
//        glBindTexture(GL_TEXTURE_2D, 0);
//        return result;
//    }

    public void bind() {
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, handle);
    }

    public void unbind() {
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, 0);
    }

    public int getTextureID() {
        return handle;
    }

}
