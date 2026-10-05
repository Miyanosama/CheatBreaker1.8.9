package net.minecraft.util;

import com.cheatbreaker.client.CheatBreaker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent$Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.HoverEvent$Action;
import net.minecraft.item.Item$8;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Crossing2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass4220;

public class ScreenShotHelper {
   public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
   public static IntBuffer pixelBuffer;
   public StructureNetherBridgePieces$Crossing2 field_0002;
   public Item$8 field_0004;
   public static Logger logger = LogManager.getLogger();
   public static int[] pixelValues;

   public static void method_12617(File var0, int var1, int var2, Framebuffer var3) {
      try {
         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0107.getValue()) {
            CheatBreaker.getInstance().method_19741().method_26702("shutter");
         }

         File var4 = new File(var0, "screenshots");
         if (!var4.exists()) {
            var4.mkdir();
         }

         if (OpenGlHelper.isFramebufferEnabled()) {
            var1 = var3.framebufferTextureWidth;
            var2 = var3.framebufferTextureHeight;
         }

         int var5 = var1 * var2;
         if (pixelBuffer == null || pixelBuffer.capacity() < var5) {
            pixelBuffer = BufferUtils.createIntBuffer(var5);
            pixelValues = new int[var5];
         }

         GL11.glPixelStorei(3333, 1);
         GL11.glPixelStorei(3317, 1);
         ((Buffer)pixelBuffer).clear();
         if (OpenGlHelper.isFramebufferEnabled()) {
            GL11.glBindTexture(3553, var3.framebufferTexture);
            GL11.glGetTexImage(3553, 0, 32993, 33639, pixelBuffer);
         } else {
            GL11.glReadPixels(0, 0, var1, var2, 32993, 33639, pixelBuffer);
         }

         pixelBuffer.get(pixelValues);
         int var6 = var1;
         int var7 = var2;
         new Thread(
               () -> {
                  TextureUtil.processPixelValues(pixelValues, var6, var7);
                  BufferedImage var4x = null;
                  if (OpenGlHelper.isFramebufferEnabled()) {
                     var4x = new BufferedImage(var3.framebufferWidth, var3.framebufferHeight, 1);

                     int var5x;
                     for (int var6x = var5x = var3.framebufferTextureHeight - var3.framebufferHeight; var6x < var3.framebufferTextureHeight; var6x++) {
                        for (int var7x = 0; var7x < var3.framebufferWidth; var7x++) {
                           var4x.setRGB(var7x, var6x - var5x, pixelValues[var6x * var3.framebufferTextureWidth + var7x]);
                        }
                     }
                  } else {
                     var4x = new BufferedImage(var6, var7, 1);
                     var4x.setRGB(0, 0, var6, var7, pixelValues, 0, var6);
                  }

                  File var12 = getTimestampedPNGFileForDirectory(var4);

                  try {
                     ImageIO.write(var4x, "png", var12);
                     ChatComponentText var13 = new ChatComponentText(
                        EnumChatFormatting.GOLD
                           + ""
                           + EnumChatFormatting.BOLD
                           + (CheatBreaker.getInstance().getGlobalSettings().field_0049.getValue() ? " [OPN]" : " [Open]")
                     );
                     var13.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent$Action.field_0011, var12.getCanonicalPath()));
                     var13.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent$Action.SHOW_TEXT, new ChatComponentText(var12.getName())));
                     ChatComponentText var14 = new ChatComponentText(
                        EnumChatFormatting.BLUE
                           + ""
                           + EnumChatFormatting.BOLD
                           + (CheatBreaker.getInstance().getGlobalSettings().field_0049.getValue() ? " [CPY]" : " [Copy]")
                     );
                     var14.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent$Action.field_0004, var12.getName()));
                     var14.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent$Action.SHOW_TEXT, new ChatComponentText("Copy the screenshot")));
                     ChatComponentText var8x = new ChatComponentText(
                        EnumChatFormatting.GREEN
                           + ""
                           + EnumChatFormatting.BOLD
                           + (CheatBreaker.getInstance().getGlobalSettings().field_0049.getValue() ? " [UPL]" : " [Upload]")
                     );
                     var8x.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent$Action.field_0003, var12.getName()));
                     var8x.getChatStyle()
                        .setChatHoverEvent(
                           new HoverEvent(HoverEvent$Action.SHOW_TEXT, new ChatComponentText("Upload to " + EnumChatFormatting.GREEN + "imgur.com & open"))
                        );
                     ChatComponentText var9 = new ChatComponentText(
                        "Saved" + (CheatBreaker.getInstance().getGlobalSettings().field_0101.getValue() ? " and copied " : " ") + "screenshot"
                     );
                     var9.getChatStyle().setUnderlined(true);
                     if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0006.getValue()) {
                        var9.appendSibling(var13);
                     }

                     if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0017.getValue()) {
                        var9.appendSibling(var14);
                     }

                     if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0117.getValue()) {
                        var9.appendSibling(var8x);
                     }

                     if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0101.getValue()) {
                        method_12619(var12.getName());
                     }

                     if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0116.getValue()) {
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var9);
                     }
                  } catch (IOException var10) {
                     logger.warn("Couldn't save screenshot");
                  }
               }
            )
            .start();
      } catch (Exception var8) {
         logger.warn("Couldn't save screenshot", var8);
      }
   }

   public static File getTimestampedPNGFileForDirectory(File var0) {
      String var1 = dateFormat.format(new Date());
      int var2 = 1;

      File var3;
      while ((var3 = new File(var0, var1 + (var2 == 1 ? "" : "_" + var2) + ".png")).exists()) {
         var2++;
      }

      return var3;
   }

   public static void method_12619(String var0) {
      File var1 = new File(Minecraft.getMinecraft().mcDataDir + File.separator + "screenshots" + File.separator + var0);
      ArrayList var2 = new ArrayList();
      var2.add(var1);
      CheatBreaker.getInstance().getModuleManager().notifications.queueNotification("Info", "&9Copied &fscreenshot.", 33834961L & 7966672L);
      UnidentifiedClass4220 var3 = new UnidentifiedClass4220(var2);
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(var3, (var0x, var1x) -> System.out.println("Lost ownership"));
   }

   public static IChatComponent saveScreenshot(File var0, int var1, int var2, Framebuffer var3) {
      return saveScreenshot(var0, null, var1, var2, var3);
   }

   public static IChatComponent saveScreenshot(File var0, String var1, int var2, int var3, Framebuffer var4) {
      try {
         File var5 = new File(var0, "screenshots");
         if (!var5.exists()) {
            var5.mkdir();
         }

         if (OpenGlHelper.isFramebufferEnabled()) {
            var2 = var4.framebufferTextureWidth;
            var3 = var4.framebufferTextureHeight;
         }

         int var6 = var2 * var3;
         if (pixelBuffer == null || pixelBuffer.capacity() < var6) {
            pixelBuffer = BufferUtils.createIntBuffer(var6);
            pixelValues = new int[var6];
         }

         GL11.glPixelStorei(3333, 1);
         GL11.glPixelStorei(3317, 1);
         ((Buffer)pixelBuffer).clear();
         if (OpenGlHelper.isFramebufferEnabled()) {
            GL11.glBindTexture(3553, var4.framebufferTexture);
            GL11.glGetTexImage(3553, 0, 32993, 33639, pixelBuffer);
         } else {
            GL11.glReadPixels(0, 0, var2, var3, 32993, 33639, pixelBuffer);
         }

         pixelBuffer.get(pixelValues);
         TextureUtil.processPixelValues(pixelValues, var2, var3);
         BufferedImage var7;
         if (OpenGlHelper.isFramebufferEnabled()) {
            var7 = new BufferedImage(var4.framebufferWidth, var4.framebufferHeight, 1);

            int var8;
            for (int var9 = var8 = var4.framebufferTextureHeight - var4.framebufferHeight; var9 < var4.framebufferTextureHeight; var9++) {
               for (int var10 = 0; var10 < var4.framebufferWidth; var10++) {
                  var7.setRGB(var10, var9 - var8, pixelValues[var9 * var4.framebufferTextureWidth + var10]);
               }
            }
         } else {
            var7 = new BufferedImage(var2, var3, 1);
            var7.setRGB(0, 0, var2, var3, pixelValues, 0, var2);
         }

         File var12 = var1 == null ? getTimestampedPNGFileForDirectory(var5) : new File(var5, var1);
         ImageIO.write(var7, "png", var12);
         ChatComponentText var13 = new ChatComponentText(var12.getName());
         var13.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent$Action.field_0011, var12.getAbsolutePath()));
         var13.getChatStyle().setUnderlined(true);
         return new ChatComponentTranslation("screenshot.success", var13);
      } catch (Exception var11) {
         logger.warn("Couldn't save screenshot", var11);
         return new ChatComponentTranslation("screenshot.failure", var11.getMessage());
      }
   }
}
