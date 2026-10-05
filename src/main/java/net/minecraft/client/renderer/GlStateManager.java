package net.minecraft.client.renderer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.src.Config;
import net.optifine.SmartAnimations;
import net.optifine.render.GlAlphaState;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.Shaders;
import net.optifine.util.LockCounter;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class GlStateManager {
   public static GlStateManager.AlphaState alphaState = new GlStateManager.AlphaState();
   public static GlStateManager.BooleanState lightingState = new GlStateManager.BooleanState(2896);
   public static GlStateManager.BooleanState[] lightState = new GlStateManager.BooleanState[8];
   public static GlStateManager.ColorMaterialState colorMaterialState = new GlStateManager.ColorMaterialState();
   public static GlStateManager.BlendState blendState = new GlStateManager.BlendState();
   public static GlStateManager.DepthState depthState = new GlStateManager.DepthState();
   public static GlStateManager.FogState fogState = new GlStateManager.FogState();
   public static GlStateManager.CullState cullState = new GlStateManager.CullState();
   public static GlStateManager.PolygonOffsetState polygonOffsetState = new GlStateManager.PolygonOffsetState();
   public static GlStateManager.ColorLogicState colorLogicState = new GlStateManager.ColorLogicState();
   public static GlStateManager.TexGenState texGenState = new GlStateManager.TexGenState();
   public static GlStateManager.ClearState clearState = new GlStateManager.ClearState();
   public static GlStateManager.StencilState stencilState = new GlStateManager.StencilState();
   public static GlStateManager.BooleanState normalizeState = new GlStateManager.BooleanState(2977);
   public static int activeTextureUnit = 0;
   public static GlStateManager.TextureState[] textureState = new GlStateManager.TextureState[32];
   public static int activeShadeModel = 7425;
   public static GlStateManager.BooleanState rescaleNormalState = new GlStateManager.BooleanState(32826);
   public static GlStateManager.ColorMask colorMaskState = new GlStateManager.ColorMask();
   public static GlStateManager.Color colorState = new GlStateManager.Color();
   public static boolean clearEnabled = true;
   public static LockCounter alphaLock = new LockCounter();
   public static GlAlphaState alphaLockState = new GlAlphaState();
   public static LockCounter blendLock = new LockCounter();
   public static GlBlendState blendLockState = new GlBlendState();
   public static boolean creatingDisplayList = false;

   public static void enableLighting() {
      lightingState.setEnabled();
   }

   public static void deleteTextures(IntBuffer var0) {
      ((Buffer)var0).rewind();

      while (var0.position() < var0.limit()) {
         int var1 = var0.get();
         deleteTexture(var1);
      }

      ((Buffer)var0).rewind();
   }

   public static void disableCull() {
      cullState.cullFace.setDisabled();
   }

   public static int getActiveTextureUnit() {
      return OpenGlHelper.defaultTexUnit + activeTextureUnit;
   }

   public static void bindCurrentTexture() {
      GL11.glBindTexture(3553, textureState[activeTextureUnit].textureName);
   }

   public static void disableAlpha() {
      if (alphaLock.isLocked()) {
         alphaLockState.setDisabled();
      } else {
         alphaState.alphaTest.setDisabled();
      }
   }

   public static void translate(float var0, float var1, float var2) {
      GL11.glTranslatef(var0, var1, var2);
   }

   public static void callLists(IntBuffer var0) {
      GL11.glCallLists(var0);
      if (Config.isShaders() && !creatingDisplayList) {
         int var1 = Shaders.activeProgram.getCountInstances();
         if (var1 > 1) {
            for (int var2 = 1; var2 < var1; var2++) {
               Shaders.uniform_instanceId.setValue(var2);
               GL11.glCallLists(var0);
            }

            Shaders.uniform_instanceId.setValue(0);
         }
      }
   }

   public static void callList(int var0) {
      GL11.glCallList(var0);
      if (Config.isShaders() && !creatingDisplayList) {
         int var1 = Shaders.activeProgram.getCountInstances();
         if (var1 > 1) {
            for (int var2 = 1; var2 < var1; var2++) {
               Shaders.uniform_instanceId.setValue(var2);
               GL11.glCallList(var0);
            }

            Shaders.uniform_instanceId.setValue(0);
         }
      }
   }

   public static void enablePolygonOffset() {
      polygonOffsetState.polygonOffsetFill.setEnabled();
   }

   public static void loadIdentity() {
      GL11.glLoadIdentity();
   }

   public static void glEnableClientState(int var0) {
      GL11.glEnableClientState(var0);
   }

   public static void scale(float var0, float var1, float var2) {
      GL11.glScalef(var0, var1, var2);
   }

   public static void viewport(int var0, int var1, int var2, int var3) {
      GL11.glViewport(var0, var1, var2, var3);
   }

   public static void disableTexture2D() {
      textureState[activeTextureUnit].texture2DState.setDisabled();
   }

   public static void glEnd() {
      GL11.glEnd();
   }

   public static void setFog(int var0) {
      if (var0 != fogState.mode) {
         fogState.mode = var0;
         GL11.glFogi(2917, var0);
         if (Config.isShaders()) {
            Shaders.setFogMode(var0);
         }
      }
   }

   public static void color(float var0, float var1, float var2, float var3) {
      if (var0 != colorState.red || var1 != colorState.green || var2 != colorState.blue || var3 != colorState.alpha) {
         colorState.red = var0;
         colorState.green = var1;
         colorState.blue = var2;
         colorState.alpha = var3;
         GL11.glColor4f(var0, var1, var2, var3);
      }
   }

   public static void setFogEnd(float var0) {
      if (var0 != fogState.end) {
         fogState.end = var0;
         GL11.glFogf(2916, var0);
      }
   }

   public static void lockBlend(GlBlendState var0) {
      if (!blendLock.isLocked()) {
         getBlendState(blendLockState);
         setBlendState(var0);
         blendLock.lock();
      }
   }

   public static void matrixMode(int var0) {
      GL11.glMatrixMode(var0);
   }

   public static int glGetTexLevelParameteri(int var0, int var1, int var2) {
      return GL11.glGetTexLevelParameteri(var0, var1, var2);
   }

   public static void ortho(double var0, double var2, double var4, double var6, double var8, double var10) {
      GL11.glOrtho(var0, var2, var4, var6, var8, var10);
   }

   public static void setFogEnabled(boolean var0) {
      fogState.fog.setState(var0);
   }

   public static boolean isFogEnabled() {
      return fogState.fog.currentState;
   }

   public static void colorMaterial(int var0, int var1) {
      if (var0 != colorMaterialState.face || var1 != colorMaterialState.mode) {
         colorMaterialState.face = var0;
         colorMaterialState.mode = var1;
         GL11.glColorMaterial(var0, var1);
      }
   }

   public static void disableColorMaterial() {
      colorMaterialState.colorMaterial.setDisabled();
   }

   public static void glTexCoordPointer(int var0, int var1, int var2, int var3) {
      GL11.glTexCoordPointer(var0, var1, var2, var3);
   }

   public static void shadeModel(int var0) {
      if (var0 != activeShadeModel) {
         activeShadeModel = var0;
         GL11.glShadeModel(var0);
      }
   }

   public static void enableDepth() {
      depthState.depthTest.setEnabled();
   }

   public static void disableRescaleNormal() {
      rescaleNormalState.setDisabled();
   }

   public static void disableLighting() {
      lightingState.setDisabled();
   }

   public static void glEndList() {
      GL11.glEndList();
      creatingDisplayList = false;
   }

   public static void translate(double var0, double var2, double var4) {
      GL11.glTranslated(var0, var2, var4);
   }

   public static void colorMask(boolean var0, boolean var1, boolean var2, boolean var3) {
      if (var0 != colorMaskState.red || var1 != colorMaskState.green || var2 != colorMaskState.blue || var3 != colorMaskState.alpha) {
         colorMaskState.red = var0;
         colorMaskState.green = var1;
         colorMaskState.blue = var2;
         colorMaskState.alpha = var3;
         GL11.glColorMask(var0, var1, var2, var3);
      }
   }

   public static void lockAlpha(GlAlphaState var0) {
      if (!alphaLock.isLocked()) {
         method_25241(alphaLockState);
         method_25309(var0);
         alphaLock.lock();
      }
   }

   public static void rotate(float var0, float var1, float var2, float var3) {
      GL11.glRotatef(var0, var1, var2, var3);
   }

   public static void deleteTexture(int var0) {
      if (var0 != 0) {
         GL11.glDeleteTextures(var0);

         for (GlStateManager.TextureState var4 : textureState) {
            if (var4.textureName == var0) {
               var4.textureName = 0;
            }
         }
      }
   }

   public static void blendFunc(int var0, int var1) {
      if (blendLock.isLocked()) {
         blendLockState.setFactors(var0, var1);
      } else if (var0 != blendState.srcFactor || var1 != blendState.dstFactor || var0 != blendState.srcFactorAlpha || var1 != blendState.dstFactorAlpha) {
         blendState.srcFactor = var0;
         blendState.dstFactor = var1;
         blendState.srcFactorAlpha = var0;
         blendState.dstFactorAlpha = var1;
         if (Config.isShaders()) {
            Shaders.uniform_blendFunc.setValue(var0, var1, var0, var1);
         }

         GL11.glBlendFunc(var0, var1);
      }
   }

   public static void glTexCoordPointer(int var0, int var1, int var2, ByteBuffer var3) {
      GL11.glTexCoordPointer(var0, var1, var2, var3);
   }

   public static GlStateManager.TexGenCoord texGenCoord(GlStateManager.TexGen var0) {
      switch (var0) {
         case S:
            return texGenState.s;
         case T:
            return texGenState.t;
         case R:
            return texGenState.r;
         case Q:
            return texGenState.q;
         default:
            return texGenState.s;
      }
   }

   public static void disableLight(int var0) {
      lightState[var0].setDisabled();
   }

   public static void pushAttrib() {
      GL11.glPushAttrib(8256);
   }

   public static void tryBlendFuncSeparate(int var0, int var1, int var2, int var3) {
      if (blendLock.isLocked()) {
         blendLockState.setFactors(var0, var1, var2, var3);
      } else if (var0 != blendState.srcFactor || var1 != blendState.dstFactor || var2 != blendState.srcFactorAlpha || var3 != blendState.dstFactorAlpha) {
         blendState.srcFactor = var0;
         blendState.dstFactor = var1;
         blendState.srcFactorAlpha = var2;
         blendState.dstFactorAlpha = var3;
         if (Config.isShaders()) {
            Shaders.uniform_blendFunc.setValue(var0, var1, var2, var3);
         }

         OpenGlHelper.glBlendFunc(var0, var1, var2, var3);
      }
   }

   public static void getBlendState(GlBlendState var0) {
      if (blendLock.isLocked()) {
         var0.setState(blendLockState);
      } else {
         var0.setState(blendState.blend.currentState, blendState.srcFactor, blendState.dstFactor, blendState.srcFactorAlpha, blendState.dstFactorAlpha);
      }
   }

   public static int generateTexture() {
      return GL11.glGenTextures();
   }

   public static void method_25310(GlStateManager.TexGen var0) {
      texGenCoord(var0).textureGen.setDisabled();
   }

   public static void glNewList(int var0, int var1) {
      GL11.glNewList(var0, var1);
      creatingDisplayList = true;
   }

   public static void setFogStart(float var0) {
      if (var0 != fogState.start) {
         fogState.start = var0;
         GL11.glFogf(2915, var0);
      }
   }

   public static void enableLight(int var0) {
      lightState[var0].setEnabled();
   }

   public static void setFogDensity(float var0) {
      if (var0 < 0.0F) {
         var0 = 0.0F;
      }

      if (var0 != fogState.density) {
         fogState.density = var0;
         GL11.glFogf(2914, var0);
         if (Config.isShaders()) {
            Shaders.setFogDensity(var0);
         }
      }
   }

   public static void glVertexPointer(int var0, int var1, int var2, ByteBuffer var3) {
      GL11.glVertexPointer(var0, var1, var2, var3);
   }

   public static void method_25309(GlAlphaState var0) {
      if (alphaLock.isLocked()) {
         alphaLockState.setState(var0);
      } else {
         alphaState.alphaTest.setState(var0.isEnabled());
         alphaFunc(var0.getFunc(), var0.getRef());
      }
   }

   public static void enableBlend() {
      if (blendLock.isLocked()) {
         blendLockState.setEnabled();
      } else {
         blendState.blend.setEnabled();
      }
   }

   public static void scale(double var0, double var2, double var4) {
      GL11.glScaled(var0, var2, var4);
   }

   public static void texGen(GlStateManager.TexGen var0, int var1) {
      GlStateManager.TexGenCoord var2 = texGenCoord(var0);
      if (var1 != var2.param) {
         var2.param = var1;
         GL11.glTexGeni(var2.coord, 9472, var1);
      }
   }

   public static void texGen(GlStateManager.TexGen var0, int var1, FloatBuffer var2) {
      GL11.glTexGen(texGenCoord(var0).coord, var1, var2);
   }

   public static void depthFunc(int var0) {
      if (var0 != depthState.depthFunc) {
         depthState.depthFunc = var0;
         GL11.glDepthFunc(var0);
      }
   }

   public static void enableAlpha() {
      if (alphaLock.isLocked()) {
         alphaLockState.setEnabled();
      } else {
         alphaState.alphaTest.setEnabled();
      }
   }

   static {
      for (int var0 = 0; var0 < 8; var0++) {
         lightState[var0] = new GlStateManager.BooleanState(16384 + var0);
      }

      for (int var1 = 0; var1 < textureState.length; var1++) {
         textureState[var1] = new GlStateManager.TextureState();
      }
   }

   public static void disablePolygonOffset() {
      polygonOffsetState.polygonOffsetFill.setDisabled();
   }

   public static int getBoundTexture() {
      return textureState[activeTextureUnit].textureName;
   }

   public static void glMultiDrawArrays(int var0, IntBuffer var1, IntBuffer var2) {
      GL14.glMultiDrawArrays(var0, var1, var2);
      if (Config.isShaders() && !creatingDisplayList) {
         int var3 = Shaders.activeProgram.getCountInstances();
         if (var3 > 1) {
            for (int var4 = 1; var4 < var3; var4++) {
               Shaders.uniform_instanceId.setValue(var4);
               GL14.glMultiDrawArrays(var0, var1, var2);
            }

            Shaders.uniform_instanceId.setValue(0);
         }
      }
   }

   public static void popMatrix() {
      GL11.glPopMatrix();
   }

   public static void pushMatrix() {
      GL11.glPushMatrix();
   }

   public static void method_25207() {
      normalizeState.setEnabled();
   }

   public static void glTexSubImage2D(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, IntBuffer var8) {
      GL11.glTexSubImage2D(var0, var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public static void enableColorMaterial() {
      colorMaterialState.colorMaterial.setEnabled();
   }

   public static void clear(int var0) {
      if (clearEnabled) {
         GL11.glClear(var0);
      }
   }

   public static void glTexParameteri(int var0, int var1, int var2) {
      GL11.glTexParameteri(var0, var1, var2);
   }

   public static void method_25279() {
      if (Config.isMinecraftThread()) {
         int var0 = GL11.glGetInteger(34016);
         int var1 = GL11.glGetInteger(32873);
         int var2 = getActiveTextureUnit();
         int var3 = getBoundTexture();
         if (var3 > 0 && (var0 != var2 || var1 != var3)) {
            Config.dbg("checkTexture: act: " + var2 + ", glAct: " + var0 + ", tex: " + var3 + ", glTex: " + var1);
         }
      }
   }

   public static void glCopyTexSubImage2D(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      GL11.glCopyTexSubImage2D(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   public static int glGetError() {
      return GL11.glGetError();
   }

   public static void bindTexture(int var0) {
      if (var0 != textureState[activeTextureUnit].textureName) {
         textureState[activeTextureUnit].textureName = var0;
         GL11.glBindTexture(3553, var0);
         if (SmartAnimations.isActive()) {
            SmartAnimations.textureRendered(var0);
         }
      }
   }

   public static void enableTexture2D() {
      textureState[activeTextureUnit].texture2DState.setEnabled();
   }

   public static void color(float var0, float var1, float var2) {
      color(var0, var1, var2, 1.0F);
   }

   public static void disableFog() {
      fogState.fog.setDisabled();
   }

   public static void clearDepth(double var0) {
      if (var0 != clearState.depth) {
         clearState.depth = var0;
         GL11.glClearDepth(var0);
      }
   }

   public static void glDeleteLists(int var0, int var1) {
      GL11.glDeleteLists(var0, var1);
   }

   public static void glColorPointer(int var0, int var1, int var2, int var3) {
      GL11.glColorPointer(var0, var1, var2, var3);
   }

   public static void disableColorLogic() {
      colorLogicState.colorLogicOp.setDisabled();
   }

   public static void glColorPointer(int var0, int var1, int var2, ByteBuffer var3) {
      GL11.glColorPointer(var0, var1, var2, var3);
   }

   public static void glBegin(int var0) {
      GL11.glBegin(var0);
   }

   public static void cullFace(int var0) {
      if (var0 != cullState.mode) {
         cullState.mode = var0;
         GL11.glCullFace(var0);
      }
   }

   public static void disableBlend() {
      if (blendLock.isLocked()) {
         blendLockState.setDisabled();
      } else {
         blendState.blend.setDisabled();
      }
   }

   public static void setBlendState(GlBlendState var0) {
      if (blendLock.isLocked()) {
         blendLockState.setState(var0);
      } else {
         blendState.blend.setState(var0.isEnabled());
         if (!var0.isSeparate()) {
            blendFunc(var0.getSrcFactor(), var0.getDstFactor());
         } else {
            tryBlendFuncSeparate(var0.getSrcFactor(), var0.getDstFactor(), var0.getSrcFactorAlpha(), var0.getDstFactorAlpha());
         }
      }
   }

   public static void method_25218() {
      normalizeState.setDisabled();
   }

   public static void glDisableClientState(int var0) {
      GL11.glDisableClientState(var0);
   }

   public static void glNormalPointer(int var0, int var1, ByteBuffer var2) {
      GL11.glNormalPointer(var0, var1, var2);
   }

   public static void enableFog() {
      fogState.fog.setEnabled();
   }

   public static void alphaFunc(int var0, float var1) {
      if (alphaLock.isLocked()) {
         alphaLockState.setFuncRef(var0, var1);
      } else if (var0 != alphaState.func || var1 != alphaState.ref) {
         alphaState.func = var0;
         alphaState.ref = var1;
         GL11.glAlphaFunc(var0, var1);
      }
   }

   public static void glTexParameterf(int var0, int var1, float var2) {
      GL11.glTexParameterf(var0, var1, var2);
   }

   public static void glDrawArrays(int var0, int var1, int var2) {
      GL11.glDrawArrays(var0, var1, var2);
      if (Config.isShaders() && !creatingDisplayList) {
         int var3 = Shaders.activeProgram.getCountInstances();
         if (var3 > 1) {
            for (int var4 = 1; var4 < var3; var4++) {
               Shaders.uniform_instanceId.setValue(var4);
               GL11.glDrawArrays(var0, var1, var2);
            }

            Shaders.uniform_instanceId.setValue(0);
         }
      }
   }

   public static void glVertexPointer(int var0, int var1, int var2, int var3) {
      GL11.glVertexPointer(var0, var1, var2, var3);
   }

   public static void unlockAlpha() {
      if (alphaLock.unlock()) {
         method_25309(alphaLockState);
      }
   }

   public static void method_25242(GlStateManager.TexGen var0) {
      texGenCoord(var0).textureGen.setEnabled();
   }

   public static void glFogi(int var0, int var1) {
      GL11.glFogi(var0, var1);
   }

   public static void enableColorLogic() {
      colorLogicState.colorLogicOp.setEnabled();
   }

   public static void resetColor() {
      colorState.red = colorState.green = colorState.blue = colorState.alpha = -1.0F;
   }

   public static void enableCull() {
      cullState.cullFace.setEnabled();
   }

   public static void popAttrib() {
      GL11.glPopAttrib();
   }

   public static void enableRescaleNormal() {
      rescaleNormalState.setEnabled();
   }

   public static void glTexImage2D(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, IntBuffer var8) {
      GL11.glTexImage2D(var0, var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public static void disableDepth() {
      depthState.depthTest.setDisabled();
   }

   public static void glFog(int var0, FloatBuffer var1) {
      GL11.glFog(var0, var1);
   }

   public static void doPolygonOffset(float var0, float var1) {
      if (var0 != polygonOffsetState.factor || var1 != polygonOffsetState.units) {
         polygonOffsetState.factor = var0;
         polygonOffsetState.units = var1;
         GL11.glPolygonOffset(var0, var1);
      }
   }

   public static void multMatrix(FloatBuffer var0) {
      GL11.glMultMatrix(var0);
   }

   public static void setActiveTexture(int var0) {
      if (activeTextureUnit != var0 - OpenGlHelper.defaultTexUnit) {
         activeTextureUnit = var0 - OpenGlHelper.defaultTexUnit;
         OpenGlHelper.setActiveTexture(var0);
      }
   }

   public static void clearColor(float var0, float var1, float var2, float var3) {
      if (var0 != clearState.color.red || var1 != clearState.color.green || var2 != clearState.color.blue || var3 != clearState.color.alpha) {
         clearState.color.red = var0;
         clearState.color.green = var1;
         clearState.color.blue = var2;
         clearState.color.alpha = var3;
         GL11.glClearColor(var0, var1, var2, var3);
      }
   }

   public static void glGetTexImage(int var0, int var1, int var2, int var3, IntBuffer var4) {
      GL11.glGetTexImage(var0, var1, var2, var3, var4);
   }

   public static void depthMask(boolean var0) {
      if (var0 != depthState.maskEnabled) {
         depthState.maskEnabled = var0;
         GL11.glDepthMask(var0);
      }
   }

   public static void method_25241(GlAlphaState var0) {
      if (alphaLock.isLocked()) {
         var0.setState(alphaLockState);
      } else {
         var0.setState(alphaState.alphaTest.currentState, alphaState.func, alphaState.ref);
      }
   }

   public static void colorLogicOp(int var0) {
      if (var0 != colorLogicState.opcode) {
         colorLogicState.opcode = var0;
         GL11.glLogicOp(var0);
      }
   }

   public static void unlockBlend() {
      if (blendLock.unlock()) {
         setBlendState(blendLockState);
      }
   }

   public static void getFloat(int var0, FloatBuffer var1) {
      GL11.glGetFloat(var0, var1);
   }

   public static class AlphaState {
      public int func;
      public float ref;
      public GlStateManager.BooleanState alphaTest = new GlStateManager.BooleanState(3008);

      public AlphaState() {
         this.func = 519;
         this.ref = -1.0F;
      }
   }

   public static class BlendState {
      public int dstFactorAlpha;
      public int srcFactorAlpha;
      public int dstFactor;
      public GlStateManager.BooleanState blend = new GlStateManager.BooleanState(3042);
      public int srcFactor = 1;

      public BlendState() {
         this.dstFactor = 0;
         this.srcFactorAlpha = 1;
         this.dstFactorAlpha = 0;
      }
   }

   public static class BooleanState {
      public int capability;
      public boolean currentState = false;

      public void setState(boolean var1) {
         if (var1 != this.currentState) {
            this.currentState = var1;
            if (var1) {
               GL11.glEnable(this.capability);
            } else {
               GL11.glDisable(this.capability);
            }
         }
      }

      public BooleanState(int var1) {
         this.capability = var1;
      }

      public void setDisabled() {
         this.setState(false);
      }

      public void setEnabled() {
         this.setState(true);
      }
   }

   public static class ClearState {
      public GlStateManager.Color color;
      public int field_179204_c;
      public double depth = 1.0;

      public ClearState() {
         this.color = new GlStateManager.Color(0.0F, 0.0F, 0.0F, 0.0F);
         this.field_179204_c = 0;
      }
   }

   public static class Color {
      public float red = 1.0F;
      public float blue;
      public float green = 1.0F;
      public float alpha;

      public Color() {
         this.blue = 1.0F;
         this.alpha = 1.0F;
      }

      public Color(float var1, float var2, float var3, float var4) {
         this.blue = 1.0F;
         this.alpha = 1.0F;
         this.red = var1;
         this.green = var2;
         this.blue = var3;
         this.alpha = var4;
      }
   }

   public static class ColorLogicState {
      public int opcode;
      public GlStateManager.BooleanState colorLogicOp = new GlStateManager.BooleanState(3058);

      public ColorLogicState() {
         this.opcode = 5379;
      }
   }

   public static class ColorMask {
      public boolean red = true;
      public boolean alpha;
      public boolean green = true;
      public boolean blue = true;

      public ColorMask() {
         this.alpha = true;
      }
   }

   public static class ColorMaterialState {
      public int mode;
      public GlStateManager.BooleanState colorMaterial = new GlStateManager.BooleanState(2903);
      public int face = 1032;

      public ColorMaterialState() {
         this.mode = 5634;
      }
   }

   public static class CullState {
      public int mode;
      public GlStateManager.BooleanState cullFace = new GlStateManager.BooleanState(2884);

      public CullState() {
         this.mode = 1029;
      }
   }

   public static class DepthState {
      public boolean maskEnabled;
      public GlStateManager.BooleanState depthTest = new GlStateManager.BooleanState(2929);
      public int depthFunc;

      public DepthState() {
         this.maskEnabled = true;
         this.depthFunc = 513;
      }
   }

   public static class FogState {
      public GlStateManager.BooleanState fog = new GlStateManager.BooleanState(2912);
      public float start;
      public float end;
      public float density;
      public int mode = 2048;

      public FogState() {
         this.density = 1.0F;
         this.start = 0.0F;
         this.end = 1.0F;
      }
   }

   public static class PolygonOffsetState {
      public GlStateManager.BooleanState polygonOffsetLine;
      public float units;
      public GlStateManager.BooleanState polygonOffsetFill = new GlStateManager.BooleanState(32823);
      public float factor;

      public PolygonOffsetState() {
         this.polygonOffsetLine = new GlStateManager.BooleanState(10754);
         this.factor = 0.0F;
         this.units = 0.0F;
      }
   }

   public static class StencilFunc {
      public int field_179081_a = 519;
      public int field_179080_c;
      public int field_179079_b = 0;

      public StencilFunc() {
         this.field_179080_c = -1;
      }
   }

   public static class StencilState {
      public int field_179077_c;
      public int field_179075_e;
      public int field_179076_b;
      public GlStateManager.StencilFunc field_179078_a = new GlStateManager.StencilFunc();
      public int field_179074_d;

      public StencilState() {
         this.field_179076_b = -1;
         this.field_179077_c = 7680;
         this.field_179074_d = 7680;
         this.field_179075_e = 7680;
      }
   }

   public static enum TexGen {
      S,
      T,
      R,
      Q;
   }

   public static class TexGenCoord {
      public int param = -1;
      public GlStateManager.BooleanState textureGen;
      public int coord;

      public TexGenCoord(int var1, int var2) {
         this.coord = var1;
         this.textureGen = new GlStateManager.BooleanState(var2);
      }
   }

   public static class TexGenState {
      public GlStateManager.TexGenCoord s = new GlStateManager.TexGenCoord(8192, 3168);
      public GlStateManager.TexGenCoord r;
      public GlStateManager.TexGenCoord t = new GlStateManager.TexGenCoord(8193, 3169);
      public GlStateManager.TexGenCoord q;

      public TexGenState() {
         this.r = new GlStateManager.TexGenCoord(8194, 3170);
         this.q = new GlStateManager.TexGenCoord(8195, 3171);
      }
   }

   public static class TextureState {
      public int textureName;
      public GlStateManager.BooleanState texture2DState = new GlStateManager.BooleanState(3553);

      public TextureState() {
         this.textureName = 0;
      }
   }
}
