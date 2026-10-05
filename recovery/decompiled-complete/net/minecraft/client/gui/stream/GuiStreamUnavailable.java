package net.minecraft.client.gui.stream;

import com.google.common.collect.Lists;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.model.WeightedBakedModel;
import net.minecraft.client.stream.IStream;
import net.minecraft.client.stream.NullStream;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Session$Type;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import tv.twitch.ErrorCode;

public class GuiStreamUnavailable extends GuiScreen {
   public WeightedBakedModel field_0003;
   public IChatComponent field_152324_f = new ChatComponentTranslation("stream.unavailable.title");
   public GuiScreen parentScreen;
   public GuiStreamUnavailable$Reason field_152326_h;
   public List<ChatComponentTranslation> field_152327_i;
   public static Logger field_152322_a = LogManager.getLogger();
   public List<String> field_152323_r = Lists.newArrayList();

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      int var4 = Math.max((int)(this.m * 0.85 / 2.0 - this.field_152323_r.size() * this.q.FONT_HEIGHT / 2.0F), 50);
      this.drawCenteredString(this.q, this.field_152324_f.getFormattedText(), this.l / 2, var4 - this.q.FONT_HEIGHT * 2, 16777215);

      for (String var6 : this.field_152323_r) {
         this.drawCenteredString(this.q, var6, this.l / 2, var4, 10526880);
         var4 += this.q.FONT_HEIGHT;
      }

      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void initGui() {
      if (this.field_152323_r.isEmpty()) {
         this.field_152323_r.addAll(this.q.listFormattedStringToWidth(this.field_152326_h.func_152561_a().getFormattedText(), (int)(this.l * 0.75F)));
         if (this.field_152327_i != null) {
            this.field_152323_r.add("");

            for (ChatComponentTranslation var2 : this.field_152327_i) {
               this.field_152323_r.add(var2.getUnformattedTextForChat());
            }
         }
      }

      if (this.field_152326_h.func_152559_b() != null) {
         this.n.add(new GuiButton(0, this.l / 2 - 155, this.m - 50, 150, 20, I18n.format("gui.cancel")));
         this.n.add(new GuiButton(1, this.l / 2 - 155 + 160, this.m - 50, 150, 20, I18n.format(this.field_152326_h.func_152559_b().getFormattedText())));
      } else {
         this.n.add(new GuiButton(0, this.l / 2 - 75, this.m - 50, 150, 20, I18n.format("gui.cancel")));
      }
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k == 1) {
            switch (GuiStreamUnavailable$1.field_152577_a[this.field_152326_h.ordinal()]) {
               case 1:
               case 2:
                  this.func_152320_a("https://account.mojang.com/me/settings");
                  break;
               case 3:
                  this.func_152320_a("https://account.mojang.com/migrate");
                  break;
               case 4:
                  this.func_152320_a("http://www.apple.com/osx/");
                  break;
               case 5:
               case 6:
               case 7:
                  this.func_152320_a("http://bugs.mojang.com/browse/MC");
            }
         }

         this.j.displayGuiScreen(this.parentScreen);
      }
   }

   public GuiStreamUnavailable(GuiScreen var1, GuiStreamUnavailable$Reason var2, List<ChatComponentTranslation> var3) {
      this.parentScreen = var1;
      this.field_152326_h = var2;
      this.field_152327_i = var3;
   }

   public GuiStreamUnavailable(GuiScreen var1, GuiStreamUnavailable$Reason var2) {
      this(var1, var2, (List<ChatComponentTranslation>)null);
   }

   public void func_152320_a(String var1) {
      try {
         Class var2 = Class.forName("java.awt.Desktop");
         Object var3 = var2.getMethod("getDesktop").invoke(null);
         var2.getMethod("browse", URI.class).invoke(var3, new URI(var1));
      } catch (Throwable var4) {
         field_152322_a.error("Couldn't open link", var4);
      }
   }

   @Override
   public void a_() {
   }

   public static void func_152321_a(GuiScreen var0) {
      Minecraft var1 = Minecraft.getMinecraft();
      IStream var2 = var1.getTwitchStream();
      if (!OpenGlHelper.framebufferSupported) {
         ArrayList var3 = Lists.newArrayList();
         var3.add(new ChatComponentTranslation("stream.unavailable.no_fbo.version", GL11.glGetString(7938)));
         var3.add(new ChatComponentTranslation("stream.unavailable.no_fbo.blend", GLContext.getCapabilities().GL_EXT_blend_func_separate));
         var3.add(new ChatComponentTranslation("stream.unavailable.no_fbo.arb", GLContext.getCapabilities().GL_ARB_framebuffer_object));
         var3.add(new ChatComponentTranslation("stream.unavailable.no_fbo.ext", GLContext.getCapabilities().GL_EXT_framebuffer_object));
         var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.NO_FBO, var3));
      } else if (var2 instanceof NullStream) {
         if (((NullStream)var2).func_152937_a().getMessage().contains("Can't load AMD 64-bit .dll on a IA 32-bit platform")) {
            var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.LIBRARY_ARCH_MISMATCH));
         } else {
            var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.LIBRARY_FAILURE));
         }
      } else if (!var2.func_152928_D() && var2.func_152912_E() == ErrorCode.TTV_EC_OS_TOO_OLD) {
         switch (GuiStreamUnavailable$1.field_152578_b[Util.getOSType().ordinal()]) {
            case 1:
               var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.UNSUPPORTED_OS_WINDOWS));
               break;
            case 2:
               var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.UNSUPPORTED_OS_MAC));
               break;
            default:
               var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.UNSUPPORTED_OS_OTHER));
         }
      } else if (!var1.getTwitchDetails().containsKey("twitch_access_token")) {
         if (var1.getSession().getSessionType() == Session$Type.LEGACY) {
            var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.ACCOUNT_NOT_MIGRATED));
         } else {
            var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.ACCOUNT_NOT_BOUND));
         }
      } else if (!var2.func_152913_F()) {
         switch (GuiStreamUnavailable$1.field_152579_c[var2.func_152918_H().ordinal()]) {
            case 1:
               var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.FAILED_TWITCH_AUTH));
               break;
            case 2:
            default:
               var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.FAILED_TWITCH_AUTH_ERROR));
         }
      } else if (var2.func_152912_E() != null) {
         List var4 = Arrays.asList(new ChatComponentTranslation("stream.unavailable.initialization_failure.extra", ErrorCode.getString(var2.func_152912_E())));
         var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.INITIALIZATION_FAILURE, var4));
      } else {
         var1.displayGuiScreen(new GuiStreamUnavailable(var0, GuiStreamUnavailable$Reason.UNKNOWN));
      }
   }
}
