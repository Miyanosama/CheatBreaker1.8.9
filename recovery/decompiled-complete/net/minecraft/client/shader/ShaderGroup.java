package net.minecraft.client.shader;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import junit.swingui.TestSelector$DoubleClickListener;
import net.minecraft.client.gui.achievement.GuiStats$StatsBlock$1;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonException;
import net.minecraft.command.CommandPlaySound;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

public class ShaderGroup {
   public List<Shader> listShaders = Lists.newArrayList();
   public List<Framebuffer> listFramebuffers;
   public Matrix4f projectionMatrix;
   public float field_148036_j;
   public Map<String, Framebuffer> mapFramebuffers = Maps.newHashMap();
   public int mainFramebufferWidth;
   public float field_148037_k;
   public IResourceManager resourceManager;
   public int mainFramebufferHeight;
   public GuiStats$StatsBlock$1 field_0013;
   public TestSelector$DoubleClickListener field_0000;
   public CommandPlaySound field_0007;
   public String shaderGroupName;
   public Framebuffer mainFramebuffer;

   public void parsePass(TextureManager var1, JsonElement var2) {
      JsonObject var3 = JsonUtils.getJsonObject(var2, "pass");
      String var4 = JsonUtils.getString(var3, "name");
      String var5 = JsonUtils.getString(var3, "intarget");
      String var6 = JsonUtils.getString(var3, "outtarget");
      Framebuffer var7 = this.getFramebuffer(var5);
      Framebuffer var8 = this.getFramebuffer(var6);
      if (var7 == null) {
         throw new JsonException("Input target '" + var5 + "' does not exist");
      } else if (var8 == null) {
         throw new JsonException("Output target '" + var6 + "' does not exist");
      } else {
         Shader var9 = this.addShader(var4, var7, var8);
         JsonArray var10 = JsonUtils.getJsonArray(var3, "auxtargets", (JsonArray)null);
         if (var10 != null) {
            int var11 = 0;

            for (JsonElement var13 : var10) {
               try {
                  JsonObject var14 = JsonUtils.getJsonObject(var13, "auxtarget");
                  String var30 = JsonUtils.getString(var14, "name");
                  String var16 = JsonUtils.getString(var14, "id");
                  Framebuffer var17 = this.getFramebuffer(var16);
                  if (var17 == null) {
                     ResourceLocation var18 = new ResourceLocation("textures/effect/" + var16 + ".png");

                     try {
                        this.resourceManager.getResource(var18);
                     } catch (FileNotFoundException var24) {
                        throw new JsonException("Render target or texture '" + var16 + "' does not exist");
                     }

                     var1.bindTexture(var18);
                     ITextureObject var19 = var1.getTexture(var18);
                     int var20 = JsonUtils.getInt(var14, "width");
                     int var21 = JsonUtils.getInt(var14, "height");
                     boolean var22 = JsonUtils.getBoolean(var14, "bilinear");
                     if (var22) {
                        GL11.glTexParameteri(3553, 10241, 9729);
                        GL11.glTexParameteri(3553, 10240, 9729);
                     } else {
                        GL11.glTexParameteri(3553, 10241, 9728);
                        GL11.glTexParameteri(3553, 10240, 9728);
                     }

                     var9.addAuxFramebuffer(var30, var19.getGlTextureId(), var20, var21);
                  } else {
                     var9.addAuxFramebuffer(var30, var17, var17.framebufferTextureWidth, var17.framebufferTextureHeight);
                  }
               } catch (Exception var25) {
                  JsonException var15 = JsonException.func_151379_a(var25);
                  var15.func_151380_a("auxtargets[" + var11 + "]");
                  throw var15;
               }

               var11++;
            }
         }

         JsonArray var26 = JsonUtils.getJsonArray(var3, "uniforms", (JsonArray)null);
         if (var26 != null) {
            int var27 = 0;

            for (JsonElement var29 : var26) {
               try {
                  this.initUniform(var29);
               } catch (Exception var23) {
                  JsonException var31 = JsonException.func_151379_a(var23);
                  var31.func_151380_a("uniforms[" + var27 + "]");
                  throw var31;
               }

               var27++;
            }
         }
      }
   }

   public void initUniform(JsonElement var1) {
      JsonObject var2 = JsonUtils.getJsonObject(var1, "uniform");
      String var3 = JsonUtils.getString(var2, "name");
      ShaderUniform var4 = this.listShaders.get(this.listShaders.size() - 1).getShaderManager().method_29622(var3);
      if (var4 == null) {
         throw new JsonException("Uniform '" + var3 + "' does not exist");
      } else {
         float[] var5 = new float[4];
         int var6 = 0;

         for (JsonElement var8 : JsonUtils.getJsonArray(var2, "values")) {
            try {
               var5[var6] = JsonUtils.getFloat(var8, "value");
            } catch (Exception var11) {
               JsonException var10 = JsonException.func_151379_a(var11);
               var10.func_151380_a("values[" + var6 + "]");
               throw var10;
            }

            var6++;
         }

         switch (var6) {
            case 0:
            default:
               break;
            case 1:
               var4.set(var5[0]);
               break;
            case 2:
               var4.set(var5[0], var5[1]);
               break;
            case 3:
               var4.set(var5[0], var5[1], var5[2]);
               break;
            case 4:
               var4.set(var5[0], var5[1], var5[2], var5[3]);
         }
      }
   }

   public Framebuffer getFramebuffer(String var1) {
      return var1 == null ? null : (var1.equals("minecraft:main") ? this.mainFramebuffer : this.mapFramebuffers.get(var1));
   }

