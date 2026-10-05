package net.minecraft.client.shader;

import io.netty.handler.codec.socks.SocksRequest;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.util.JsonException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShaderLinkHelper {
   public static ShaderLinkHelper staticShaderLinkHelper;
   public SocksRequest field_0002;
   public static Logger logger = LogManager.getLogger();

   public static ShaderLinkHelper getStaticShaderLinkHelper() {
      return staticShaderLinkHelper;
   }

   public void deleteShader(ShaderManager var1) {
      var1.getFragmentShaderLoader().deleteShader(var1);
      var1.getVertexShaderLoader().deleteShader(var1);
      OpenGlHelper.glDeleteProgram(var1.getProgram());
   }

   public static void setNewStaticShaderLinkHelper() {
      staticShaderLinkHelper = new ShaderLinkHelper();
   }

   public void linkProgram(ShaderManager var1) {
      var1.getFragmentShaderLoader().attachShader(var1);
      var1.getVertexShaderLoader().attachShader(var1);
      OpenGlHelper.glLinkProgram(var1.getProgram());
      int var2 = OpenGlHelper.glGetProgrami(var1.getProgram(), OpenGlHelper.GL_LINK_STATUS);
      if (var2 == 0) {
         logger.warn(
            "Error encountered when linking program containing VS "
               + var1.getVertexShaderLoader().getShaderFilename()
               + " and FS "
               + var1.getFragmentShaderLoader().getShaderFilename()
               + ". Log output:"
         );
         logger.warn(OpenGlHelper.glGetProgramInfoLog(var1.getProgram(), 32768));
      }
   }

   public int createProgram() {
      int var1 = OpenGlHelper.glCreateProgram();
      if (var1 <= 0) {
         throw new JsonException("Could not create shader program (returned program ID " + var1 + ")");
      } else {
         return var1;
      }
   }
}
