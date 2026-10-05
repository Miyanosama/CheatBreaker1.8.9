package net.minecraft.client.renderer;

import io.netty.handler.ssl.util.SimpleTrustManagerFactory$SimpleTrustManagerFactorySpi;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.entity.EntityBodyHelper;
import net.minecraft.tileentity.TileEntity$1;
import net.minecraft.util.FrameTimer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class GLAllocation {
   public FrameTimer field_0002;
   public SimpleTrustManagerFactory$SimpleTrustManagerFactorySpi field_0004;
   public EntityBodyHelper field_0001;
   public FrameTimer field_0003;
   public TileEntity$1 field_0000;

   public static synchronized void deleteDisplayLists(int var0) {
      GL11.glDeleteLists(var0, 1);
   }

   public static synchronized ByteBuffer createDirectByteBuffer(int var0) {
      return ByteBuffer.allocateDirect(var0).order(ByteOrder.nativeOrder());
   }

   public static IntBuffer createDirectIntBuffer(int var0) {
      return createDirectByteBuffer(var0 << 2).asIntBuffer();
   }

   public static synchronized void deleteDisplayLists(int var0, int var1) {
      GL11.glDeleteLists(var0, var1);
   }

   public static synchronized int generateDisplayLists(int var0) {
      int var1 = GL11.glGenLists(var0);
      if (var1 == 0) {
         int var2 = GL11.glGetError();
         String var3 = "No error code reported";
         if (var2 != 0) {
            var3 = GLU.gluErrorString(var2);
         }

         throw new IllegalStateException("glGenLists returned an ID of 0 for a count of " + var0 + ", GL error (" + var2 + "): " + var3);
      } else {
         return var1;
      }
   }

   public static FloatBuffer createDirectFloatBuffer(int var0) {
      return createDirectByteBuffer(var0 << 2).asFloatBuffer();
   }
}
