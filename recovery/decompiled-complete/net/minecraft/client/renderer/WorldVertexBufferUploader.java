package net.minecraft.client.renderer;

import io.netty.channel.DefaultChannelPipeline$HeadContext;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.List;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumUsage;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.SVertexBuilder;
import org.lwjgl.opengl.GL11;

public class WorldVertexBufferUploader {
   public DefaultChannelPipeline$HeadContext field_0001;
   public S09PacketHeldItemChange field_0000;

   public void draw(WorldRenderer var1) {
      if (var1.method_25859() > 0) {
         if (var1.getDrawMode() == 7 && Config.isQuadsToTriangles()) {
            var1.quadsToTriangles();
         }

         VertexFormat var2 = var1.getVertexFormat();
         int var3 = var2.getNextOffset();
         ByteBuffer var4 = var1.getByteBuffer();
         List var5 = var2.getElements();
         boolean var6 = Reflector.ForgeVertexFormatElementEnumUseage_preDraw.exists();
         boolean var7 = Reflector.ForgeVertexFormatElementEnumUseage_postDraw.exists();

         for (int var8 = 0; var8 < var5.size(); var8++) {
            VertexFormatElement var9 = (VertexFormatElement)var5.get(var8);
            VertexFormatElement$EnumUsage var10 = var9.getUsage();
            if (var6) {
               Reflector.callVoid(var10, Reflector.ForgeVertexFormatElementEnumUseage_preDraw, var2, var8, var3, var4);
            } else {
               int var11 = var9.getType().getGlConstant();
               int var12 = var9.getIndex();
               ((Buffer)var4).position(var2.getOffset(var8));
               switch (WorldVertexBufferUploader$1.field_0005[var10.ordinal()]) {
                  case 1:
                     GL11.glVertexPointer(var9.getElementCount(), var11, var3, var4);
                     GL11.glEnableClientState(32884);
                     break;
                  case 2:
                     OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + var12);
                     GL11.glTexCoordPointer(var9.getElementCount(), var11, var3, var4);
                     GL11.glEnableClientState(32888);
                     OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                     break;
                  case 3:
                     GL11.glColorPointer(var9.getElementCount(), var11, var3, var4);
                     GL11.glEnableClientState(32886);
                     break;
                  case 4:
                     GL11.glNormalPointer(var11, var3, var4);
                     GL11.glEnableClientState(32885);
               }
            }
         }

         if (var1.isMultiTexture()) {
            var1.method_25852();
         } else if (Config.isShaders()) {
            SVertexBuilder.drawArrays(var1.getDrawMode(), 0, var1.method_25859(), var1);
         } else {
            GL11.glDrawArrays(var1.getDrawMode(), 0, var1.method_25859());
         }

         int var13 = 0;

         for (int var14 = var5.size(); var13 < var14; var13++) {
            VertexFormatElement var15 = (VertexFormatElement)var5.get(var13);
            VertexFormatElement$EnumUsage var16 = var15.getUsage();
            if (var7) {
               Reflector.callVoid(var16, Reflector.ForgeVertexFormatElementEnumUseage_postDraw, var2, var13, var3, var4);
            } else {
               int var17 = var15.getIndex();
               switch (WorldVertexBufferUploader$1.field_0005[var16.ordinal()]) {
                  case 1:
                     GL11.glDisableClientState(32884);
                     break;
                  case 2:
                     OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + var17);
                     GL11.glDisableClientState(32888);
                     OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
                     break;
                  case 3:
                     GL11.glDisableClientState(32886);
                     GlStateManager.resetColor();
                     break;
                  case 4:
                     GL11.glDisableClientState(32885);
               }
            }
         }
      }

      var1.reset();
   }
}
