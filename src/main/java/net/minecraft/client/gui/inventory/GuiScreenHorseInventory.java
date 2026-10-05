package net.minecraft.client.gui.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiScreenHorseInventory extends GuiContainer {
   public static ResourceLocation horseGuiTextures = new ResourceLocation("textures/gui/container/horse.png");
   public float mousePosY;
   public IInventory playerInventory;
   public EntityHorse horseEntity;
   public IInventory horseInventory;
   public float mousePosx;

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      this.q.drawString(this.horseInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
      this.q.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.g - 96 + 2, 4210752);
   }

   public GuiScreenHorseInventory(IInventory var1, IInventory var2, EntityHorse var3) {
      super(new ContainerHorseInventory(var1, var2, var3, Minecraft.getMinecraft().thePlayer));
      this.playerInventory = var1;
      this.horseInventory = var2;
      this.horseEntity = var3;
      this.p = false;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.mousePosx = var1;
      this.mousePosY = var2;
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(horseGuiTextures);
      int var4 = (this.l - this.f) / 2;
      int var5 = (this.m - this.g) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.f, this.g);
      if (this.horseEntity.isChested()) {
         this.drawTexturedModalRect(var4 + 79, var5 + 17, 0, this.g, 90, 54);
      }

      if (this.horseEntity.canWearArmor()) {
         this.drawTexturedModalRect(var4 + 7, var5 + 35, 0, this.g + 54, 18, 18);
      }

      GuiInventory.drawEntityOnScreen(var4 + 51, var5 + 60, 17, var4 + 51 - this.mousePosx, var5 + 75 - 50 - this.mousePosY, this.horseEntity);
   }
}
