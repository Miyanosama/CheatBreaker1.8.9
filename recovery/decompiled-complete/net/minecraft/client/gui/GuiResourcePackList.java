package net.minecraft.client.gui;

import io.netty.handler.codec.http.HttpResponseStatus;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.command.CommandClone$StaticCloneData;
import net.minecraft.util.EnumChatFormatting;

public abstract class GuiResourcePackList extends GuiListExtended {
   public CommandClone$StaticCloneData field_0000;
   public List<ResourcePackListEntry> field_148204_l;
   public HttpResponseStatus field_0003;
   public Minecraft mc;

   public List<ResourcePackListEntry> getList() {
      return this.field_148204_l;
   }

   @Override
   public int getSize() {
      return this.getList().size();
   }

   @Override
   public void drawListHeader(int var1, int var2, Tessellator var3) {
      String var4 = EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.BOLD + this.getListHeader();
      this.mc.fontRendererObj.drawString(var4, var1 + this.b / 2 - this.mc.fontRendererObj.getStringWidth(var4) / 2, Math.min(this.d + 3, var2), 16777215);
   }

   @Override
   public int getScrollBarX() {
      return this.f - 6;
   }

   @Override
   public int v_() {
      return this.b;
   }

   public ResourcePackListEntry getListEntry(int var1) {
      return this.getList().get(var1);
   }

   public abstract String getListHeader();

   public GuiResourcePackList(Minecraft var1, int var2, int var3, List<ResourcePackListEntry> var4) {
      super(var1, var2, var3, 32, var3 - 55 + 4, 36);
      this.mc = var1;
      this.field_148204_l = var4;
      this.k = false;
      this.setHasListHeader(true, (int)(var1.fontRendererObj.FONT_HEIGHT * 1.5F));
   }
}
