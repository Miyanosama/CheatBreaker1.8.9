package net.minecraft.client.gui.inventory;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

public class GuiCrafting extends GuiContainer {
   public LayerSheepWool field_0001;
   public ChunkCoordIntPair field_0002;
   public static ResourceLocation craftingTableGuiTextures = new ResourceLocation("textures/gui/container/crafting_table.png");

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      this.q.drawString(I18n.format("container.crafting"), 28, 6, 4210752);
      this.q.drawString(I18n.format("container.inventory"), 8, this.g - 96 + 2, 4210752);
   }

   public GuiCrafting(InventoryPlayer var1, World var2, BlockPos var3) {
      super(new ContainerWorkbench(var1, var2, var3));
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(craftingTableGuiTextures);
      int var4 = (this.l - this.f) / 2;
      int var5 = (this.m - this.g) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.f, this.g);
   }

   public GuiCrafting(InventoryPlayer var1, World var2) {
      this(var1, var2, BlockPos.ORIGIN);
   }
}
