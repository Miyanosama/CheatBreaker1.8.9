package net.minecraft.client.gui;

import com.cheatbreaker.client.network.CustomPayloadSender;
import io.netty.buffer.Unpooled;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class GuiRepair extends GuiContainer implements ICrafting {
   public GuiTextField nameField;
   public ContainerRepair anvil;
   public static ResourceLocation anvilResource = new ResourceLocation("textures/gui/container/anvil.png");
   public InventoryPlayer playerInventory;

   @Override
   public void sendAllWindowProperties(Container var1, IInventory var2) {
   }

   @Override
   public void updateCraftingInventory(Container var1, List<ItemStack> var2) {
      this.sendSlotContents(var1, 0, var1.a(0).getStack());
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      if (this.nameField.textboxKeyTyped(var1, var2)) {
         this.renameItem();
      } else {
         super.keyTyped(var1, var2);
      }
   }

   @Override
   public void a_() {
      super.a_();
      Keyboard.enableRepeatEvents(false);
      this.h.removeCraftingFromCrafters(this);
   }

   public void renameItem() {
      String var1 = this.nameField.getText();
      Slot var2 = this.anvil.a(0);
      if (var2 != null && var2.getHasStack() && !var2.getStack().hasDisplayName() && var1.equals(var2.getStack().getDisplayName())) {
         var1 = "";
      }

      this.anvil.updateItemName(var1);
      this.j.thePlayer.sendQueue.addToSendQueue(new CustomPayloadSender("MC|ItemName", new PacketBuffer(Unpooled.buffer()).writeString(var1)));
   }

   @Override
   public void sendSlotContents(Container var1, int var2, ItemStack var3) {
      if (var2 == 0) {
         this.nameField.setText(var3 == null ? "" : var3.getDisplayName());
         this.nameField.setEnabled(var3 != null);
         if (var3 != null) {
            this.renameItem();
         }
      }
   }

   @Override
   public void initGui() {
      super.initGui();
      Keyboard.enableRepeatEvents(true);
      int var1 = (this.l - this.f) / 2;
      int var2 = (this.m - this.g) / 2;
      this.nameField = new GuiTextField(0, this.q, var1 + 62, var2 + 24, 103, 12);
      this.nameField.setTextColor(-1);
      this.nameField.setDisabledTextColour(-1);
      this.nameField.setEnableBackgroundDrawing(false);
      this.nameField.setMaxStringLength(30);
      this.h.removeCraftingFromCrafters(this);
      this.h.onCraftGuiOpened(this);
   }

   public GuiRepair(InventoryPlayer var1, World var2) {
      super(new ContainerRepair(var1, var2, Minecraft.getMinecraft().thePlayer));
      this.playerInventory = var1;
      this.anvil = (ContainerRepair)this.h;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      GlStateManager.disableLighting();
      GlStateManager.disableBlend();
      this.nameField.drawTextBox();
   }

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      GlStateManager.disableLighting();
      GlStateManager.disableBlend();
      this.q.drawString(I18n.format("container.repair"), 60, 6, 4210752);
      if (this.anvil.maximumCost > 0) {
         int var3 = 8453920;
         boolean var4 = true;
         String var5 = I18n.format("container.repair.cost", this.anvil.maximumCost);
         if (this.anvil.maximumCost >= 40 && !this.j.thePlayer.bA.isCreativeMode) {
            var5 = I18n.format("container.repair.expensive");
            var3 = 16736352;
         } else if (!this.anvil.a(2).getHasStack()) {
            var4 = false;
         } else if (!this.anvil.a(2).canTakeStack(this.playerInventory.player)) {
            var3 = 16736352;
         }

         if (var4) {
            int var6 = 0xFF000000 | (var3 & 16579836) >> 2 | var3 & 0xFF000000;
            int var7 = this.f - 8 - this.q.getStringWidth(var5);
            byte var8 = 67;
            if (this.q.getUnicodeFlag()) {
               a(var7 - 3, var8 - 2, this.f - 7, var8 + 10, -16777216);
               a(var7 - 2, var8 - 1, this.f - 8, var8 + 9, -12895429);
            } else {
               this.q.drawString(var5, var7, var8 + 1, var6);
               this.q.drawString(var5, var7 + 1, var8, var6);
               this.q.drawString(var5, var7 + 1, var8 + 1, var6);
            }

            this.q.drawString(var5, var7, var8, var3);
         }
      }

      GlStateManager.enableLighting();
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(anvilResource);
      int var4 = (this.l - this.f) / 2;
      int var5 = (this.m - this.g) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.f, this.g);
      this.drawTexturedModalRect(var4 + 59, var5 + 20, 0, this.g + (this.anvil.a(0).getHasStack() ? 0 : 16), 110, 16);
      if ((this.anvil.a(0).getHasStack() || this.anvil.a(1).getHasStack()) && !this.anvil.a(2).getHasStack()) {
         this.drawTexturedModalRect(var4 + 99, var5 + 45, this.f, 0, 28, 21);
      }
   }

   @Override
   public void sendProgressBarUpdate(Container var1, int var2, int var3) {
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      super.mouseClicked(var1, var2, var3);
      this.nameField.mouseClicked(var1, var2, var3);
   }
}
