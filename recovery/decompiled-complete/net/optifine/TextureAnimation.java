package net.optifine;

import io.netty.handler.codec.spdy.DefaultSpdySynStreamFrame;
import io.netty.handler.codec.spdy.SpdyOrHttpChooser;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Properties;
import net.minecraft.client.multiplayer.ServerData$ServerResourceMode;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.nbt.NBTException;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.shaders.uniform.ShaderUniform1i;
import net.optifine.util.TextureUtils;
import org.java_websocket.SocketChannelIOHelper;
import org.lwjgl.opengl.GL11;

public class TextureAnimation {
   public ByteBuffer interpolateData;
   public String srcTex = null;
   public SpdyOrHttpChooser field_0011;
   public int frameWidth;
   public int frameHeight;
   public byte[] srcData;
   public ShaderUniform1i field_0022;
   public ResourceLocation dstTexLoc;
   public int dstTextId;
   public String dstTex = null;
   public int interpolateSkip;
   public int currentFrameIndex;
   public int dstX;
   public NBTException field_0010;
   public SocketChannelIOHelper field_0017;
   public DefaultSpdySynStreamFrame field_0020;
   public boolean interpolate;
   public TextureAnimationFrame[] frames;
   public boolean active;
   public ServerData$ServerResourceMode field_0019;
   public boolean valid;
   public FolderResourcePack field_0001;
   public ByteBuffer imageData;
   public int dstY;

   public void updateTextureInerpolate(TextureAnimationFrame var1, TextureAnimationFrame var2, double var3) {
      int var5 = this.frameWidth * this.frameHeight * 4;
      int var6 = var5 * var1.index;
      if (var6 + var5 <= this.imageData.limit()) {
         int var7 = var5 * var2.index;
         if (var7 + var5 <= this.imageData.limit()) {
            ((Buffer)this.interpolateData).clear();

            for (int var8 = 0; var8 < var5; var8++) {
               int var9 = this.imageData.get(var6 + var8) & 255;
               int var10 = this.imageData.get(var7 + var8) & 255;
               int var11 = this.mix(var9, var10, var3);
               byte var12 = (byte)var11;
               this.interpolateData.put(var12);
            }

            ((Buffer)this.interpolateData).flip();
            GlStateManager.bindTexture(this.dstTextId);
            GL11.glTexSubImage2D(3553, 0, this.dstX, this.dstY, this.frameWidth, this.frameHeight, 6408, 5121, this.interpolateData);
         }
      }
   }

   public TextureAnimation(String var1, byte[] var2, String var3, ResourceLocation var4, int var5, int var6, int var7, int var8, Properties var9) {
      this.dstTexLoc = null;
      this.dstTextId = -1;
      this.dstX = 0;
      this.dstY = 0;
      this.frameWidth = 0;
      this.frameHeight = 0;
      this.frames = null;
      this.currentFrameIndex = 0;
      this.interpolate = false;
      this.interpolateSkip = 0;
      this.interpolateData = null;
      this.srcData = null;
      this.imageData = null;
      this.active = true;
      this.valid = true;
      this.srcTex = var1;
      this.dstTex = var3;
      this.dstTexLoc = var4;
      this.dstX = var5;
      this.dstY = var6;
      this.frameWidth = var7;
      this.frameHeight = var8;
      int var10 = var7 * var8 * 4;
      if (var2.length % var10 != 0) {
         Config.warn("Invalid animated texture length: " + var2.length + ", frameWidth: " + var7 + ", frameHeight: " + var8);
      }

      this.srcData = var2;
      int var11 = var2.length / var10;
      if (var9.get("tile.0") != null) {
         for (int var12 = 0; var9.get("tile." + var12) != null; var12++) {
            var11 = var12 + 1;
         }
      }

      String var20 = (String)var9.get("duration");
      int var13 = Math.max(Config.parseInt(var20, 1), 1);
      this.frames = new TextureAnimationFrame[var11];

      for (int var14 = 0; var14 < this.frames.length; var14++) {
         String var15 = (String)var9.get("tile." + var14);
         int var16 = Config.parseInt(var15, var14);
         String var17 = (String)var9.get("duration." + var14);
         int var18 = Math.max(Config.parseInt(var17, var13), 1);
         TextureAnimationFrame var19 = new TextureAnimationFrame(var16, var18);
         this.frames[var14] = var19;
      }

      this.interpolate = Config.parseBoolean(var9.getProperty("interpolate"), false);
      this.interpolateSkip = Config.parseInt(var9.getProperty("skip"), 0);
      if (this.interpolate) {
         this.interpolateData = GLAllocation.createDirectByteBuffer(var10);
      }
   }

   public ResourceLocation getDstTexLoc() {
      return this.dstTexLoc;
   }

   public String getSrcTex() {
      return this.srcTex;
   }

   public int getFrameCount() {
      return this.frames.length;
   }

   public int mix(int var1, int var2, double var3) {
      return (int)(var1 * (1.0 - var3) + var2 * var3);
   }

   public TextureAnimationFrame getCurrentFrame() {
      return this.getFrame(this.currentFrameIndex);
   }

   public boolean isActive() {
      return this.active;
   }

   public String getDstTex() {
      return this.dstTex;
   }

   public void updateTexture() {
      if (this.valid) {
         if (this.dstTextId < 0) {
            ITextureObject var1 = TextureUtils.getTexture(this.dstTexLoc);
            if (var1 == null) {
               this.valid = false;
               return;
            }

            this.dstTextId = var1.getGlTextureId();
         }

         if (this.imageData == null) {
            this.imageData = GLAllocation.createDirectByteBuffer(this.srcData.length);
            this.imageData.put(this.srcData);
            ((Buffer)this.imageData).flip();
            this.srcData = null;
         }

         this.active = SmartAnimations.isActive() ? SmartAnimations.method_01901(this.dstTextId) : true;
         if (this.nextFrame() && this.active) {
            int var7 = this.frameWidth * this.frameHeight * 4;
            TextureAnimationFrame var2 = this.getCurrentFrame();
            if (var2 != null) {
               int var3 = var7 * var2.index;
               if (var3 + var7 <= this.imageData.limit()) {
                  if (!this.interpolate || var2.counter <= 0) {
                     ((Buffer)this.imageData).position(var3);
                     GlStateManager.bindTexture(this.dstTextId);
                     GL11.glTexSubImage2D(3553, 0, this.dstX, this.dstY, this.frameWidth, this.frameHeight, 6408, 5121, this.imageData);
                  } else if (this.interpolateSkip <= 1 || var2.counter % this.interpolateSkip == 0) {
                     TextureAnimationFrame var4 = this.getFrame(this.currentFrameIndex + 1);
                     double var5 = 1.0 * var2.counter / var2.duration;
                     this.updateTextureInerpolate(var2, var4, var5);
                  }
               }
            }
         }
      }
   }

   public TextureAnimationFrame getFrame(int var1) {
      if (this.frames.length <= 0) {
         return null;
      } else {
         if (var1 < 0 || var1 >= this.frames.length) {
            var1 = 0;
         }

         return this.frames[var1];
      }
   }

   public boolean nextFrame() {
      TextureAnimationFrame var1 = this.getCurrentFrame();
      if (var1 == null) {
         return false;
      } else {
         var1.counter++;
         if (var1.counter < var1.duration) {
            return this.interpolate;
         } else {
            var1.counter = 0;
            this.currentFrameIndex++;
            if (this.currentFrameIndex >= this.frames.length) {
               this.currentFrameIndex = 0;
            }

            return true;
         }
      }
   }
}
