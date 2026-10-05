package net.minecraft.client.shader;

import com.google.common.collect.Maps;
import io.netty.channel.sctp.SctpMessage;
import io.netty.channel.sctp.nio.NioSctpServerChannel$2;
import io.netty.util.internal.chmv8.ForkJoinPool$Submitter;
import java.util.Map;
import net.minecraft.client.renderer.OpenGlHelper;

public enum ShaderLoader$ShaderType {
   FRAGMENT("fragment", ".fsh", OpenGlHelper.GL_FRAGMENT_SHADER),
   VERTEX("vertex", ".vsh", OpenGlHelper.GL_VERTEX_SHADER);
   public NioSctpServerChannel$2 field_0004;
   // $VF: synthetic field
   public static ShaderLoader$ShaderType[] $VALUES = new ShaderLoader$ShaderType[]{ShaderLoader$ShaderType.VERTEX, ShaderLoader$ShaderType.FRAGMENT};
   public String shaderName;
   public Map<String, ShaderLoader> loadedShaders = Maps.newHashMap();
   public SctpMessage field_0001;
   public int shaderMode;
   public String shaderExtension;
   public ForkJoinPool$Submitter field_0002;

   public String getShaderExtension() {
      return this.shaderExtension;
   }

   public String getShaderName() {
      return this.shaderName;
   }

   public ShaderLoader$ShaderType(String var3, String var4, int var5) {
      this.shaderName = var3;
      this.shaderExtension = var4;
      this.shaderMode = var5;
   }

   public Map<String, ShaderLoader> getLoadedShaders() {
      return this.loadedShaders;
   }

   public int getShaderMode() {
      return this.shaderMode;
   }
}
