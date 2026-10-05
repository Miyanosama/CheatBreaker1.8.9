package com.cheatbreaker.client.ui.element.profile;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Profile;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.element.ProfileElement;
import com.cheatbreaker.client.ui.element.module.ModulesGuiButtonElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.module.CBProfileCreateGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.channel.socket.oio.OioSocketChannel$1;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.BlockButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomColors;
import org.apache.log4j.or.jms.MessageRenderer;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass5100;

public class ProfilesListElement extends AbstractScrollableElement {
   public BlockButton field_0004;
   public ModulesGuiButtonElement field_0006;
   public ResourceLocation field_0003;
   public OioSocketChannel$1 field_0007;
   public MessageRenderer field_0005;
   public CustomColors field_0001;
   public List<ProfileElement> field_0000 = new ArrayList<>();
   public int field_0002;

   @Override
   public boolean method_03198(AbstractModule var1) {
      return true;
   }

   @Override
   public void method_03200(AbstractModule var1) {
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      RenderUtil.method_22054(
         this.x,
         this.y,
         this.x + this.width,
         this.y + this.height + 2,
         8.0,
         GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0032 : UnidentifiedClass5100.field_0034
      );
      this.preDraw(var1, var2);
      this.field_0009 = 15;

      for (int var4 = 0; var4 < this.field_0000.size(); var4++) {
         ProfileElement var5 = this.field_0000.get(var4);
         var5.setDimensions(this.x + 4, this.y + 4 + var4 * 18, this.width - 12, 18);
         var5.yOffset = this.field_0013;
         var5.handleDrawElement(var1, var2, var3);
         this.field_0009 = this.field_0009 + var5.getHeight();
      }

      boolean var6 = var1 > (this.x + this.width - 92) * this.scale
         && var1 < (this.x + this.width - 6) * this.scale
         && var2 > (this.y + this.field_0009 - 10 + this.field_0013) * this.scale
         && var2 < (this.y + this.field_0009 + 3 + this.field_0013) * this.scale;
      GL11.glColor4f(var6 ? 0.0F : 0.25F, var6 ? 0.8F : 0.25F, var6 ? 0.0F : 0.25F, 0.65F);
      RenderUtil.drawIcon(this.field_0003, 3.5F, this.x + this.width - 15, this.y + this.field_0009 - 6.5F);
      String var7 = (var6 ? "(COPIES CURRENT PROFILE) " : "") + "ADD NEW PROFILE";
      CheatBreaker.getInstance()
         .field_0068
         .drawString(
            var7,
            this.x + this.width - 17 - CheatBreaker.getInstance().field_0068.getStringWidth(var7),
            this.y + this.field_0009 - 7.5F,
            var6 ? 2130738944 : (GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0014 : UnidentifiedClass5100.field_0009)
         );
      this.field_0009 += 10;
      this.field_0006.yOffset = this.field_0013;
      this.field_0006.setDimensions(this.x + this.width - 130, this.y + this.field_0009, 125, 20);
      this.field_0006.handleDrawElement(var1, var2, var3);
      this.postDraw(var1, var2);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      if (this.field_0006.isMouseInside(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));

         try {
            Desktop.getDesktop().open(CheatBreaker.getInstance().getConfigManager().field_0003);
         } catch (IOException | IllegalArgumentException var17) {
            var17.printStackTrace();
         }
      } else {
         for (ProfileElement var5 : this.field_0000) {
            if (var5.isMouseInside(var1, var2)) {
               var5.handleMouseClick(var1, var2, var3);
               return;
            }
         }

         boolean var18 = var1 > (this.x + this.width - 92) * this.scale
            && var1 < (this.x + this.width - 6) * this.scale
            && var2 > (this.y + this.field_0009 - 20 + this.field_0013) * this.scale
            && var2 < (this.y + this.field_0009 - 7 + this.field_0013) * this.scale;
         if (var18) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            Minecraft.getMinecraft().displayGuiScreen(new CBProfileCreateGui(CBModulesGui.instance, this, this.field_0002, this.scale));
         }

         double var19 = this.height - 10;
         double var7 = this.field_0009;
         double var9 = var19 / var7 * 100.0;
         double var11 = var19 / 100.0 * var9;
         double var13 = this.field_0013 / 100.0 * var9;
         boolean var15 = var1 > (this.x + this.width - 9) * this.scale
            && var1 < (this.x + this.width - 3) * this.scale
            && var2 > (this.y + 11 - var13) * this.scale
            && var2 < (this.y + 8 + var11 - var13) * this.scale;
         boolean var16 = var1 > (this.x + this.width - 9) * this.scale
            && var1 < (this.x + this.width - 3) * this.scale
            && var2 > (this.y + 11) * this.scale
            && var2 < (this.y + 6 + var19 - 3.0) * this.scale;
         if (var3 == 0 && var16 || var15) {
            this.field_0001 = true;
         }
      }
   }

   public ProfilesListElement(float var1, int var2, int var3, int var4, int var5) {
      super(var1, var2, var3, var4, var5);
      this.field_0003 = new ResourceLocation("client/icons/plus-64.png");
      this.field_0002 = -12418828;
      int var10006 = this.x + var4 - 120;
      this.field_0006 = new ModulesGuiButtonElement(
         CheatBreaker.getInstance().field_0039, null, "Open Profiles Folder", var10006, this.y + var5 + 4, 110, 28, -12418828, var1
      );
      this.method_03199();
   }

   public void method_03199() {
      new Thread(
            () -> {
               this.field_0000.clear();
               File var1 = new File(
                  Minecraft.getMinecraft().mcDataDir
                     + File.separator
                     + "config"
                     + File.separator
                     + "cheatbreaker-client-"
                     + "1.8.9".replaceAll("\\.", "-")
                     + File.separator
                     + "profiles"
               );
               if (var1.exists()) {
                  for (File var5 : var1.listFiles()) {
                     if (var5.getName().endsWith(".cfg")) {
                        Profile var6 = null;

                        for (Profile var8 : CheatBreaker.getInstance().getConfigManager().field_0006) {
                           if (var5.getName().equals(var8.getName() + ".cfg")) {
                              var6 = var8;
                           }
                        }

                        if (var6 == null) {
                           CheatBreaker.getInstance().getConfigManager().field_0006.add(new Profile(var5.getName().replace(".cfg", ""), false));
                        }
                     }
                  }
               }

               for (Profile var10 : CheatBreaker.getInstance().getConfigManager().field_0006) {
                  this.field_0000.add(new ProfileElement(this, this.field_0002, var10, this.scale));
               }

               this.field_0000.sort((var0, var1x) -> {
                  if (var0.field_0002.getName().equalsIgnoreCase("default")) {
                     return 0;
                  } else {
                     return var0.field_0002.index < var1x.field_0002.index ? -1 : 1;
                  }
               });
            }
         )
         .start();
   }
}
