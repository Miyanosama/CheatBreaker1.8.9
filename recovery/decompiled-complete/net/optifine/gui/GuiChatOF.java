package net.optifine.gui;

import net.minecraft.block.BlockPistonBase$1;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.enchantment.EnchantmentWaterWalker;
import net.minecraft.src.Config;
import net.optifine.shaders.Shaders;

public class GuiChatOF extends GuiChat {
   public static String field_0003;
   public BlockPistonBase$1 field_0000;
   public static String field_0001;
   public EnchantmentWaterWalker field_0002;

   public boolean checkCustomCommand(String var1) {
      if (var1 == null) {
         return false;
      } else {
         var1 = var1.trim();
         if (var1.equals("/reloadShaders")) {
            if (Config.isShaders()) {
               Shaders.uninit();
               Shaders.loadShaderPack();
            }

            return true;
         } else if (var1.equals("/reloadChunks")) {
            this.j.renderGlobal.loadRenderers();
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public void f(String var1) {
      if (this.checkCustomCommand(var1)) {
         this.j.ingameGUI.getChatGUI().addToSentMessages(var1);
      } else {
         super.f(var1);
      }
   }

   public GuiChatOF(GuiChat var1) {
      super(GuiVideoSettings.getGuiChatText(var1));
   }
}
