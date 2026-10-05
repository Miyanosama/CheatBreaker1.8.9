package net.minecraft.client.gui;

import net.minecraft.client.renderer.BlockModelRenderer$AmbientOcclusionFace;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.gen.structure.MapGenStructure$1;

public class GuiKeyBindingList$KeyEntry implements GuiListExtended$IGuiListEntry {
   public String keyDesc;
   public MapGenStructure$1 field_0005;
   public KeyBinding keybinding;
   public GuiButton btnReset;
   public GuiButton btnChangeKeyBinding;
   public BlockModelRenderer$AmbientOcclusionFace field_0006;

   @Override
   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      if (this.btnChangeKeyBinding.mousePressed(GuiKeyBindingList.access$100(this.field_148284_a), var2, var3)) {
         GuiKeyBindingList.access$200(this.field_148284_a).buttonId = this.keybinding;
         return true;
      } else if (this.btnReset.mousePressed(GuiKeyBindingList.access$100(this.field_148284_a), var2, var3)) {
         GuiKeyBindingList.access$100(this.field_148284_a).gameSettings.setOptionKeyBinding(this.keybinding, this.keybinding.getKeyCodeDefault());
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
      boolean var9 = GuiKeyBindingList.access$200(this.field_148284_a).buttonId == this.keybinding;
      GuiKeyBindingList.access$100(this.field_148284_a)
         .fontRendererObj
         .drawString(
            this.keyDesc,
            var2 + 90 - GuiKeyBindingList.access$300(this.field_148284_a),
            var3 + var5 / 2 - GuiKeyBindingList.access$100(this.field_148284_a).fontRendererObj.FONT_HEIGHT / 2,
            16777215
         );
      this.btnReset.h = var2 + 190;
      this.btnReset.i = var3;
      this.btnReset.l = this.keybinding.getKeyCode() != this.keybinding.getKeyCodeDefault();
      this.btnReset.drawButton(GuiKeyBindingList.access$100(this.field_148284_a), var6, var7);
      this.btnChangeKeyBinding.h = var2 + 105;
      this.btnChangeKeyBinding.i = var3;
      this.btnChangeKeyBinding.j = GameSettings.getKeyDisplayString(this.keybinding.getKeyCode());
      boolean var10 = false;
      if (this.keybinding.getKeyCode() != 0) {
         for (KeyBinding var14 : GuiKeyBindingList.access$100(this.field_148284_a).gameSettings.keyBindings) {
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

      this.btnChangeKeyBinding.drawButton(GuiKeyBindingList.access$100(this.field_148284_a), var6, var7);
   }

   public GuiKeyBindingList$KeyEntry(GuiKeyBindingList var1, KeyBinding var2) {
      this.field_148284_a = var1;
      super();
      this.keybinding = var2;
      this.keyDesc = I18n.format(var2.getKeyDescription());
      this.btnChangeKeyBinding = new GuiButton(0, 0, 0, 75, 20, I18n.format(var2.getKeyDescription()));
      this.btnReset = new GuiButton(0, 0, 0, 50, 20, I18n.format("controls.reset"));
   }
}
