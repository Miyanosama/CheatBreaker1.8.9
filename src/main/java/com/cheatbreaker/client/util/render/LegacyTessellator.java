package com.cheatbreaker.client.util.render;

import com.cheatbreaker.client.CheatBreaker;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.src.Config;
import org.lwjgl.opengl.GL11;

public class LegacyTessellator {
   public boolean[] recoveredField1397;
   public ByteBuffer recoveredField1398;
   public static LegacyTessellator recoveredField1413 = new LegacyTessellator(2097152);
   public boolean recoveredField1400;
   public boolean recoveredField1401;
   public boolean recoveredField1402;
   public int recoveredField1403;
   public int recoveredField1404;
   public boolean recoveredField1405;
   public double recoveredField1406;
   public int recoveredField1407;
   public TextureAtlasSprite[] recoveredField1408;
   public WorldRenderer recoveredField1409;
   public double recoveredField1410;
   public boolean recoveredField1411;
   public boolean recoveredField1412;
   public static boolean recoveredField1426 = false;
   public int recoveredField1414;
   public int recoveredField1415;
   public int recoveredField1416;
   public boolean recoveredField1417;
   public int[] recoveredField1418;
   public int recoveredField1419;
   public ShortBuffer recoveredField1420;
   public int recoveredField1421;
   public double recoveredField1422;
   public IntBuffer recoveredField1423;
   public double recoveredField1424;
   public WorldVertexBufferUploader recoveredField1425 = new WorldVertexBufferUploader();
   public static boolean recoveredField1399 = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
   public double recoveredField1427;
   public boolean recoveredField1428;
   public FloatBuffer recoveredField1429;
   public boolean recoveredField1430;
   public int recoveredField1431;

   public void method_12119(byte var1, byte var2, byte var3) {
      this.method_12127(var1 & 255, var2 & 255, var3 & 255);
   }

   public void method_12118() {
      this.recoveredField1403 = 0;
      ((Buffer)this.recoveredField1398).clear();
      this.recoveredField1431 = 0;
      this.recoveredField1404 = 0;
   }

   public void method_12122(double var1, double var3, double var5, double var7, double var9) {
      this.method_12120(var7, var9);
      this.method_12121(var1, var3, var5);
   }

   public void method_12130(boolean var1) {
      this.recoveredField1411 = var1;
   }

   public int method_12129(TextureAtlasSprite var1, int var2) {
      var1.bindSpriteTexture();
      int var3 = -1;
      int var4 = -1;
      int var5 = this.recoveredField1404 / 4;

      for (int var6 = var2; var6 < var5; var6++) {
         TextureAtlasSprite var7 = this.recoveredField1408[var6];
         if (var7 == var1) {
            if (var4 < 0) {
               var4 = var6;
            }
         } else if (var4 >= 0) {
            this.method_12137(var4, var6);
            var4 = -1;
            if (var3 < 0) {
               var3 = var6;
            }
         }
      }

      if (var4 >= 0) {
         this.method_12137(var4, var5);
      }

      if (var3 < 0) {
         var3 = var5;
      }

      return var3;
   }

   public void method_12133() {
      this.method_12125(7);
   }

   public void method_12128(int var1, int var2, int var3, int var4) {
      if (!this.recoveredField1401) {
         if (var1 > 255) {
            var1 = 255;
         }

         if (var2 > 255) {
            var2 = 255;
         }

         if (var3 > 255) {
            var3 = 255;
         }

         if (var4 > 255) {
            var4 = 255;
         }

         if (var1 < 0) {
            var1 = 0;
         }

         if (var2 < 0) {
            var2 = 0;
         }

         if (var3 < 0) {
            var3 = 0;
         }

         if (var4 < 0) {
            var4 = 0;
         }

         this.recoveredField1412 = true;
         if (recoveredField1399) {
            this.recoveredField1414 = var4 << 24 | var3 << 16 | var2 << 8 | var1;
         } else {
            this.recoveredField1414 = var1 << 24 | var2 << 16 | var3 << 8 | var4;
         }
      }
   }

   public void method_12126(int var1, int var2) {
      int var3 = var1 >> 16 & 0xFF;
      int var4 = var1 >> 8 & 0xFF;
      int var5 = var1 & 0xFF;
      this.method_12128(var3, var4, var5, var2);
   }

   public static LegacyTessellator method_12115() {
      return recoveredField1413;
   }

