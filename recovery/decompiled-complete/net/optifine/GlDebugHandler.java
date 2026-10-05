package net.optifine;

import io.netty.channel.AbstractChannelHandlerContext$WriteAndFlushTask$1;
import java.nio.IntBuffer;
import net.minecraft.src.Config;
import org.lwjgl.opengl.ARBDebugOutput;
import org.lwjgl.opengl.ARBDebugOutputCallback;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.opengl.ARBDebugOutputCallback.Handler;

public class GlDebugHandler implements Handler {
   public AbstractChannelHandlerContext$WriteAndFlushTask$1 field_0000;

   public String getSeverity(int var1) {
      switch (var1) {
         case 37190:
            return "HIGH";
         case 37191:
            return "MEDIUM";
         case 37192:
            return "LOW";
         default:
            return this.getUnknown(var1);
      }
   }

   public void handleMessage(int var1, int var2, int var3, int var4, String var5) {
      if (!var5.contains("glBindFramebuffer") && !var5.contains("Wide lines") && !var5.contains("shader recompiled")) {
         Config.dbg(
            "[LWJGL] source: "
               + this.getSource(var1)
               + ", type: "
               + this.getType(var2)
               + ", id: "
               + var3
               + ", severity: "
               + this.getSeverity(var4)
               + ", message: "
               + var5
         );
         new Throwable("StackTrace").printStackTrace();
      }
   }

   public String getSource(int var1) {
      switch (var1) {
         case 33350:
            return "API";
         case 33351:
            return "WIN";
         case 33352:
            return "SHADER";
         case 33353:
            return "EXT";
         case 33354:
            return "APP";
         case 33355:
            return "OTHER";
         default:
            return this.getUnknown(var1);
      }
   }

   public String getType(int var1) {
      switch (var1) {
         case 33356:
            return "ERROR";
         case 33357:
            return "DEPRECATED";
         case 33358:
            return "UNDEFINED";
         case 33359:
            return "PORTABILITY";
         case 33360:
            return "PERFORMANCE";
         case 33361:
            return "OTHER";
         default:
            return this.getUnknown(var1);
      }
   }

   public String getUnknown(int var1) {
      return "Unknown (0x" + Integer.toHexString(var1).toUpperCase() + ")";
   }

   public static void createDisplayDebug() {
      boolean var0 = GLContext.getCapabilities().GL_ARB_debug_output;
      ContextAttribs var1 = new ContextAttribs().withDebug(true);
      Display.create(new PixelFormat().withDepthBits(24), var1);
      ARBDebugOutput.glDebugMessageCallbackARB(new ARBDebugOutputCallback(new GlDebugHandler()));
      ARBDebugOutput.glDebugMessageControlARB(4352, 4352, 4352, (IntBuffer)null, true);
      GL11.glEnable(33346);
   }
}