   public void resetProjectionMatrix() {
      this.projectionMatrix = new Matrix4f();
      this.projectionMatrix.setIdentity();
      this.projectionMatrix.m00 = 2.0F / this.mainFramebuffer.framebufferTextureWidth;
      this.projectionMatrix.m11 = 2.0F / -this.mainFramebuffer.framebufferTextureHeight;
      this.projectionMatrix.m22 = -0.0020001999F;
      this.projectionMatrix.m33 = 1.0F;
      this.projectionMatrix.m03 = -1.0F;
      this.projectionMatrix.m13 = 1.0F;
      this.projectionMatrix.m23 = -1.0001999F;
   }

   public ShaderGroup(TextureManager var1, IResourceManager var2, Framebuffer var3, ResourceLocation var4) {
      this.listFramebuffers = Lists.newArrayList();
      this.resourceManager = var2;
      this.mainFramebuffer = var3;
      this.field_148036_j = 0.0F;
      this.field_148037_k = 0.0F;
      this.mainFramebufferWidth = var3.framebufferWidth;
      this.mainFramebufferHeight = var3.framebufferHeight;
      this.shaderGroupName = var4.toString();
      this.resetProjectionMatrix();
      this.parseGroup(var1, var4);
   }

   public void deleteShaderGroup() {
      for (Framebuffer var2 : this.mapFramebuffers.values()) {
         var2.deleteFramebuffer();
      }

      for (Shader var4 : this.listShaders) {
         var4.deleteShader();
      }

      this.listShaders.clear();
   }

   public void addFramebuffer(String var1, int var2, int var3) {
      Framebuffer var4 = new Framebuffer(var2, var3, true);
      var4.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
      this.mapFramebuffers.put(var1, var4);
      if (var2 == this.mainFramebufferWidth && var3 == this.mainFramebufferHeight) {
         this.listFramebuffers.add(var4);
      }
   }

   public void parseGroup(TextureManager var1, ResourceLocation var2) {
      JsonParser var3 = new JsonParser();
      InputStream var4 = null;

      try {
         IResource var5 = this.resourceManager.getResource(var2);
         var4 = var5.getInputStream();
         JsonObject var22 = var3.parse(IOUtils.toString(var4, Charsets.UTF_8)).getAsJsonObject();
         if (JsonUtils.isJsonArray(var22, "targets")) {
            JsonArray var7 = var22.getAsJsonArray("targets");
            int var8 = 0;

            for (JsonElement var10 : var7) {
               try {
                  this.initTarget(var10);
               } catch (Exception var19) {
                  JsonException var12 = JsonException.func_151379_a(var19);
                  var12.func_151380_a("targets[" + var8 + "]");
                  throw var12;
               }

               var8++;
            }
         }

         if (JsonUtils.isJsonArray(var22, "passes")) {
            JsonArray var23 = var22.getAsJsonArray("passes");
            int var24 = 0;

            for (JsonElement var26 : var23) {
               try {
                  this.parsePass(var1, var26);
               } catch (Exception var18) {
                  JsonException var27 = JsonException.func_151379_a(var18);
                  var27.func_151380_a("passes[" + var24 + "]");
                  throw var27;
               }

               var24++;
            }
         }
      } catch (Exception var20) {
         JsonException var6 = JsonException.func_151379_a(var20);
         var6.func_151381_b(var2.getResourcePath());
         throw var6;
      } finally {
         IOUtils.closeQuietly(var4);
      }
   }

   public Shader addShader(String var1, Framebuffer var2, Framebuffer var3) {
      Shader var4 = new Shader(this.resourceManager, var1, var2, var3);
      this.listShaders.add(this.listShaders.size(), var4);
      return var4;
   }

   public String getShaderGroupName() {
      return this.shaderGroupName;
   }

   public void createBindFramebuffers(int var1, int var2) {
      this.mainFramebufferWidth = this.mainFramebuffer.framebufferTextureWidth;
      this.mainFramebufferHeight = this.mainFramebuffer.framebufferTextureHeight;
      this.resetProjectionMatrix();

      for (Shader var4 : this.listShaders) {
         var4.setProjectionMatrix(this.projectionMatrix);
      }

      for (Framebuffer var6 : this.listFramebuffers) {
         var6.createBindFramebuffer(var1, var2);
      }
   }

   public void loadShaderGroup(float var1) {
      if (var1 < this.field_148037_k) {
         this.field_148036_j = this.field_148036_j + (1.0F - this.field_148037_k);
         this.field_148036_j += var1;
      } else {
         this.field_148036_j = this.field_148036_j + (var1 - this.field_148037_k);
      }

      this.field_148037_k = var1;

      while (this.field_148036_j > 20.0F) {
         this.field_148036_j -= 20.0F;
      }

      for (Shader var3 : this.listShaders) {
         var3.loadShader(this.field_148036_j / 20.0F);
      }
   }

   public Framebuffer getFramebufferRaw(String var1) {
      return this.mapFramebuffers.get(var1);
   }

   public void initTarget(JsonElement var1) {
      if (JsonUtils.isString(var1)) {
         this.addFramebuffer(var1.getAsString(), this.mainFramebufferWidth, this.mainFramebufferHeight);
      } else {
         JsonObject var2 = JsonUtils.getJsonObject(var1, "target");
         String var3 = JsonUtils.getString(var2, "name");
         int var4 = JsonUtils.getInt(var2, "width", this.mainFramebufferWidth);
         int var5 = JsonUtils.getInt(var2, "height", this.mainFramebufferHeight);
         if (this.mapFramebuffers.containsKey(var3)) {
            throw new JsonException(var3 + " is already defined");
         }

         this.addFramebuffer(var3, var4, var5);
      }
   }

   public List<Shader> method_20563() {
      return this.listShaders;
   }
}
