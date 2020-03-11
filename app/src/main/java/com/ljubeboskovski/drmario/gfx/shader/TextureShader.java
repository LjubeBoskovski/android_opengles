package com.ljubeboskovski.drmario.gfx.shader;

import android.content.Context;
import android.opengl.GLES30;

import com.ljubeboskovski.drmario.Global;
import com.ljubeboskovski.drmario.R;


public class TextureShader extends ShaderProgram {

    private static final int VERTEX_RESOURCE_ID = R.raw.texture_vertex;
    private static final int FRAGMENT_RESOURCE_ID = R.raw.texture_fragment;

    public TextureShader(Context context) {
        super(context, VERTEX_RESOURCE_ID, FRAGMENT_RESOURCE_ID);

        addAttribute("aPosition", Global.SIZE_POSITION, GLES30.GL_FLOAT,
                Global.BYTES_PER_FLOAT, false);
        addAttribute("aColor", Global.SIZE_COLOR, GLES30.GL_FLOAT, Global.BYTES_PER_FLOAT,
                false);
        addAttribute("aTextureCoordinate", Global.SIZE_TEXTURE_COORDS, GLES30.GL_FLOAT,
                Global.BYTES_PER_FLOAT, false);
    }
}
