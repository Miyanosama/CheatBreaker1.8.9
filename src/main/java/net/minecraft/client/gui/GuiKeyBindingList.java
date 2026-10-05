package net.minecraft.client.gui;

import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.lang3.ArrayUtils;

public class GuiKeyBindingList extends GuiListExtended {
   public int maxListLabelWidth = 0;
   public GuiListExtended.IGuiListEntry[] listEntries;
   public GuiControls recoveredField276;
   public Minecraft recoveredField277;

   public GuiKeyBindingList(GuiControls var1, Minecraft var2) {
      super(var2, var1.l, var1.m, 63, var1.m - 32, 20);
      this.recoveredField276 = var1;
      this.recoveredField277 = var2;
      KeyBinding[] var3 = ArrayUtils.clone(var2.gameSettings.keyBindings);
      this.listEntries = new GuiListExtended.IGuiListEntry[var3.length + KeyBinding.getKeybinds().size()];
      Arrays.sort(var3);
      int var4 = 0;
      String var5 = null;

      for (KeyBinding var9 : var3) {
         String var10 = var9.getKeyCategory();
         if (!var10.equals(var5)) {
            var5 = var10;
            this.listEntries[var4++] = new GuiKeyBindingList.CategoryEntry(var10);
         }

         int var11 = var2.fontRendererObj.getStringWidth(I18n.format(var9.getKeyDescription()));
         if (var11 > this.maxListLabelWidth) {
            this.maxListLabelWidth = var11;
         }

         this.listEntries[var4++] = new GuiKeyBindingList.KeyEntry(var9);
      }
   }

   @Override
   public int getScrollBarX() {
      return super.getScrollBarX() + 15;
   }

   @Override
   public int v_() {
      return super.v_() + 32;
   }

   @Override
   public GuiListExtended.IGuiListEntry getListEntry(int var1) {
      return this.listEntries[var1];
   }

   @Override
   public int getSize() {
      return this.listEntries.length;
   }

   public class CategoryEntry implements GuiListExtended.IGuiListEntry {
      public String labelText;
      public int labelWidth;

      @Override
      public void setSelected(int var1, int var2, int var3) {
      }

      @Override
      public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
         return false;
      }

      @Override
      public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
      }

      public CategoryEntry(String var2) {
         this.labelText = I18n.format(var2);
         this.labelWidth = GuiKeyBindingList.this.recoveredField277.fontRendererObj.getStringWidth(this.labelText);
      }

      @Override
      public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
         GuiKeyBindingList.this.recoveredField277
            .fontRendererObj
            .drawString(
               this.labelText,
               GuiKeyBindingList.this.recoveredField277.currentScreen.l / 2 - this.labelWidth / 2,
               var3 + var5 - GuiKeyBindingList.this.recoveredField277.fontRendererObj.FONT_HEIGHT - 1,
               16777215
            );
      }
   }

   public class KeyEntry implements GuiListExtended.IGuiListEntry {
      public String keyDesc;
      public KeyBinding keybinding;
      public GuiButton btnReset;
      public GuiButton btnChangeKeyBinding;

      @Override
      public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
         if (this.btnChangeKeyBinding.mousePressed(GuiKeyBindingList.this.recoveredField277, var2, var3)) {
            GuiKeyBindingList.this.recoveredField276.buttonId = this.keybinding;
            return true;
         } else if (this.btnReset.mousePressed(GuiKeyBindingList.this.recoveredField277, var2, var3)) {
            GuiKeyBindingList.this.recoveredField277.gameSettings.setOptionKeyBinding(this.keybinding, this.keybinding.getKeyCodeDefault());
            KeyBinding.resetKeyBindingArrayAndHash();
            return true;
         } else {
            return false;
         }
      }

      @Override
      public void setSelected(int var1, int var2, int var3) {
      }

      @Override
      public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
         this.btnChangeKeyBinding.mouseReleased(var2, var3);
         this.btnReset.mouseReleased(var2, var3);
      }

      @Override
      public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
         boolean var9 = GuiKeyBindingList.this.recoveredField276.buttonId == this.keybinding;
         GuiKeyBindingList.this.recoveredField277
            .fontRendererObj
            .drawString(
               this.keyDesc,
               var2 + 90 - GuiKeyBindingList.this.maxListLabelWidth,
               var3 + var5 / 2 - GuiKeyBindingList.this.recoveredField277.fontRendererObj.FONT_HEIGHT / 2,
               16777215
            );
         this.btnReset.h = var2 + 190;
         this.btnReset.i = var3;
         this.btnReset.l = this.keybinding.getKeyCode() != this.keybinding.getKeyCodeDefault();
         this.btnReset.drawButton(GuiKeyBindingList.this.recoveredField277, var6, var7);
         this.btnChangeKeyBinding.h = var2 + 105;
         this.btnChangeKeyBinding.i = var3;
         this.btnChangeKeyBinding.j = GameSettings.getKeyDisplayString(this.keybinding.getKeyCode());
         boolean var10 = false;
         if (this.keybinding.getKeyCode() != 0) {
            for (KeyBinding var14 : GuiKeyBindingList.this.recoveredField277.gameSettings.keyBindings) {
               if (var14 != this.keybinding && var14.getKeyCode() == this.keybinding.getKeyCode()) {
                  var10 = true;
                  break;
               }
            }
         }

         if (var9) {
            this.btnChangeKeyBinding.j = EnumChatFormatting.WHITE
               + "> "
               + EnumChatFormatting.YELLOW
               + this.btnChangeKeyBinding.j
               + EnumChatFormatting.WHITE
               + " <";
         } else if (var10) {
            this.btnChangeKeyBinding.j = EnumChatFormatting.RED + this.btnChangeKeyBinding.j;
         }

         this.btnChangeKeyBinding.drawButton(GuiKeyBindingList.this.recoveredField277, var6, var7);
      }

      public KeyEntry(KeyBinding var2) {
         this.keybinding = var2;
         this.keyDesc = I18n.format(var2.getKeyDescription());
         this.btnChangeKeyBinding = new GuiButton(0, 0, 0, 75, 20, I18n.format(var2.getKeyDescription()));
         this.btnReset = new GuiButton(0, 0, 0, 50, 20, I18n.format("controls.reset"));
      }
   }
}
