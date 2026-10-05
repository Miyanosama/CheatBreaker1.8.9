package net.minecraft.client.shader;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockSilverfish$EnumType$4;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonBlendingMode;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShaderManager {
   public ShaderLoader field_0009;
   public List<Integer> field_0018;
   public boolean isDirty;
   public List<String> field_0015;
   public static ShaderDefault defaultShaderUniform = new ShaderDefault();
   public List<Integer> field_0004;
   public boolean field_0019;
   public ShaderLoader field_0013;
   public static Logger logger = LogManager.getLogger();
   public Map<String, Object> shaderSamplers = Maps.newHashMap();
   public Map<String, ShaderUniform> field_0002;
   public int program;
   public static int currentProgram = -1;
   public List<ShaderUniform> shaderUniforms;
   public List<String> field_0014;
   public String field_0017;
   public static ShaderManager staticShaderManager = null;
   public BlockSilverfish$EnumType$4 field_0006;
   public JsonBlendingMode field_0011;
   public List<Integer> field_0016;
   public static boolean field_148000_e = true;

   public ShaderUniform getShaderUniformOrDefault(String var1) {
      return (ShaderUniform)(this.field_0002.containsKey(var1) ? this.field_0002.get(var1) : defaultShaderUniform);
   }

   public void endShader() {
      OpenGlHelper.glUseProgram(0);
      currentProgram = -1;
      staticShaderManager = null;
      field_148000_e = true;

      for (int var1 = 0; var1 < this.field_0016.size(); var1++) {
         if (this.shaderSamplers.get(this.field_0015.get(var1)) != null) {
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit + var1);
            GlStateManager.bindTexture(0);
         }
      }
   }

   public void addSamplerTexture(String var1, Object var2) {
      if (this.shaderSamplers.containsKey(var1)) {
         this.shaderSamplers.remove(var1);
      }

      this.shaderSamplers.put(var1, var2);
      this.markDirty();
   }

   public void useShader() {
      this.isDirty = false;
      staticShaderManager = this;
      this.field_0011.func_148109_a();
      if (this.program != currentProgram) {
         OpenGlHelper.glUseProgram(this.program);
         currentProgram = this.program;
      }

      if (this.field_0019) {
         GlStateManager.enableCull();
      } else {
         GlStateManager.disableCull();
      }

      for (int var1 = 0; var1 < this.field_0016.size(); var1++) {
         if (this.shaderSamplers.get(this.field_0015.get(var1)) != null) {
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit + var1);
            GlStateManager.enableTexture2D();
            Object var2 = this.shaderSamplers.get(this.field_0015.get(var1));
            int var3 = -1;
            if (var2 instanceof Framebuffer) {
               var3 = ((Framebuffer)var2).framebufferTexture;
            } else if (var2 instanceof ITextureObject) {
               var3 = ((ITextureObject)var2).getGlTextureId();
            } else if (var2 instanceof Integer) {
               var3 = (Integer)var2;
            }

            if (var3 != -1) {
               GlStateManager.bindTexture(var3);
               OpenGlHelper.glUniform1i(OpenGlHelper.glGetUniformLocation(this.program, this.field_0015.get(var1)), var1);
            }
         }
      }

      for (ShaderUniform var5 : this.shaderUniforms) {
         var5.method_13153();
      }
   }

   public void parseUniform(JsonElement var1) {
      JsonObject var2 = JsonUtils.getJsonObject(var1, "uniform");
      String var3 = JsonUtils.getString(var2, "name");
      int var4 = ShaderUniform.parseType(JsonUtils.getString(var2, "type"));
      int var5 = JsonUtils.getInt(var2, "count");
      float[] var6 = new float[Math.max(var5, 16)];
      JsonArray var7 = JsonUtils.getJsonArray(var2, "values");
      if (var7.size() != var5 && var7.size() > 1) {
         throw new JsonException("Invalid amount of values specified (expected " + var5 + ", found " + var7.size() + ")");
      } else {
         int var8 = 0;

         for (JsonElement var10 : var7) {
            try {
               var6[var8] = JsonUtils.getFloat(var10, "value");
            } catch (Exception var13) {
               JsonException var12 = JsonException.func_151379_a(var13);
               var12.func_151380_a("values[" + var8 + "]");
               throw var12;
            }

            var8++;
         }

         if (var5 > 1 && var7.size() == 1) {
            while (var8 < var5) {
               var6[var8] = var6[0];
               var8++;
            }
         }

         int var14 = var5 > 1 && var5 <= 4 && var4 < 8 ? var5 - 1 : 0;
         ShaderUniform var15 = new ShaderUniform(var3, var4 + var14, var5, this);
         if (var4 <= 3) {
            var15.set((int)var6[0], (int)var6[1], (int)var6[2], (int)var6[3]);
         } else if (var4 <= 7) {
            var15.func_148092_b(var6[0], var6[1], var6[2], var6[3]);
         } else {
            var15.set(var6);
         }

         this.shaderUniforms.add(var15);
      }
   }

   public ShaderLoader getFragmentShaderLoader() {
      return this.field_0009;
   }

   public void deleteShader() {
      ShaderLinkHelper.getStaticShaderLinkHelper().deleteShader(this);
   }

   public int getProgram() {
      return this.program;
   }

   public ShaderLoader getVertexShaderLoader() {
      return this.field_0013;
   }

   public void method_29623() {
      int var1 = 0;

      for (int var2 = 0; var1 < this.field_0015.size(); var2++) {
         String var3 = this.field_0015.get(var1);
         int var4 = OpenGlHelper.glGetUniformLocation(this.program, var3);
         if (var4 == -1) {
            logger.warn("Shader " + this.field_0017 + "could not find sampler named " + var3 + " in the specified shader program.");
            this.shaderSamplers.remove(var3);
            this.field_0015.remove(var2);
            var2--;
         } else {
            this.field_0016.add(var4);
         }

         var1++;
      }

      for (ShaderUniform var7 : this.shaderUniforms) {
         String var8 = var7.getShaderName();
         int var5 = OpenGlHelper.glGetUniformLocation(this.program, var8);
         if (var5 == -1) {
            logger.warn("Could not find uniform named " + var8 + " in the specified shader program.");
         } else {
            this.field_0018.add(var5);
            var7.setUniformLocation(var5);
            this.field_0002.put(var8, var7);
         }
      }
   }

   public ShaderUniform method_29622(String var1) {
      return this.field_0002.containsKey(var1) ? this.field_0002.get(var1) : null;
   }

   public ShaderManager(IResourceManager var1, String var2) {
      this.field_0015 = Lists.newArrayList();
      this.field_0016 = Lists.newArrayList();
      this.shaderUniforms = Lists.newArrayList();
      this.field_0018 = Lists.newArrayList();
      this.field_0002 = Maps.newHashMap();
      JsonParser var3 = new JsonParser();
      ResourceLocation var4 = new ResourceLocation("shaders/program/" + var2 + ".json");
      this.field_0017 = var2;
      InputStream var5 = null;

      try {
         var5 = var1.getResource(var4).getInputStream();
         JsonObject var6 = var3.parse(IOUtils.toString(var5, Charsets.UTF_8)).getAsJsonObject();
         String var28 = JsonUtils.getString(var6, "vertex");
         String var8 = JsonUtils.getString(var6, "fragment");
         JsonArray var9 = JsonUtils.getJsonArray(var6, "samplers", (JsonArray)null);
         if (var9 != null) {
            int var10 = 0;

            for (JsonElement var12 : var9) {
               try {
                  this.parseSampler(var12);
               } catch (Exception var25) {
                  JsonException var14 = JsonException.func_151379_a(var25);
                  var14.func_151380_a("samplers[" + var10 + "]");
                  throw var14;
               }

               var10++;
            }
         }

         JsonArray var29 = JsonUtils.getJsonArray(var6, "attributes", (JsonArray)null);
         if (var29 != null) {
            int var30 = 0;
            this.field_0004 = Lists.newArrayListWithCapacity(var29.size());
            this.field_0014 = Lists.newArrayListWithCapacity(var29.size());

            for (JsonElement var13 : var29) {
               try {
                  this.field_0014.add(JsonUtils.getString(var13, "attribute"));
               } catch (Exception var24) {
                  JsonException var15 = JsonException.func_151379_a(var24);
                  var15.func_151380_a("attributes[" + var30 + "]");
                  throw var15;
               }

               var30++;
            }
         } else {
            this.field_0004 = null;
            this.field_0014 = null;
         }

         JsonArray var31 = JsonUtils.getJsonArray(var6, "uniforms", (JsonArray)null);
         if (var31 != null) {
            int var33 = 0;

            for (JsonElement var37 : var31) {
               try {
                  this.parseUniform(var37);
               } catch (Exception var23) {
                  JsonException var16 = JsonException.func_151379_a(var23);
                  var16.func_151380_a("uniforms[" + var33 + "]");
                  throw var16;
               }

               var33++;
            }
         }

         this.field_0011 = JsonBlendingMode.func_148110_a(JsonUtils.getJsonObject(var6, "blend", (JsonObject)null));
         this.field_0019 = JsonUtils.getBoolean(var6, "cull", true);
         this.field_0013 = ShaderLoader.loadShader(var1, ShaderLoader$ShaderType.VERTEX, var28);
         this.field_0009 = ShaderLoader.loadShader(var1, ShaderLoader$ShaderType.FRAGMENT, var8);
         this.program = ShaderLinkHelper.getStaticShaderLinkHelper().createProgram();
         ShaderLinkHelper.getStaticShaderLinkHelper().linkProgram(this);
         this.method_29623();
         if (this.field_0014 != null) {
            for (String var36 : this.field_0014) {
               int var38 = OpenGlHelper.glGetAttribLocation(this.program, var36);
               this.field_0004.add(var38);
            }
         }
      } catch (Exception var26) {
         JsonException var7 = JsonException.func_151379_a(var26);
         var7.func_151381_b(var4.getResourcePath());
         throw var7;
      } finally {
         IOUtils.closeQuietly(var5);
      }

      this.markDirty();
   }

   public void markDirty() {
      this.isDirty = true;
   }

   public void parseSampler(JsonElement var1) {
      JsonObject var2 = JsonUtils.getJsonObject(var1, "sampler");
      String var3 = JsonUtils.getString(var2, "name");
      if (!JsonUtils.method_05470(var2, "file")) {
         this.shaderSamplers.put(var3, null);
         this.field_0015.add(var3);
      } else {
         this.field_0015.add(var3);
      }
   }
}
