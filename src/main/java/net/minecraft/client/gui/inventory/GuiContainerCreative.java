package net.minecraft.client.gui.inventory;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiContainerCreative extends InventoryEffectRenderer {
   public List<Slot> field_147063_B;
   public Slot field_147064_C;
   public boolean isScrolling;
   public GuiTextField searchField;
   public boolean wasClicking;
   public static ResourceLocation creativeInventoryTabs = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
   public boolean field_147057_D;
   public CreativeCrafting field_147059_E;
   public static InventoryBasic field_147060_v = new InventoryBasic("tmp", true, 45);
   public static int selectedTabIndex = CreativeTabs.tabBlock.getTabIndex();
   public float currentScroll;

   @Override
   public void renderToolTip(ItemStack var1, int var2, int var3) {
      if (selectedTabIndex == CreativeTabs.tabAllSearch.getTabIndex()) {
         List var4 = var1.getTooltip(this.j.thePlayer, this.j.gameSettings.advancedItemTooltips);
         CreativeTabs var5 = var1.getItem().getCreativeTab();
         if (var5 == null && var1.getItem() == Items.enchanted_book) {
            Map var6 = EnchantmentHelper.getEnchantments(var1);
            if (var6.size() == 1) {
               Enchantment var7 = Enchantment.getEnchantmentById((Integer)var6.keySet().iterator().next());

               for (CreativeTabs var11 : CreativeTabs.creativeTabArray) {
                  if (var11.hasRelevantEnchantmentType(var7.type)) {
                     var5 = var11;
                     break;
                  }
               }
            }
         }

         if (var5 != null) {
            var4.add(1, "" + EnumChatFormatting.BOLD + EnumChatFormatting.BLUE + I18n.format(var5.getTranslatedTabLabel()));
         }

         for (int var12 = 0; var12 < var4.size(); var12++) {
            if (var12 == 0) {
               var4.set(var12, var1.getRarity().rarityColor + (String)var4.get(var12));
            } else {
               var4.set(var12, EnumChatFormatting.GRAY + (String)var4.get(var12));
            }
         }

         this.drawHoveringText(var4, var2, var3);
      } else {
         super.renderToolTip(var1, var2, var3);
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      boolean var4 = Mouse.isButtonDown(0);
      int var5 = this.i;
      int var6 = this.r;
      int var7 = var5 + 175;
      int var8 = var6 + 18;
      int var9 = var7 + 14;
      int var10 = var8 + 112;
      if (!this.wasClicking && var4 && var1 >= var7 && var2 >= var8 && var1 < var9 && var2 < var10) {
         this.isScrolling = this.needsScrollBars();
      }

      if (!var4) {
         this.isScrolling = false;
      }

      this.wasClicking = var4;
      if (this.isScrolling) {
         this.currentScroll = (var2 - var8 - 7.5F) / (var10 - var8 - 15.0F);
         this.currentScroll = MathHelper.clamp_float(this.currentScroll, 0.0F, 1.0F);
         ((GuiContainerCreative.ContainerCreative)this.h).scrollTo(this.currentScroll);
      }

      super.drawScreen(var1, var2, var3);

      for (CreativeTabs var14 : CreativeTabs.creativeTabArray) {
         if (this.renderCreativeInventoryHoveringText(var14, var1, var2)) {
            break;
         }
      }

      if (this.field_147064_C != null
         && selectedTabIndex == CreativeTabs.tabInventory.getTabIndex()
         && this.c(this.field_147064_C.xDisplayPosition, this.field_147064_C.yDisplayPosition, 16, 16, var1, var2)) {
         this.drawCreativeTabHoveringText(I18n.format("inventory.binSlot"), var1, var2);
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableLighting();
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      if (selectedTabIndex != CreativeTabs.tabAllSearch.getTabIndex()) {
         if (GameSettings.isKeyDown(this.j.gameSettings.recoveredField2688)) {
            this.setCurrentCreativeTab(CreativeTabs.tabAllSearch);
         } else {
            super.keyTyped(var1, var2);
         }
      } else {
         if (this.field_147057_D) {
            this.field_147057_D = false;
            this.searchField.setText("");
         }

         if (!this.checkHotbarKeys(var2)) {
            if (this.searchField.textboxKeyTyped(var1, var2)) {
               this.updateCreativeSearch();
            } else {
               super.keyTyped(var1, var2);
            }
         }
      }
   }

   @Override
   public void handleMouseClick(Slot var1, int var2, int var3, int var4) {
      this.field_147057_D = true;
      boolean var5 = var4 == 1;
      var4 = var2 == -999 && var4 == 0 ? 4 : var4;
      if (var1 == null && selectedTabIndex != CreativeTabs.tabInventory.getTabIndex() && var4 != 5) {
         InventoryPlayer var15 = this.j.thePlayer.bi;
         if (var15.getItemStack() != null) {
            if (var3 == 0) {
               this.j.thePlayer.dropPlayerItemWithRandomChoice(var15.getItemStack(), true);
               this.j.playerController.sendPacketDropItem(var15.getItemStack());
               var15.setItemStack((ItemStack)null);
            }

            if (var3 == 1) {
               ItemStack var17 = var15.getItemStack().splitStack(1);
               this.j.thePlayer.dropPlayerItemWithRandomChoice(var17, true);
               this.j.playerController.sendPacketDropItem(var17);
               if (var15.getItemStack().stackSize == 0) {
                  var15.setItemStack((ItemStack)null);
               }
            }
         }
      } else if (var1 == this.field_147064_C && var5) {
         for (int var14 = 0; var14 < this.j.thePlayer.bj.getInventory().size(); var14++) {
            this.j.playerController.sendSlotPacket((ItemStack)null, var14);
         }
      } else if (selectedTabIndex == CreativeTabs.tabInventory.getTabIndex()) {
         if (var1 == this.field_147064_C) {
            this.j.thePlayer.bi.setItemStack((ItemStack)null);
         } else if (var4 == 4 && var1 != null && var1.getHasStack()) {
            ItemStack var6 = var1.decrStackSize(var3 == 0 ? 1 : var1.getStack().getMaxStackSize());
            this.j.thePlayer.dropPlayerItemWithRandomChoice(var6, true);
            this.j.playerController.sendPacketDropItem(var6);
         } else if (var4 == 4 && this.j.thePlayer.bi.getItemStack() != null) {
            this.j.thePlayer.dropPlayerItemWithRandomChoice(this.j.thePlayer.bi.getItemStack(), true);
            this.j.playerController.sendPacketDropItem(this.j.thePlayer.bi.getItemStack());
            this.j.thePlayer.bi.setItemStack((ItemStack)null);
         } else {
            this.j.thePlayer.bj.slotClick(var1 == null ? var2 : ((GuiContainerCreative.CreativeSlot)var1).slot.slotNumber, var3, var4, this.j.thePlayer);
            this.j.thePlayer.bj.detectAndSendChanges();
         }
      } else if (var4 != 5 && var1.inventory == field_147060_v) {
         InventoryPlayer var13 = this.j.thePlayer.bi;
         ItemStack var7 = var13.getItemStack();
         ItemStack var8 = var1.getStack();
         if (var4 == 2) {
            if (var8 != null && var3 >= 0 && var3 < 9) {
               ItemStack var19 = var8.copy();
               var19.stackSize = var19.getMaxStackSize();
               this.j.thePlayer.bi.setInventorySlotContents(var3, var19);
               this.j.thePlayer.bj.detectAndSendChanges();
            }

            return;
         }

         if (var4 == 3) {
            if (var13.getItemStack() == null && var1.getHasStack()) {
               ItemStack var18 = var1.getStack().copy();
               var18.stackSize = var18.getMaxStackSize();
               var13.setItemStack(var18);
            }

            return;
         }

         if (var4 == 4) {
            if (var8 != null) {
               ItemStack var9 = var8.copy();
               var9.stackSize = var3 == 0 ? 1 : var9.getMaxStackSize();
               this.j.thePlayer.dropPlayerItemWithRandomChoice(var9, true);
               this.j.playerController.sendPacketDropItem(var9);
            }

            return;
         }

         if (var7 != null && var8 != null && var7.isItemEqual(var8)) {
            if (var3 == 0) {
               if (var5) {
                  var7.stackSize = var7.getMaxStackSize();
               } else if (var7.stackSize < var7.getMaxStackSize()) {
                  var7.stackSize++;
               }
            } else if (var7.stackSize <= 1) {
               var13.setItemStack((ItemStack)null);
            } else {
               var7.stackSize--;
            }
         } else if (var8 != null && var7 == null) {
            var13.setItemStack(ItemStack.copyItemStack(var8));
            var7 = var13.getItemStack();
            if (var5) {
               var7.stackSize = var7.getMaxStackSize();
            }
         } else {
            var13.setItemStack((ItemStack)null);
         }
      } else {
         this.h.slotClick(var1 == null ? var2 : var1.slotNumber, var3, var4, this.j.thePlayer);
         if (Container.getDragEvent(var3) == 2) {
            for (int var11 = 0; var11 < 9; var11++) {
               this.j.playerController.sendSlotPacket(this.h.a(45 + var11).getStack(), 36 + var11);
            }
         } else if (var1 != null) {
            ItemStack var12 = this.h.a(var1.slotNumber).getStack();
            this.j.playerController.sendSlotPacket(var12, var1.slotNumber - this.h.c.size() + 9 + 36);
         }
      }
   }

   public GuiContainerCreative(EntityPlayer var1) {
      super(new GuiContainerCreative.ContainerCreative(var1));
      var1.bk = this.h;
      this.p = true;
      this.g = 136;
      this.f = 195;
   }

   public void updateCreativeSearch() {
      GuiContainerCreative.ContainerCreative var1 = (GuiContainerCreative.ContainerCreative)this.h;
      var1.itemList.clear();

      for (Item var3 : Item.itemRegistry) {
         if (var3 != null && var3.getCreativeTab() != null) {
            var3.getSubItems(var3, (CreativeTabs)null, var1.itemList);
         }
      }

      for (Enchantment var5 : Enchantment.enchantmentsBookList) {
         if (var5 != null && var5.type != null) {
            Items.enchanted_book.getAll(var5, var1.itemList);
         }
      }

      Iterator var9 = var1.itemList.iterator();
      String var11 = this.searchField.getText().toLowerCase();

      while (var9.hasNext()) {
         ItemStack var12 = (ItemStack)var9.next();
         boolean var13 = false;

         for (String var7 : var12.getTooltip(this.j.thePlayer, this.j.gameSettings.advancedItemTooltips)) {
            if (EnumChatFormatting.getTextWithoutFormattingCodes(var7).toLowerCase().contains(var11)) {
               var13 = true;
               break;
            }
         }

         if (!var13) {
            var9.remove();
         }
      }

      this.currentScroll = 0.0F;
      var1.scrollTo(0.0F);
   }

   @Override
   public void updateScreen() {
      if (!this.j.playerController.isInCreativeMode()) {
         this.j.displayGuiScreen(new GuiInventory(this.j.thePlayer));
      }

      this.updateActivePotionEffects();
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      RenderHelper.enableGUIStandardItemLighting();
      CreativeTabs var4 = CreativeTabs.creativeTabArray[selectedTabIndex];

      for (CreativeTabs var8 : CreativeTabs.creativeTabArray) {
         this.j.getTextureManager().bindTexture(creativeInventoryTabs);
         if (var8.getTabIndex() != selectedTabIndex) {
            this.func_147051_a(var8);
         }
      }

      this.j.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + var4.getBackgroundImageName()));
      this.drawTexturedModalRect(this.i, this.r, 0, 0, this.f, this.g);
      this.searchField.drawTextBox();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      int var9 = this.i + 175;
      int var10 = this.r + 18;
      int var11 = var10 + 112;
      this.j.getTextureManager().bindTexture(creativeInventoryTabs);
      if (var4.shouldHidePlayerInventory()) {
         this.drawTexturedModalRect(var9, var10 + (int)((var11 - var10 - 17) * this.currentScroll), 232 + (this.needsScrollBars() ? 0 : 12), 0, 12, 15);
      }

      this.func_147051_a(var4);
      if (var4 == CreativeTabs.tabInventory) {
         GuiInventory.drawEntityOnScreen(this.i + 43, this.r + 45, 20, this.i + 43 - var2, this.r + 45 - 30 - var3, this.j.thePlayer);
      }
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      int var1 = Mouse.getEventDWheel();
      if (var1 != 0 && this.needsScrollBars()) {
         int var2 = ((GuiContainerCreative.ContainerCreative)this.h).itemList.size() / 9 - 5;
         if (var1 > 0) {
            var1 = 1;
         }

         if (var1 < 0) {
            var1 = -1;
         }

         this.currentScroll = (float)(this.currentScroll - (double)var1 / var2);
         this.currentScroll = MathHelper.clamp_float(this.currentScroll, 0.0F, 1.0F);
         ((GuiContainerCreative.ContainerCreative)this.h).scrollTo(this.currentScroll);
      }
   }

   public void func_147051_a(CreativeTabs var1) {
      boolean var2 = var1.getTabIndex() == selectedTabIndex;
      boolean var3 = var1.isTabInFirstRow();
      int var4 = var1.getTabColumn();
      int var5 = var4 * 28;
      int var6 = 0;
      int var7 = this.i + 28 * var4;
      int var8 = this.r;
      byte var9 = 32;
      if (var2) {
         var6 += 32;
      }

      if (var4 == 5) {
         var7 = this.i + this.f - 28;
      } else if (var4 > 0) {
         var7 += var4;
      }

      if (var3) {
         var8 -= 28;
      } else {
         var6 += 64;
         var8 += this.g - 4;
      }

      GlStateManager.disableLighting();
      this.drawTexturedModalRect(var7, var8, var5, var6, 28, var9);
      recoveredField2942 = 100.0F;
      this.k.zLevel = 100.0F;
      var7 += 6;
      var8 = var8 + 8 + (var3 ? 1 : -1);
      GlStateManager.enableLighting();
      GlStateManager.enableRescaleNormal();
      ItemStack var10 = var1.getIconItemStack();
      this.k.renderItemAndEffectIntoGUI(var10, var7, var8);
      this.k.renderItemOverlays(this.q, var10, var7, var8);
      GlStateManager.disableLighting();
      this.k.zLevel = 0.0F;
      recoveredField2942 = 0.0F;
   }

   public boolean func_147049_a(CreativeTabs var1, int var2, int var3) {
      int var4 = var1.getTabColumn();
      int var5 = 28 * var4;
      int var6 = 0;
      if (var4 == 5) {
         var5 = this.f - 28 + 2;
      } else if (var4 > 0) {
         var5 += var4;
      }

      if (var1.isTabInFirstRow()) {
         var6 -= 32;
      } else {
         var6 += this.g;
      }

      return var2 >= var5 && var2 <= var5 + 28 && var3 >= var6 && var3 <= var6 + 32;
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3) {
      if (var3 == 0) {
         int var4 = var1 - this.i;
         int var5 = var2 - this.r;

         for (CreativeTabs var9 : CreativeTabs.creativeTabArray) {
            if (this.func_147049_a(var9, var4, var5)) {
               this.setCurrentCreativeTab(var9);
               return;
            }
         }
      }

      super.mouseReleased(var1, var2, var3);
   }

   public boolean needsScrollBars() {
      return selectedTabIndex != CreativeTabs.tabInventory.getTabIndex()
         && CreativeTabs.creativeTabArray[selectedTabIndex].shouldHidePlayerInventory()
         && ((GuiContainerCreative.ContainerCreative)this.h).func_148328_e();
   }

   @Override
   public void a_() {
      super.a_();
      if (this.j.thePlayer != null && this.j.thePlayer.bi != null) {
         this.j.thePlayer.bj.removeCraftingFromCrafters(this.field_147059_E);
      }

      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      if (var3 == 0) {
         int var4 = var1 - this.i;
         int var5 = var2 - this.r;

         for (CreativeTabs var9 : CreativeTabs.creativeTabArray) {
            if (this.func_147049_a(var9, var4, var5)) {
               return;
            }
         }
      }

      super.mouseClicked(var1, var2, var3);
   }

   @Override
   public void updateActivePotionEffects() {
      int var1 = this.i;
      super.updateActivePotionEffects();
      if (this.searchField != null && this.i != var1) {
         this.searchField.xPosition = this.i + 82;
      }
   }

   public void setCurrentCreativeTab(CreativeTabs var1) {
      int var2 = selectedTabIndex;
      selectedTabIndex = var1.getTabIndex();
      GuiContainerCreative.ContainerCreative var3 = (GuiContainerCreative.ContainerCreative)this.h;
      this.s.clear();
      var3.itemList.clear();
      var1.displayAllReleventItems(var3.itemList);
      if (var1 == CreativeTabs.tabInventory) {
         Container var4 = this.j.thePlayer.bj;
         if (this.field_147063_B == null) {
            this.field_147063_B = var3.c;
         }

         var3.c = Lists.newArrayList();

         for (int var5 = 0; var5 < var4.c.size(); var5++) {
            GuiContainerCreative.CreativeSlot var6 = new GuiContainerCreative.CreativeSlot(var4.c.get(var5), var5);
            var3.c.add(var6);
            if (var5 >= 5 && var5 < 9) {
               int var10 = var5 - 5;
               int var11 = var10 / 2;
               int var12 = var10 % 2;
               var6.xDisplayPosition = 9 + var11 * 54;
               var6.yDisplayPosition = 6 + var12 * 27;
            } else if (var5 >= 0 && var5 < 5) {
               var6.yDisplayPosition = -2000;
               var6.xDisplayPosition = -2000;
            } else if (var5 < var4.c.size()) {
               int var7 = var5 - 9;
               int var8 = var7 % 9;
               int var9 = var7 / 9;
               var6.xDisplayPosition = 9 + var8 * 18;
               if (var5 >= 36) {
                  var6.yDisplayPosition = 112;
               } else {
                  var6.yDisplayPosition = 54 + var9 * 18;
               }
            }
         }

         this.field_147064_C = new Slot(field_147060_v, 0, 173, 112);
         var3.c.add(this.field_147064_C);
      } else if (var2 == CreativeTabs.tabInventory.getTabIndex()) {
         var3.c = this.field_147063_B;
         this.field_147063_B = null;
      }

      if (this.searchField != null) {
         if (var1 == CreativeTabs.tabAllSearch) {
            this.searchField.setVisible(true);
            this.searchField.setCanLoseFocus(false);
            this.searchField.setFocused(true);
            this.searchField.setText("");
            this.updateCreativeSearch();
         } else {
            this.searchField.setVisible(false);
            this.searchField.setCanLoseFocus(true);
            this.searchField.setFocused(false);
         }
      }

      this.currentScroll = 0.0F;
      var3.scrollTo(0.0F);
   }

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      CreativeTabs var3 = CreativeTabs.creativeTabArray[selectedTabIndex];
      if (var3.drawInForegroundOfTab()) {
         GlStateManager.disableBlend();
         this.q.drawString(I18n.format(var3.getTranslatedTabLabel()), 8, 6, 4210752);
      }
   }

   public int getSelectedTabIndex() {
      return selectedTabIndex;
   }

   @Override
   public void initGui() {
      if (this.j.playerController.isInCreativeMode()) {
         super.initGui();
         this.n.clear();
         Keyboard.enableRepeatEvents(true);
         this.searchField = new GuiTextField(0, this.q, this.i + 82, this.r + 6, 89, this.q.FONT_HEIGHT);
         this.searchField.setMaxStringLength(15);
         this.searchField.setEnableBackgroundDrawing(false);
         this.searchField.setVisible(false);
         this.searchField.setTextColor(16777215);
         int var1 = selectedTabIndex;
         selectedTabIndex = -1;
         this.setCurrentCreativeTab(CreativeTabs.creativeTabArray[var1]);
         this.field_147059_E = new CreativeCrafting(this.j);
         this.j.thePlayer.bj.onCraftGuiOpened(this.field_147059_E);
      } else {
         this.j.displayGuiScreen(new GuiInventory(this.j.thePlayer));
      }
   }

   public boolean renderCreativeInventoryHoveringText(CreativeTabs var1, int var2, int var3) {
      int var4 = var1.getTabColumn();
      int var5 = 28 * var4;
      int var6 = 0;
      if (var4 == 5) {
         var5 = this.f - 28 + 2;
      } else if (var4 > 0) {
         var5 += var4;
      }

      if (var1.isTabInFirstRow()) {
         var6 -= 32;
      } else {
         var6 += this.g;
      }

      if (this.c(var5 + 3, var6 + 3, 23, 27, var2, var3)) {
         this.drawCreativeTabHoveringText(I18n.format(var1.getTranslatedTabLabel()), var2, var3);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.k == 0) {
         this.j.displayGuiScreen(new GuiAchievements(this, this.j.thePlayer.getStatFileWriter()));
      }

      if (var1.k == 1) {
         this.j.displayGuiScreen(new GuiStats(this, this.j.thePlayer.getStatFileWriter()));
      }
   }

   public static class ContainerCreative extends Container {
      public List<ItemStack> itemList = Lists.newArrayList();

      public boolean func_148328_e() {
         return this.itemList.size() > 45;
      }

      @Override
      public boolean canInteractWith(EntityPlayer var1) {
         return true;
      }

      @Override
      public ItemStack transferStackInSlot(EntityPlayer var1, int var2) {
         if (var2 >= this.c.size() - 9 && var2 < this.c.size()) {
            Slot var3 = this.c.get(var2);
            if (var3 != null && var3.getHasStack()) {
               var3.putStack((ItemStack)null);
            }
         }

         return null;
      }

      @Override
      public void retrySlotClick(int var1, int var2, boolean var3, EntityPlayer var4) {
      }

      @Override
      public boolean canDragIntoSlot(Slot var1) {
         return var1.inventory instanceof InventoryPlayer || var1.yDisplayPosition > 90 && var1.xDisplayPosition <= 162;
      }

      @Override
      public boolean canMergeSlot(ItemStack var1, Slot var2) {
         return var2.yDisplayPosition > 90;
      }

      public void scrollTo(float var1) {
         int var2 = (this.itemList.size() + 9 - 1) / 9 - 5;
         int var3 = (int)(var1 * var2 + 0.5);
         if (var3 < 0) {
            var3 = 0;
         }

         for (int var4 = 0; var4 < 5; var4++) {
            for (int var5 = 0; var5 < 9; var5++) {
               int var6 = var5 + (var4 + var3) * 9;
               if (var6 >= 0 && var6 < this.itemList.size()) {
                  GuiContainerCreative.field_147060_v.setInventorySlotContents(var5 + var4 * 9, this.itemList.get(var6));
               } else {
                  GuiContainerCreative.field_147060_v.setInventorySlotContents(var5 + var4 * 9, (ItemStack)null);
               }
            }
         }
      }

      public ContainerCreative(EntityPlayer var1) {
         InventoryPlayer var2 = var1.bi;

         for (int var3 = 0; var3 < 5; var3++) {
            for (int var4 = 0; var4 < 9; var4++) {
               this.a(new Slot(GuiContainerCreative.field_147060_v, var3 * 9 + var4, 9 + var4 * 18, 18 + var3 * 18));
            }
         }

         for (int var5 = 0; var5 < 9; var5++) {
            this.a(new Slot(var2, var5, 9 + var5 * 18, 112));
         }

         this.scrollTo(0.0F);
      }
   }

   public class CreativeSlot extends Slot {
      public Slot slot;

      @Override
      public ItemStack getStack() {
         return this.slot.getStack();
      }

      @Override
      public void onSlotChanged() {
         this.slot.onSlotChanged();
      }

      public CreativeSlot(Slot var2, int var3) {
         super(var2.inventory, var3, 0, 0);
         this.slot = var2;
      }

      @Override
      public void putStack(ItemStack var1) {
         this.slot.putStack(var1);
      }

      @Override
      public int getItemStackLimit(ItemStack var1) {
         return this.slot.getItemStackLimit(var1);
      }

      @Override
      public boolean isItemValid(ItemStack var1) {
         return this.slot.isItemValid(var1);
      }

      @Override
      public ItemStack decrStackSize(int var1) {
         return this.slot.decrStackSize(var1);
      }

      @Override
      public String getSlotTexture() {
         return this.slot.getSlotTexture();
      }

      @Override
      public boolean isHere(IInventory var1, int var2) {
         return this.slot.isHere(var1, var2);
      }

      @Override
      public int getSlotStackLimit() {
         return this.slot.getSlotStackLimit();
      }

      @Override
      public boolean getHasStack() {
         return this.slot.getHasStack();
      }

      @Override
      public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
         this.slot.onPickupFromSlot(var1, var2);
      }
   }
}
