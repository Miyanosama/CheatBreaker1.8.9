package net.minecraft.client.gui;

import com.cheatbreaker.client.network.CustomPayloadSender;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiMerchant extends GuiContainer {
   public static Logger logger = LogManager.getLogger();
   public IChatComponent chatComponent;
   public static ResourceLocation MERCHANT_GUI_TEXTURE = new ResourceLocation("textures/gui/container/villager.png");
   public IMerchant merchant;
   public GuiMerchant.MerchantButton previousButton;
   public int selectedMerchantRecipe;
   public GuiMerchant.MerchantButton nextButton;

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(MERCHANT_GUI_TEXTURE);
      int var4 = (this.l - this.f) / 2;
      int var5 = (this.m - this.g) / 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.f, this.g);
      MerchantRecipeList var6 = this.merchant.getRecipes(this.j.thePlayer);
      if (var6 != null && !var6.isEmpty()) {
         int var7 = this.selectedMerchantRecipe;
         if (var7 < 0 || var7 >= var6.size()) {
            return;
         }

         MerchantRecipe var8 = var6.get(var7);
         if (var8.isRecipeDisabled()) {
            this.j.getTextureManager().bindTexture(MERCHANT_GUI_TEXTURE);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            this.drawTexturedModalRect(this.i + 83, this.r + 21, 212, 0, 28, 21);
            this.drawTexturedModalRect(this.i + 83, this.r + 51, 212, 0, 28, 21);
         }
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      MerchantRecipeList var4 = this.merchant.getRecipes(this.j.thePlayer);
      if (var4 != null && !var4.isEmpty()) {
         int var5 = (this.l - this.f) / 2;
         int var6 = (this.m - this.g) / 2;
         int var7 = this.selectedMerchantRecipe;
         MerchantRecipe var8 = var4.get(var7);
         ItemStack var9 = var8.getItemToBuy();
         ItemStack var10 = var8.getSecondItemToBuy();
         ItemStack var11 = var8.getItemToSell();
         GlStateManager.pushMatrix();
         RenderHelper.enableGUIStandardItemLighting();
         GlStateManager.disableLighting();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableColorMaterial();
         GlStateManager.enableLighting();
         this.k.zLevel = 100.0F;
         this.k.renderItemAndEffectIntoGUI(var9, var5 + 36, var6 + 24);
         this.k.renderItemOverlays(this.q, var9, var5 + 36, var6 + 24);
         if (var10 != null) {
            this.k.renderItemAndEffectIntoGUI(var10, var5 + 62, var6 + 24);
            this.k.renderItemOverlays(this.q, var10, var5 + 62, var6 + 24);
         }

         this.k.renderItemAndEffectIntoGUI(var11, var5 + 120, var6 + 24);
         this.k.renderItemOverlays(this.q, var11, var5 + 120, var6 + 24);
         this.k.zLevel = 0.0F;
         GlStateManager.disableLighting();
         if (this.c(36, 24, 16, 16, var1, var2) && var9 != null) {
            this.renderToolTip(var9, var1, var2);
         } else if (var10 != null && this.c(62, 24, 16, 16, var1, var2) && var10 != null) {
            this.renderToolTip(var10, var1, var2);
         } else if (var11 != null && this.c(120, 24, 16, 16, var1, var2) && var11 != null) {
            this.renderToolTip(var11, var1, var2);
         } else if (var8.isRecipeDisabled() && (this.c(83, 21, 28, 21, var1, var2) || this.c(83, 51, 28, 21, var1, var2))) {
            this.drawCreativeTabHoveringText(I18n.format("merchant.deprecated"), var1, var2);
         }

         GlStateManager.popMatrix();
         GlStateManager.enableLighting();
         GlStateManager.enableDepth();
         RenderHelper.enableStandardItemLighting();
      }
   }

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      String var3 = this.chatComponent.getUnformattedText();
      this.q.drawString(var3, this.f / 2 - this.q.getStringWidth(var3) / 2, 6, 4210752);
      this.q.drawString(I18n.format("container.inventory"), 8, this.g - 96 + 2, 4210752);
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      MerchantRecipeList var1 = this.merchant.getRecipes(this.j.thePlayer);
      if (var1 != null) {
         this.nextButton.l = this.selectedMerchantRecipe < var1.size() - 1;
         this.previousButton.l = this.selectedMerchantRecipe > 0;
      }
   }

   public IMerchant getMerchant() {
      return this.merchant;
   }

   @Override
   public void initGui() {
      super.initGui();
      int var1 = (this.l - this.f) / 2;
      int var2 = (this.m - this.g) / 2;
      this.n.add(this.nextButton = new GuiMerchant.MerchantButton(1, var1 + 120 + 27, var2 + 24 - 1, true));
      this.n.add(this.previousButton = new GuiMerchant.MerchantButton(2, var1 + 36 - 19, var2 + 24 - 1, false));
      this.nextButton.l = false;
      this.previousButton.l = false;
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      boolean var2 = false;
      if (var1 == this.nextButton) {
         this.selectedMerchantRecipe++;
         MerchantRecipeList var3 = this.merchant.getRecipes(this.j.thePlayer);
         if (var3 != null && this.selectedMerchantRecipe >= var3.size()) {
            this.selectedMerchantRecipe = var3.size() - 1;
         }

         var2 = true;
      } else if (var1 == this.previousButton) {
         this.selectedMerchantRecipe--;
         if (this.selectedMerchantRecipe < 0) {
            this.selectedMerchantRecipe = 0;
         }

         var2 = true;
      }

      if (var2) {
         ((ContainerMerchant)this.h).setCurrentRecipeIndex(this.selectedMerchantRecipe);
         PacketBuffer var4 = new PacketBuffer(Unpooled.buffer());
         var4.writeInt(this.selectedMerchantRecipe);
         this.j.getNetHandler().addToSendQueue(new CustomPayloadSender("MC|TrSel", var4));
      }
   }

   public GuiMerchant(InventoryPlayer var1, IMerchant var2, World var3) {
      super(new ContainerMerchant(var1, var2, var3));
      this.merchant = var2;
      this.chatComponent = var2.getDisplayName();
   }

   public static class MerchantButton extends GuiButton {
      public boolean field_146157_o;

      @Override
      public void drawButton(Minecraft var1, int var2, int var3) {
         if (this.m) {
            var1.getTextureManager().bindTexture(GuiMerchant.MERCHANT_GUI_TEXTURE);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            boolean var4 = var2 >= this.h && var3 >= this.i && var2 < this.h + this.f && var3 < this.i + this.height;
            int var5 = 0;
            int var6 = 176;
            if (!this.l) {
               var6 += this.f * 2;
            } else if (var4) {
               var6 += this.f;
            }

            if (!this.field_146157_o) {
               var5 += this.height;
            }

            this.drawTexturedModalRect(this.h, this.i, var6, var5, this.f, this.height);
         }
      }

      public MerchantButton(int var1, int var2, int var3, boolean var4) {
         super(var1, var2, var3, 12, 19, "");
         this.field_146157_o = var4;
      }
   }
}
