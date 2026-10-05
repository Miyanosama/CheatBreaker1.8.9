package net.minecraft.client.gui;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler$3;
import net.minecraft.client.renderer.WorldVertexBufferUploader$1;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;

public class GuiResourcePackSelected extends GuiResourcePackList {
   public SoundHandler$3 field_0000;
   public WorldVertexBufferUploader$1 field_0001;

   @Override
   public String getListHeader() {
      return I18n.format("resourcePack.selected.title");
   }

   public GuiResourcePackSelected(Minecraft var1, int var2, int var3, List<ResourcePackListEntry> var4) {
      super(var1, var2, var3, var4);
   }
}
