package net.optifine.shaders.uniform;

import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.ssl.JettyNpnSslEngine$2;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.optifine.entity.model.ModelAdapterSpider;
import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniform1i extends ShaderUniformBase {
   public ModelAdapterSpider field_0003;
   public S00PacketServerInfo field_0006;
   public static int field_0002;
   public GuiNewChat field_0005;
   public RenderItem field_0000;
   public JettyNpnSslEngine$2 field_0001;
   public int[] programValues;
   public ProtobufDecoder field_0004;

   public ShaderUniform1i(String var1) {
      super(var1);
      this.resetValue();
   }

   public void setValue(int var1) {
      int var2 = this.getProgram();
      int var3 = this.programValues[var2];
      if (var1 != var3) {
         this.programValues[var2] = var1;
         int var4 = this.getLocation();
         if (var4 >= 0) {
            ARBShaderObjects.glUniform1iARB(var4, var1);
            this.checkGLError();
         }
      }
   }

   @Override
   public void onProgramSet(int var1) {
      if (var1 >= this.programValues.length) {
         int[] var2 = this.programValues;
         int[] var3 = new int[var1 + 10];
         System.arraycopy(var2, 0, var3, 0, var2.length);

         for (int var4 = var2.length; var4 < var3.length; var4++) {
            var3[var4] = Integer.MIN_VALUE;
         }

         this.programValues = var3;
      }
   }

   @Override
   public void resetValue() {
      this.programValues = new int[]{Integer.MIN_VALUE};
   }

   public int getValue() {
      int var1 = this.getProgram();
      return this.programValues[var1];
   }
}