   public LegacyTessellator(int var1) {
      this.recoveredField1411 = false;
      this.recoveredField1430 = true;
      this.recoveredField1415 = 0;
      this.recoveredField1417 = true;
      this.recoveredField1397 = new boolean[256];
      this.recoveredField1408 = null;
      this.recoveredField1407 = var1;
      this.recoveredField1398 = GLAllocation.createDirectByteBuffer(var1 * 4);
      this.recoveredField1423 = this.recoveredField1398.asIntBuffer();
      this.recoveredField1429 = this.recoveredField1398.asFloatBuffer();
      this.recoveredField1420 = this.recoveredField1398.asShortBuffer();
      this.recoveredField1418 = new int[var1];
      this.recoveredField1409 = new WorldRenderer(var1);
   }

   public void method_12125(int var1) {
      if (this.recoveredField1405) {
         throw new IllegalStateException("Already tesselating!");
      } else {
         this.recoveredField1405 = true;
         this.method_12118();
         this.recoveredField1416 = var1;
         this.recoveredField1428 = false;
         this.recoveredField1412 = false;
         this.recoveredField1400 = false;
         this.recoveredField1402 = false;
         this.recoveredField1401 = false;
      }
   }

   public void method_12120(double var1, double var3) {
      this.recoveredField1400 = true;
      this.recoveredField1410 = var1;
      this.recoveredField1427 = var3;
   }

   public void method_12123(float var1, float var2, float var3) {
      this.recoveredField1428 = true;
      byte var4 = (byte)(var1 * 127.0F);
      byte var5 = (byte)(var2 * 127.0F);
      byte var6 = (byte)(var3 * 127.0F);
      this.recoveredField1419 = var4 & 255 | (var5 & 255) << 8 | (var6 & 255) << 16;
   }

   public void method_12124(float var1, float var2, float var3, float var4) {
      this.method_12128((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F), (int)(var4 * 255.0F));
   }

   public void method_12136(int var1) {
      this.recoveredField1402 = true;
      this.recoveredField1421 = var1;
   }

   public void method_12135(float var1, float var2, float var3) {
      this.method_12127((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F));
   }

   public boolean method_12132() {
      return this.recoveredField1411;
   }

   public int method_12113() {
      if (!this.recoveredField1405) {
         throw new IllegalStateException("Not tesselating!");
      } else {
         this.recoveredField1405 = false;
         if (this.recoveredField1403 > 0 && (!this.recoveredField1411 || !Config.isMultiTexture())) {
            ((Buffer)this.recoveredField1423).clear();
            this.recoveredField1423.put(this.recoveredField1418, 0, this.recoveredField1431);
            ((Buffer)this.recoveredField1398).position(0);
            ((Buffer)this.recoveredField1398).limit(this.recoveredField1431 * 4);
            if (this.recoveredField1400) {
               ((Buffer)this.recoveredField1429).position(3);
               GL11.glTexCoordPointer(2, 32, this.recoveredField1429);
               GL11.glEnableClientState(32888);
            }

            if (this.recoveredField1402) {
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
               ((Buffer)this.recoveredField1420).position(14);
               GL11.glTexCoordPointer(2, 32, this.recoveredField1420);
               GL11.glEnableClientState(32888);
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            }

            if (this.recoveredField1412) {
               ((Buffer)this.recoveredField1398).position(20);
               GL11.glColorPointer(4, true, 32, this.recoveredField1398);
               GL11.glEnableClientState(32886);
            }

            if (this.recoveredField1428) {
               ((Buffer)this.recoveredField1398).position(24);
               GL11.glNormalPointer(32, this.recoveredField1398);
               GL11.glEnableClientState(32885);
            }

            ((Buffer)this.recoveredField1429).position(0);
            GL11.glVertexPointer(3, 32, this.recoveredField1429);
            GL11.glEnableClientState(32884);
            GL11.glDrawArrays(this.recoveredField1416, 0, this.recoveredField1403);
            GL11.glDisableClientState(32884);
            if (this.recoveredField1400) {
               GL11.glDisableClientState(32888);
            }

            if (this.recoveredField1402) {
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
               GL11.glDisableClientState(32888);
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            }

            if (this.recoveredField1412) {
               GL11.glDisableClientState(32886);
            }

            if (this.recoveredField1428) {
               GL11.glDisableClientState(32885);
            }
         }

         int var1 = this.recoveredField1431 * 4;
         this.method_12118();
         return var1;
      }
   }

   public void method_12114() {
      this.method_12113();
   }

   public void method_12127(int var1, int var2, int var3) {
      this.method_12128(
         var1,
         var2,
         var3,
         CheatBreaker.getInstance() != null
               && CheatBreaker.getInstance().getModuleManager() != null
               && CheatBreaker.getInstance().getModuleManager().xray.isEnabled()
            ? (Integer)CheatBreaker.getInstance().getModuleManager().xray.opacity.getValue()
            : 255
      );
   }

