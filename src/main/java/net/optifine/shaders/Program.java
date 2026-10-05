package net.optifine.shaders;

import java.nio.IntBuffer;
import java.util.Arrays;
import net.optifine.render.GlAlphaState;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.config.RenderScale;

public class Program {
   public int index;
   public String drawBufSettings;
   public Program programBackup;
   public Boolean[] buffersFlip = new Boolean[8];
   public IntBuffer drawBuffersBuffer;
   public GlAlphaState alphaState;
   public ProgramStage programStage;
   public boolean[] toggleColorTextures = new boolean[8];
   public RenderScale renderScale;
   public GlBlendState blendState;
   public int compositeMipmapSetting;
   public int id;
   public int countInstances;
   public int ref;
   public IntBuffer drawBuffers;
   public String name;

   public int getCountInstances() {
      return this.countInstances;
   }

   public void resetProperties() {
      this.alphaState = null;
      this.blendState = null;
      this.renderScale = null;
      Arrays.fill(this.buffersFlip, null);
   }

   public Boolean[] getBuffersFlip() {
      return this.buffersFlip;
   }

   public void setBlendState(GlBlendState var1) {
      this.blendState = var1;
   }

   public ProgramStage getProgramStage() {
      return this.programStage;
   }

   public void setDrawBuffers(IntBuffer var1) {
      this.drawBuffers = var1;
   }

   public Program(int var1, String var2, ProgramStage var3, Program var4) {
      this.index = var1;
      this.name = var2;
      this.programStage = var3;
      this.programBackup = var4;
   }

   public Program getProgramBackup() {
      return this.programBackup;
   }

   public void setAlphaState(GlAlphaState var1) {
      this.alphaState = var1;
   }

   public void setRef(int var1) {
      this.ref = var1;
   }

   public boolean[] getToggleColorTextures() {
      return this.toggleColorTextures;
   }

   public GlAlphaState getAlphaState() {
      return this.alphaState;
   }

   public int getIndex() {
      return this.index;
   }

   public void setCompositeMipmapSetting(int var1) {
      this.compositeMipmapSetting = var1;
   }

   public String getDrawBufSettings() {
      return this.drawBufSettings;
   }

   public int getId() {
      return this.id;
   }

   public Program(int var1, String var2, ProgramStage var3, boolean var4) {
      this.index = var1;
      this.name = var2;
      this.programStage = var3;
      this.programBackup = var4 ? this : null;
   }

   public void resetId() {
      this.id = 0;
      this.ref = 0;
   }

   public int getCompositeMipmapSetting() {
      return this.compositeMipmapSetting;
   }

   public GlBlendState getBlendState() {
      return this.blendState;
   }

   public String getRealProgramName() {
      if (this.id == 0) {
         return "none";
      } else {
         Program var1;
         for (var1 = this; var1.getRef() != this.id; var1 = var1.getProgramBackup()) {
            if (var1.getProgramBackup() == null || var1.getProgramBackup() == var1) {
               return "unknown";
            }
         }

         return var1.getName();
      }
   }

   public int getRef() {
      return this.ref;
   }

   public void setCountInstances(int var1) {
      this.countInstances = var1;
   }

   public void setId(int var1) {
      this.id = var1;
   }

   public void setRenderScale(RenderScale var1) {
      this.renderScale = var1;
   }

   public void setDrawBufSettings(String var1) {
      this.drawBufSettings = var1;
   }

   public String getName() {
      return this.name;
   }

   public void resetConfiguration() {
      this.drawBufSettings = null;
      this.compositeMipmapSetting = 0;
      this.countInstances = 0;
      if (this.drawBuffersBuffer == null) {
         this.drawBuffersBuffer = Shaders.nextIntBuffer(8);
      }
   }

   public IntBuffer getDrawBuffersBuffer() {
      return this.drawBuffersBuffer;
   }

   @Override
   public String toString() {
      return "name: " + this.name + ", id: " + this.id + ", ref: " + this.ref + ", real: " + this.getRealProgramName();
   }

   public RenderScale getRenderScale() {
      return this.renderScale;
   }

   public void copyFrom(Program var1) {
      this.id = var1.getId();
      this.alphaState = var1.getAlphaState();
      this.blendState = var1.getBlendState();
      this.renderScale = var1.getRenderScale();
      System.arraycopy(var1.getBuffersFlip(), 0, this.buffersFlip, 0, this.buffersFlip.length);
      this.drawBufSettings = var1.getDrawBufSettings();
      this.drawBuffers = var1.getDrawBuffers();
      this.compositeMipmapSetting = var1.getCompositeMipmapSetting();
      this.countInstances = var1.getCountInstances();
      System.arraycopy(var1.getToggleColorTextures(), 0, this.toggleColorTextures, 0, this.toggleColorTextures.length);
   }

   public IntBuffer getDrawBuffers() {
      return this.drawBuffers;
   }
}
