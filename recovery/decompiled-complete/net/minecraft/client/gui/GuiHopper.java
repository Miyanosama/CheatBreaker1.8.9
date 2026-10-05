package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.optifine.entity.model.CustomEntityModels;

public class GuiHopper extends GuiContainer {
   public CustomEntityModels field_0001;
   public IInventory hopperInventory;
   public static ResourceLocation HOPPER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/hopper.png");
   public IInventory playerInventory;

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      this.q.drawString(this.hopperInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
      this.q.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.g - 96 + 2, 4210752);
   }

   public GuiHopper(InventoryPlayer var1, IInventory var2) {
      super(new ContainerHopper(var1, var2, Minecraft.getMinecraft().thePlayer));
      this.playerInventory = var1;
      this.hopperInventory = var2;
      this.p = false;
      this.g = 133;
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(HOPPER_GUI_TEXTURE);
      int var4 = (this.l - this.f) / 2;
      int var5 = (this.m - this.g) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.f, this.g);
   }
}
