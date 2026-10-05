package net.minecraft.client.gui.inventory;

import com.cheatbreaker.client.network.CustomPayloadSender;
import io.netty.buffer.Unpooled;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.profiler.PlayerUsageSnooper$1;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiBeacon extends GuiContainer {
   public ItemModelMesher field_0003;
   public GuiBeacon$ConfirmButton beaconConfirmButton;
   public PlayerUsageSnooper$1 field_0002;
   public static ResourceLocation beaconGuiTextures = new ResourceLocation("textures/gui/container/beacon.png");
   public static Logger logger = LogManager.getLogger();
   public boolean buttonsNotDrawn;
   public IInventory tileBeacon;

   @Override
   public void updateScreen() {
      super.updateScreen();
      int var1 = this.tileBeacon.getField(0);
      int var2 = this.tileBeacon.getField(1);
      int var3 = this.tileBeacon.getField(2);
      if (this.buttonsNotDrawn && var1 >= 0) {
         this.buttonsNotDrawn = false;

         for (int var4 = 0; var4 <= 2; var4++) {
            int var5 = TileEntityBeacon.effectsList[var4].length;
            int var6 = var5 * 22 + (var5 - 1) * 2;

            for (int var7 = 0; var7 < var5; var7++) {
               int var8 = TileEntityBeacon.effectsList[var4][var7].id;
               GuiBeacon$PowerButton var9 = new GuiBeacon$PowerButton(
                  this, var4 << 8 | var8, this.i + 76 + var7 * 24 - var6 / 2, this.r + 22 + var4 * 25, var8, var4
               );
               this.n.add(var9);
               if (var4 >= var1) {
                  var9.l = false;
               } else if (var8 == var2) {
                  var9.func_146140_b(true);
               }
            }
         }

         byte var10 = 3;
         int var11 = TileEntityBeacon.effectsList[var10].length + 1;
         int var12 = var11 * 22 + (var11 - 1) * 2;

         for (int var13 = 0; var13 < var11 - 1; var13++) {
            int var15 = TileEntityBeacon.effectsList[var10][var13].id;
            GuiBeacon$PowerButton var16 = new GuiBeacon$PowerButton(this, var10 << 8 | var15, this.i + 167 + var13 * 24 - var12 / 2, this.r + 47, var15, var10);
            this.n.add(var16);
            if (var10 >= var1) {
               var16.l = false;
            } else if (var15 == var3) {
               var16.func_146140_b(true);
            }
         }

         if (var2 > 0) {
            GuiBeacon$PowerButton var14 = new GuiBeacon$PowerButton(
               this, var10 << 8 | var2, this.i + 167 + (var11 - 1) * 24 - var12 / 2, this.r + 47, var2, var10
            );
            this.n.add(var14);
            if (var10 >= var1) {
               var14.l = false;
            } else if (var2 == var3) {
               var14.func_146140_b(true);
            }
         }
      }

      this.beaconConfirmButton.l = this.tileBeacon.getStackInSlot(0) != null && var2 > 0;
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.k == -2) {
         this.j.displayGuiScreen((GuiScreen)null);
      } else if (var1.k == -1) {
         String var2 = "MC|Beacon";
         PacketBuffer var3 = new PacketBuffer(Unpooled.buffer());
         var3.writeInt(this.tileBeacon.getField(1));
         var3.writeInt(this.tileBeacon.getField(2));
         this.j.getNetHandler().addToSendQueue(new CustomPayloadSender(var2, var3));
         this.j.displayGuiScreen((GuiScreen)null);
      } else if (var1 instanceof GuiBeacon$PowerButton) {
         if (((GuiBeacon$PowerButton)var1).func_146141_c()) {
            return;
         }

         int var5 = var1.k;
         int var6 = var5 & 0xFF;
         int var4 = var5 >> 8;
         if (var4 < 3) {
            this.tileBeacon.setField(1, var6);
         } else {
            this.tileBeacon.setField(2, var6);
         }

         this.n.clear();
         this.initGui();
         this.updateScreen();
      }
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(beaconGuiTextures);
      int var4 = (this.l - this.f) / 2;
      int var5 = (this.m - this.g) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.f, this.g);
      this.k.zLevel = 100.0F;
      this.k.renderItemAndEffectIntoGUI(new ItemStack(Items.emerald), var4 + 42, var5 + 109);
      this.k.renderItemAndEffectIntoGUI(new ItemStack(Items.diamond), var4 + 42 + 22, var5 + 109);
      this.k.renderItemAndEffectIntoGUI(new ItemStack(Items.gold_ingot), var4 + 42 + 44, var5 + 109);
      this.k.renderItemAndEffectIntoGUI(new ItemStack(Items.iron_ingot), var4 + 42 + 66, var5 + 109);
      this.k.zLevel = 0.0F;
   }

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      RenderHelper.disableStandardItemLighting();
      this.drawCenteredString(this.q, I18n.format("tile.beacon.primary"), 62, 10, 14737632);
      this.drawCenteredString(this.q, I18n.format("tile.beacon.secondary"), 169, 10, 14737632);

      for (GuiButton var4 : this.n) {
         if (var4.isMouseOver()) {
            var4.drawButtonForegroundLayer(var1 - this.i, var2 - this.r);
            break;
         }
      }

      RenderHelper.enableGUIStandardItemLighting();
   }

   @Override
   public void initGui() {
      super.initGui();
      this.n.add(this.beaconConfirmButton = new GuiBeacon$ConfirmButton(this, -1, this.i + 164, this.r + 107));
      this.n.add(new GuiBeacon$CancelButton(this, -2, this.i + 190, this.r + 107));
      this.buttonsNotDrawn = true;
      this.beaconConfirmButton.l = false;
   }

   public GuiBeacon(InventoryPlayer var1, IInventory var2) {
      super(new ContainerBeacon(var1, var2));
      this.tileBeacon = var2;
      this.f = 230;
      this.g = 219;
   }
}
