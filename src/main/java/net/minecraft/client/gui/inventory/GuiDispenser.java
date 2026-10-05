package net.minecraft.client.gui.inventory;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiDispenser extends GuiContainer {
   public IInventory dispenserInventory;
   public static ResourceLocation recoveredField437 = new ResourceLocation("textures/gui/container/dispenser.png");
   public InventoryPlayer recoveredField438;

   public GuiDispenser(InventoryPlayer var1, IInventory var2) {
      super(new ContainerDispenser(var1, var2));
      this.recoveredField438 = var1;
      this.dispenserInventory = var2;
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(recoveredField437);
      int var4 = (this.l - this.f) / 2;
      int var5 = (this.m - this.g) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.f, this.g);
   }

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      String var3 = this.dispenserInventory.getDisplayName().getUnformattedText();
      this.q.drawString(var3, this.f / 2 - this.q.getStringWidth(var3) / 2, 6, 4210752);
      this.q.drawString(this.recoveredField438.getDisplayName().getUnformattedText(), 8, this.g - 96 + 2, 4210752);
   }
}
