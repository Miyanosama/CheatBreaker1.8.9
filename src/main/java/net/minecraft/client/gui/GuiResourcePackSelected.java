package net.minecraft.client.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;

public class GuiResourcePackSelected extends GuiResourcePackList {
   @Override
   public String getListHeader() {
      return I18n.format("resourcePack.selected.title");
   }

   public GuiResourcePackSelected(Minecraft var1, int var2, int var3, List<ResourcePackListEntry> var4) {
      super(var1, var2, var3, var4);
   }
}
