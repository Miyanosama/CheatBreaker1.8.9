package net.optifine.shaders.gui;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.src.Config;
import net.optifine.Lang;
import net.optifine.shaders.IShaderPack;
import net.optifine.shaders.Shaders;
import net.optifine.util.ResUtils;

public class GuiSlotShaders extends GuiSlot {
   public GuiShaders shadersGui;
   public ArrayList shaderslist;
   public int selectedIndex;
   public long lastClickedCached = 0L;

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
      if (var1 != this.selectedIndex || this.p != this.lastClickedCached) {
         String var5 = (String)this.shaderslist.get(var1);
         IShaderPack var6 = Shaders.getShaderPack(var5);
         if (this.checkCompatible(var6, var1)) {
            this.selectIndex(var1);
         }
      }
   }

   public void updateList() {
      this.shaderslist = Shaders.listOfShaders();
      this.selectedIndex = 0;
      int var1 = 0;

      for (int var2 = this.shaderslist.size(); var1 < var2; var1++) {
         if (((String)this.shaderslist.get(var1)).equals(Shaders.currentShaderName)) {
            this.selectedIndex = var1;
            break;
         }
      }
   }

   @Override
   public void drawBackground() {
   }

   public int getSelectedIndex() {
      return this.selectedIndex;
   }

   public GuiSlotShaders(GuiShaders var1, int var2, int var3, int var4, int var5, int var6) {
      super(var1.getMc(), var2, var3, var4, var5, var6);
      this.shadersGui = var1;
      this.updateList();
      this.amountScrolled = 0.0F;
      int var7 = this.selectedIndex * var6;
      int var8 = (var5 - var4) / 2;
      if (var7 > var8) {
         this.scrollBy(var7 - var8);
      }
   }

   @Override
   public int getContentHeight() {
      return this.getSize() * 18;
   }

   @Override
   public int getSize() {
      return this.shaderslist.size();
   }

   @Override
   public int v_() {
      return this.b - 20;
   }

   @Override
   public int getScrollBarX() {
      return this.b - 6;
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      String var7 = (String)this.shaderslist.get(var1);
      if (var7.equals("OFF")) {
         var7 = Lang.get("of.options.shaders.packNone");
      } else if (var7.equals("(internal)")) {
         var7 = Lang.get("of.options.shaders.packDefault");
      }

      this.shadersGui.drawCenteredString(var7, this.b / 2, var3 + 1, 14737632);
   }

   public void selectIndex(int var1) {
      this.selectedIndex = var1;
      this.lastClickedCached = this.p;
      Shaders.setShaderPack((String)this.shaderslist.get(var1));
      Shaders.uninit();
      this.shadersGui.updateButtons();
   }

   @Override
   public boolean isSelected(int var1) {
      return var1 == this.selectedIndex;
   }

   public boolean checkCompatible(IShaderPack var1, final int var2) {
      if (var1 == null) {
         return true;
      } else {
         InputStream var3 = var1.getResourceAsStream("/shaders/shaders.properties");
         Properties var4 = ResUtils.readProperties(var3, "Shaders");
         if (var4 == null) {
            return true;
         } else {
            String var5 = "version.1.8.9";
            String var6 = var4.getProperty(var5);
            if (var6 == null) {
               return true;
            } else {
               var6 = var6.trim();
               String var7 = "M6_pre2";
               int var8 = Config.compareRelease(var7, var6);
               if (var8 >= 0) {
                  return true;
               } else {
                  String var9 = ("HD_U_" + var6).replace('_', ' ');
                  String var10 = I18n.format("of.message.shaders.nv1", var9);
                  String var11 = I18n.format("of.message.shaders.nv2");
                  GuiYesNoCallback var12 = new GuiYesNoCallback() {
                     @Override
                     public void confirmClicked(boolean var1, int var2x) {
                        if (var1) {
                           GuiSlotShaders.this.selectIndex(var2);
                        }

                        GuiSlotShaders.this.a.displayGuiScreen(GuiSlotShaders.this.shadersGui);
                     }
                  };
                  GuiYesNo var13 = new GuiYesNo(var12, var10, var11, 0);
                  this.a.displayGuiScreen(var13);
                  return false;
               }
            }
         }
      }
   }
}