   public void method_12137(int var1, int var2) {
      int var3 = var2 - var1;
      if (var3 > 0) {
         int var4 = var1 * 4;
         int var5 = var3 * 4;
         ((Buffer)this.recoveredField1429).position(3);
         GL11.glTexCoordPointer(2, 32, this.recoveredField1429);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
         ((Buffer)this.recoveredField1420).position(14);
         GL11.glTexCoordPointer(2, 32, this.recoveredField1420);
         GL11.glEnableClientState(32888);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
         ((Buffer)this.recoveredField1398).position(20);
         GL11.glColorPointer(4, true, 32, this.recoveredField1398);
         ((Buffer)this.recoveredField1429).position(0);
         GL11.glVertexPointer(3, 32, this.recoveredField1429);
         GL11.glDrawArrays(this.recoveredField1416, var4, var5);
      }
   }

   public WorldRenderer method_12138() {
      return this.recoveredField1409;
   }

   public void method_12121(double var1, double var3, double var5) {
      if (this.recoveredField1417 && this.recoveredField1431 >= this.recoveredField1407 - 32) {
         Config.dbg("Expand tessellator buffer, old: " + this.recoveredField1407 + ", new: " + this.recoveredField1407 * 2);
         this.recoveredField1407 *= 2;
         int[] var7 = new int[this.recoveredField1407];
         System.arraycopy(this.recoveredField1418, 0, var7, 0, this.recoveredField1418.length);
         this.recoveredField1418 = var7;
         this.recoveredField1398 = GLAllocation.createDirectByteBuffer(this.recoveredField1407 * 4);
         this.recoveredField1423 = this.recoveredField1398.asIntBuffer();
         this.recoveredField1429 = this.recoveredField1398.asFloatBuffer();
         this.recoveredField1420 = this.recoveredField1398.asShortBuffer();
         if (this.recoveredField1408 != null) {
            TextureAtlasSprite[] var8 = new TextureAtlasSprite[this.recoveredField1407 / 4];
            System.arraycopy(this.recoveredField1408, 0, var8, 0, this.recoveredField1408.length);
            this.recoveredField1408 = var8;
         }
      }

      this.recoveredField1404++;
      if (this.recoveredField1400) {
         this.recoveredField1418[this.recoveredField1431 + 3] = Float.floatToRawIntBits((float)this.recoveredField1410);
         this.recoveredField1418[this.recoveredField1431 + 4] = Float.floatToRawIntBits((float)this.recoveredField1427);
      }

      if (this.recoveredField1402) {
         this.recoveredField1418[this.recoveredField1431 + 7] = this.recoveredField1421;
      }

      if (this.recoveredField1412) {
         this.recoveredField1418[this.recoveredField1431 + 5] = this.recoveredField1414;
      }

      if (this.recoveredField1428) {
         this.recoveredField1418[this.recoveredField1431 + 6] = this.recoveredField1419;
      }

      this.recoveredField1418[this.recoveredField1431 + 0] = Float.floatToRawIntBits((float)(var1 + this.recoveredField1406));
      this.recoveredField1418[this.recoveredField1431 + 1] = Float.floatToRawIntBits((float)(var3 + this.recoveredField1422));
      this.recoveredField1418[this.recoveredField1431 + 2] = Float.floatToRawIntBits((float)(var5 + this.recoveredField1424));
      this.recoveredField1431 += 8;
      this.recoveredField1403++;
      if (!this.recoveredField1417 && this.recoveredField1404 % 4 == 0 && this.recoveredField1431 >= this.recoveredField1407 - 32) {
         this.method_12114();
         this.recoveredField1405 = true;
      }
   }

   public void method_12117(int var1) {
      int var2 = var1 >> 16 & 0xFF;
      int var3 = var1 >> 8 & 0xFF;
      int var4 = var1 & 0xFF;
      this.method_12127(var2, var3, var4);
   }

   public LegacyTessellator() {
      this.recoveredField1430 = false;
   }

   public void method_12134(double var1, double var3, double var5) {
      this.recoveredField1406 = var1;
      this.recoveredField1422 = var3;
      this.recoveredField1424 = var5;
   }

   public void method_12131() {
      this.recoveredField1401 = true;
   }

   public void method_12116(float var1, float var2, float var3) {
      this.recoveredField1406 += var1;
      this.recoveredField1422 += var2;
      this.recoveredField1424 += var3;
   }
}
