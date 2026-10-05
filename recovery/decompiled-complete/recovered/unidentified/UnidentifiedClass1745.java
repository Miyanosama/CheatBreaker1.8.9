package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import io.netty.handler.codec.socks.SocksAuthStatus;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import net.minecraft.client.gui.MapItemRenderer$Instance;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.command.server.CommandPublishLocalServer;
import net.minecraft.entity.monster.EntityPigZombie$AITargetAggressor;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.src.Config;
import net.optifine.entity.model.ModelAdapterBlaze;
import net.optifine.entity.model.ModelAdapterEnderman;
import net.optifine.util.CacheLocal;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass1745 {
   public boolean[] field_0023;
   public ByteBuffer field_0038;
   public CommandPublishLocalServer field_0020;
   public static boolean field_0035 = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN;
   public boolean field_0007;
   public boolean field_0009;
   public boolean field_0039;
   public int field_0031;
   public int field_0010;
   public boolean field_0041;
   public double field_0006;
   public CacheLocal field_0024;
   public int field_0028;
   public EntityPigZombie$AITargetAggressor field_0016;
   public TextureAtlasSprite[] field_0033;
   public WorldRenderer field_0037;
   public double field_0004;
   public boolean field_0015;
   public boolean field_0026;
   public static UnidentifiedClass1745 field_0036 = new UnidentifiedClass1745(2097152);
   public SocksAuthStatus field_0003;
   public int field_0002;
   public MapItemRenderer$Instance field_0005;
   public int field_0001;
   public int field_0034;
   public boolean field_0027;
   public EntitySnowball field_0032;
   public int[] field_0019;
   public int field_0040;
   public ShortBuffer field_0029;
   public int field_0014;
   public double field_0008;
   public IntBuffer field_0018;
   public double field_0013;
   public WorldVertexBufferUploader field_0012 = new WorldVertexBufferUploader();
   public ModelAdapterBlaze field_0011;
   public ModelAdapterEnderman field_0000;
   public static boolean field_0042 = false;
   public double field_0025;
   public boolean field_0030;
   public FloatBuffer field_0022;
   public boolean field_0017;
   public int field_0021;

   public void method_12119(byte var1, byte var2, byte var3) {
      this.method_12127(var1 & 255, var2 & 255, var3 & 255);
   }

   public void method_12118() {
      this.field_0031 = 0;
      ((Buffer)this.field_0038).clear();
      this.field_0021 = 0;
      this.field_0010 = 0;
   }

   public void method_12122(double var1, double var3, double var5, double var7, double var9) {
      this.method_12120(var7, var9);
      this.method_12121(var1, var3, var5);
   }

   public void method_12130(boolean var1) {
      this.field_0015 = var1;
   }

   public int method_12129(TextureAtlasSprite var1, int var2) {
      var1.bindSpriteTexture();
      int var3 = -1;
      int var4 = -1;
      int var5 = this.field_0010 / 4;

      for (int var6 = var2; var6 < var5; var6++) {
         TextureAtlasSprite var7 = this.field_0033[var6];
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
      if (!this.field_0009) {
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

         this.field_0026 = true;
         if (field_0035) {
            this.field_0002 = var4 << 24 | var3 << 16 | var2 << 8 | var1;
         } else {
            this.field_0002 = var1 << 24 | var2 << 16 | var3 << 8 | var4;
         }
      }
   }

   public void method_12126(int var1, int var2) {
      int var3 = var1 >> 16 & 0xFF;
      int var4 = var1 >> 8 & 0xFF;
      int var5 = var1 & 0xFF;
      this.method_12128(var3, var4, var5, var2);
   }

   public static UnidentifiedClass1745 method_12115() {
      return field_0036;
   }

   public UnidentifiedClass1745(int var1) {
      this.field_0015 = false;
      this.field_0017 = true;
      this.field_0001 = 0;
      this.field_0027 = true;
      this.field_0023 = new boolean[256];
      this.field_0033 = null;
      this.field_0028 = var1;
      this.field_0038 = GLAllocation.createDirectByteBuffer(var1 * 4);
      this.field_0018 = this.field_0038.asIntBuffer();
      this.field_0022 = this.field_0038.asFloatBuffer();
      this.field_0029 = this.field_0038.asShortBuffer();
      this.field_0019 = new int[var1];
      this.field_0037 = new WorldRenderer(var1);
   }

   public void method_12125(int var1) {
      if (this.field_0041) {
         throw new IllegalStateException("Already tesselating!");
      } else {
         this.field_0041 = true;
         this.method_12118();
         this.field_0034 = var1;
         this.field_0030 = false;
         this.field_0026 = false;
         this.field_0007 = false;
         this.field_0039 = false;
         this.field_0009 = false;
      }
   }

   public void method_12120(double var1, double var3) {
      this.field_0007 = true;
      this.field_0004 = var1;
      this.field_0025 = var3;
   }

   public void method_12123(float var1, float var2, float var3) {
      this.field_0030 = true;
      byte var4 = (byte)(var1 * 127.0F);
      byte var5 = (byte)(var2 * 127.0F);
      byte var6 = (byte)(var3 * 127.0F);
      this.field_0040 = var4 & 255 | (var5 & 255) << 8 | (var6 & 255) << 16;
   }

   public void method_12124(float var1, float var2, float var3, float var4) {
      this.method_12128((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F), (int)(var4 * 255.0F));
   }

   public void method_12136(int var1) {
      this.field_0039 = true;
      this.field_0014 = var1;
   }

   public void method_12135(float var1, float var2, float var3) {
      this.method_12127((int)(var1 * 255.0F), (int)(var2 * 255.0F), (int)(var3 * 255.0F));
   }

   public boolean method_12132() {
      return this.field_0015;
   }

   public int method_12113() {
      if (!this.field_0041) {
         throw new IllegalStateException("Not tesselating!");
      } else {
         this.field_0041 = false;
         if (this.field_0031 > 0 && (!this.field_0015 || !Config.isMultiTexture())) {
            ((Buffer)this.field_0018).clear();
            this.field_0018.put(this.field_0019, 0, this.field_0021);
            ((Buffer)this.field_0038).position(0);
            ((Buffer)this.field_0038).limit(this.field_0021 * 4);
            if (this.field_0007) {
               ((Buffer)this.field_0022).position(3);
               GL11.glTexCoordPointer(2, 32, this.field_0022);
               GL11.glEnableClientState(32888);
            }

            if (this.field_0039) {
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
               ((Buffer)this.field_0029).position(14);
               GL11.glTexCoordPointer(2, 32, this.field_0029);
               GL11.glEnableClientState(32888);
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            }

            if (this.field_0026) {
               ((Buffer)this.field_0038).position(20);
               GL11.glColorPointer(4, true, 32, this.field_0038);
               GL11.glEnableClientState(32886);
            }

            if (this.field_0030) {
               ((Buffer)this.field_0038).position(24);
               GL11.glNormalPointer(32, this.field_0038);
               GL11.glEnableClientState(32885);
            }

            ((Buffer)this.field_0022).position(0);
            GL11.glVertexPointer(3, 32, this.field_0022);
            GL11.glEnableClientState(32884);
            GL11.glDrawArrays(this.field_0034, 0, this.field_0031);
            GL11.glDisableClientState(32884);
            if (this.field_0007) {
               GL11.glDisableClientState(32888);
            }

            if (this.field_0039) {
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
               GL11.glDisableClientState(32888);
               OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
            }

            if (this.field_0026) {
               GL11.glDisableClientState(32886);
            }

            if (this.field_0030) {
               GL11.glDisableClientState(32885);
            }
         }

         int var1 = this.field_0021 * 4;
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
         ((Buffer)this.field_0022).position(3);
         GL11.glTexCoordPointer(2, 32, this.field_0022);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
         ((Buffer)this.field_0029).position(14);
         GL11.glTexCoordPointer(2, 32, this.field_0029);
         GL11.glEnableClientState(32888);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
         ((Buffer)this.field_0038).position(20);
         GL11.glColorPointer(4, true, 32, this.field_0038);
         ((Buffer)this.field_0022).position(0);
         GL11.glVertexPointer(3, 32, this.field_0022);
         GL11.glDrawArrays(this.field_0034, var4, var5);
      }
   }

   public WorldRenderer method_12138() {
      return this.field_0037;
   }

   public void method_12121(double var1, double var3, double var5) {
      if (this.field_0027 && this.field_0021 >= this.field_0028 - 32) {
         Config.dbg("Expand tessellator buffer, old: " + this.field_0028 + ", new: " + this.field_0028 * 2);
         this.field_0028 *= 2;
         int[] var7 = new int[this.field_0028];
         System.arraycopy(this.field_0019, 0, var7, 0, this.field_0019.length);
         this.field_0019 = var7;
         this.field_0038 = GLAllocation.createDirectByteBuffer(this.field_0028 * 4);
         this.field_0018 = this.field_0038.asIntBuffer();
         this.field_0022 = this.field_0038.asFloatBuffer();
         this.field_0029 = this.field_0038.asShortBuffer();
         if (this.field_0033 != null) {
            TextureAtlasSprite[] var8 = new TextureAtlasSprite[this.field_0028 / 4];
            System.arraycopy(this.field_0033, 0, var8, 0, this.field_0033.length);
            this.field_0033 = var8;
         }
      }

      this.field_0010++;
      if (this.field_0007) {
         this.field_0019[this.field_0021 + 3] = Float.floatToRawIntBits((float)this.field_0004);
         this.field_0019[this.field_0021 + 4] = Float.floatToRawIntBits((float)this.field_0025);
      }

      if (this.field_0039) {
         this.field_0019[this.field_0021 + 7] = this.field_0014;
      }

      if (this.field_0026) {
         this.field_0019[this.field_0021 + 5] = this.field_0002;
      }

      if (this.field_0030) {
         this.field_0019[this.field_0021 + 6] = this.field_0040;
      }

      this.field_0019[this.field_0021 + 0] = Float.floatToRawIntBits((float)(var1 + this.field_0006));
      this.field_0019[this.field_0021 + 1] = Float.floatToRawIntBits((float)(var3 + this.field_0008));
      this.field_0019[this.field_0021 + 2] = Float.floatToRawIntBits((float)(var5 + this.field_0013));
      this.field_0021 += 8;
      this.field_0031++;
      if (!this.field_0027 && this.field_0010 % 4 == 0 && this.field_0021 >= this.field_0028 - 32) {
         this.method_12114();
         this.field_0041 = true;
      }
   }

   public void method_12117(int var1) {
      int var2 = var1 >> 16 & 0xFF;
      int var3 = var1 >> 8 & 0xFF;
      int var4 = var1 & 0xFF;
      this.method_12127(var2, var3, var4);
   }

   public UnidentifiedClass1745() {
      this.field_0017 = false;
   }

   public void method_12134(double var1, double var3, double var5) {
      this.field_0006 = var1;
      this.field_0008 = var3;
      this.field_0013 = var5;
   }

   public void method_12131() {
      this.field_0009 = true;
   }

   public void method_12116(float var1, float var2, float var3) {
      this.field_0006 += var1;
      this.field_0008 += var2;
      this.field_0013 += var3;
   }
}
