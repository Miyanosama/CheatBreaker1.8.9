package net.minecraft.client.shader;

import io.netty.handler.ssl.SslProvider;
import java.io.BufferedInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.util.JsonException;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$22;
import org.lwjgl.BufferUtils;

public class ShaderLoader {
   public ShaderLoader$ShaderType shaderType;
   public LogBrokerMonitor$22 field_0005;
   public int shader;
   public InventoryEffectRenderer field_0004;
   public String shaderFilename;
   public int shaderAttachCount = 0;
   public SslProvider field_0006;

   public ShaderLoader(ShaderLoader$ShaderType var1, int var2, String var3) {
      this.shaderType = var1;
      this.shader = var2;
      this.shaderFilename = var3;
   }

   public String getShaderFilename() {
      return this.shaderFilename;
   }

   public void deleteShader(ShaderManager var1) {
      this.shaderAttachCount--;
      if (this.shaderAttachCount <= 0) {
         OpenGlHelper.glDeleteShader(this.shader);
         this.shaderType.getLoadedShaders().remove(this.shaderFilename);
      }
   }

   public void attachShader(ShaderManager var1) {
      this.shaderAttachCount++;
      OpenGlHelper.glAttachShader(var1.getProgram(), this.shader);
   }

   public static ShaderLoader loadShader(IResourceManager var0, ShaderLoader$ShaderType var1, String var2) {
      ShaderLoader var3 = var1.getLoadedShaders().get(var2);
      if (var3 == null) {
         ResourceLocation var4 = new ResourceLocation("shaders/program/" + var2 + var1.getShaderExtension());
         BufferedInputStream var5 = new BufferedInputStream(var0.getResource(var4).getInputStream());
         byte[] var6 = toByteArray(var5);
         ByteBuffer var7 = BufferUtils.createByteBuffer(var6.length);
         var7.put(var6);
         ((Buffer)var7).position(0);
         int var8 = OpenGlHelper.glCreateShader(var1.getShaderMode());
         OpenGlHelper.glShaderSource(var8, var7);
         OpenGlHelper.glCompileShader(var8);
         if (OpenGlHelper.glGetShaderi(var8, OpenGlHelper.GL_COMPILE_STATUS) == 0) {
            String var9 = StringUtils.trim(OpenGlHelper.glGetShaderInfoLog(var8, 32768));
            JsonException var10 = new JsonException("Couldn't compile " + var1.getShaderName() + " program: " + var9);
            var10.func_151381_b(var4.getResourcePath());
            throw var10;
         }

         var3 = new ShaderLoader(var1, var8, var2);
         var1.getLoadedShaders().put(var2, var3);
      }

      return var3;
   }

   public static byte[] toByteArray(BufferedInputStream var0) {
      byte[] var1;
      try {
         var1 = IOUtils.toByteArray(var0);
      } finally {
         var0.close();
      }

      return var1;
   }
}
