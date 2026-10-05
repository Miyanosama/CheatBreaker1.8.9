package net.minecraft.client.gui.inventory;

import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.util.concurrent.MultithreadEventExecutorGroup$GenericEventExecutorChooser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiChest extends GuiContainer {
   public MultithreadEventExecutorGroup$GenericEventExecutorChooser field_0003;
   public UnsupportedMessageTypeException field_0005;
   public IInventory lowerChestInventory;
   public static ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
   public int inventoryRows;
   public IInventory upperChestInventory;

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
      int var4 = (this.l - this.f) / 2;
      int var5 = (this.m - this.g) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.f, this.inventoryRows * 18 + 17);
      this.drawTexturedModalRect(var4, var5 + this.inventoryRows * 18 + 17, 0, 126, this.f, 96);
   }

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      this.q.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
      this.q.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 8, this.g - 96 + 2, 4210752);
   }

   public GuiChest(IInventory var1, IInventory var2) {
      super(new ContainerChest(var1, var2, Minecraft.getMinecraft().thePlayer));
      this.upperChestInventory = var1;
      this.lowerChestInventory = var2;
      this.p = false;
      short var3 = 222;
      int var4 = var3 - 108;
      this.inventoryRows = var2.getSizeInventory() / 9;
      this.g = var4 + this.inventoryRows * 18;
   }
}
