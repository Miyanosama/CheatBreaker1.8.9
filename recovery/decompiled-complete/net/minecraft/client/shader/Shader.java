package net.minecraft.client.shader;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import org.lwjgl.util.vector.Matrix4f;
import org.newsclub.net.unix.NativeUnixSocket;
import recovered.unidentified.UnidentifiedClass3568;

public class Shader {
   public Framebuffer framebufferIn;
   public Matrix4f projectionMatrix;
   public List<Integer> listAuxHeights;
   public List<Object> listAuxFramebuffers = Lists.newArrayList();
   public List<String> listAuxNames = Lists.newArrayList();
   public Framebuffer framebufferOut;
   public ShaderManager manager;
   public UnidentifiedClass3568 field_0006;
   public NativeUnixSocket field_0003;
   public List<Integer> listAuxWidths = Lists.newArrayList();
   public RenderCow field_0000;

   public void loadShader(float var1) {
      this.preLoadShader();
      this.framebufferIn.unbindFramebuffer();
      float var2 = this.framebufferOut.framebufferTextureWidth;
      float var3 = this.framebufferOut.framebufferTextureHeight;
      GlStateManager.viewport(0, 0, (int)var2, (int)var3);
      this.manager.addSamplerTexture("DiffuseSampler", this.framebufferIn);

      for (int var4 = 0; var4 < this.listAuxFramebuffers.size(); var4++) {
         this.manager.addSamplerTexture(this.listAuxNames.get(var4), this.listAuxFramebuffers.get(var4));
         this.manager.getShaderUniformOrDefault("AuxSize" + var4).set(this.listAuxWidths.get(var4).intValue(), this.listAuxHeights.get(var4).intValue());
      }

      this.manager.getShaderUniformOrDefault("ProjMat").set(this.projectionMatrix);
      this.manager.getShaderUniformOrDefault("InSize").set(this.framebufferIn.framebufferTextureWidth, this.framebufferIn.framebufferTextureHeight);
      this.manager.getShaderUniformOrDefault("OutSize").set(var2, var3);
      this.manager.getShaderUniformOrDefault("Time").set(var1);
      Minecraft var9 = Minecraft.getMinecraft();
      this.manager.getShaderUniformOrDefault("ScreenSize").set(var9.displayWidth, var9.displayHeight);
      this.manager.useShader();
      this.framebufferOut.framebufferClear();
      this.framebufferOut.bindFramebuffer(false);
      GlStateManager.depthMask(false);
      GlStateManager.colorMask(true, true, true, true);
      Tessellator var5 = Tessellator.getInstance();
      WorldRenderer var6 = var5.getWorldRenderer();
      var6.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var6.pos(0.0, var3, 500.0).color(255, 255, 255, 255).endVertex();
      var6.pos(var2, var3, 500.0).color(255, 255, 255, 255).endVertex();
      var6.pos(var2, 0.0, 500.0).color(255, 255, 255, 255).endVertex();
      var6.pos(0.0, 0.0, 500.0).color(255, 255, 255, 255).endVertex();
      var5.draw();
      GlStateManager.depthMask(true);
      GlStateManager.colorMask(true, true, true, true);
      this.manager.endShader();
      this.framebufferOut.unbindFramebuffer();
      this.framebufferIn.unbindFramebufferTexture();

      for (Object var8 : this.listAuxFramebuffers) {
         if (var8 instanceof Framebuffer) {
            ((Framebuffer)var8).unbindFramebufferTexture();
         }
      }
   }

   public void preLoadShader() {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableBlend();
      GlStateManager.disableDepth();
      GlStateManager.disableAlpha();
      GlStateManager.disableFog();
      GlStateManager.disableLighting();
      GlStateManager.disableColorMaterial();
      GlStateManager.enableTexture2D();
      GlStateManager.bindTexture(0);
   }

   public void setProjectionMatrix(Matrix4f var1) {
      this.projectionMatrix = var1;
   }

   public ShaderManager getShaderManager() {
      return this.manager;
   }

   public Shader(IResourceManager var1, String var2, Framebuffer var3, Framebuffer var4) {
      this.listAuxHeights = Lists.newArrayList();
      this.manager = new ShaderManager(var1, var2);
      this.framebufferIn = var3;
      this.framebufferOut = var4;
   }

   public void deleteShader() {
      this.manager.deleteShader();
   }

   public void addAuxFramebuffer(String var1, Object var2, int var3, int var4) {
      this.listAuxNames.add(this.listAuxNames.size(), var1);
      this.listAuxFramebuffers.add(this.listAuxFramebuffers.size(), var2);
      this.listAuxWidths.add(this.listAuxWidths.size(), var3);
      this.listAuxHeights.add(this.listAuxHeights.size(), var4);
   }
}
