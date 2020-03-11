package com.ljubeboskovski.drmario.gfx.texture;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLUtils;

import com.ljubeboskovski.drmario.util.TextureHelper;

public class Texture {

    private Context context;
    private int width, height;
    private int handle;

    public Texture(final Context context, final int resourceId) {
        handle = loadTexture(context, resourceId);
    }


    private int loadTexture(final Context context, final int resourceId)
    {
        final int[] textureHandle = new int[1];

        GLES30.glGenTextures(1, textureHandle, 0);

        if (textureHandle[0] == 0)
        {
            throw new RuntimeException("Error generating texture name.");
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;	// No pre-scaling

        // Read in the resource
        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

        // Bind to the texture in OpenGL
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureHandle[0]);

        // Set filtering
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_NEAREST);

        // Load the bitmap into the bound texture.
        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0, bitmap, 0);

        // Recycle the bitmap, since its data has been loaded into OpenGL.
        bitmap.recycle();

        return textureHandle[0];
    }

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
