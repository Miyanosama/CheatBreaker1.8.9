package net.minecraft.client.gui.inventory;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.slf4j.helpers.BasicMarkerFactory;

public class GuiContainerCreative$ContainerCreative extends Container {
   public List<ItemStack> itemList = Lists.newArrayList();
   public RenderVillager field_0002;
   public BasicMarkerFactory field_0000;

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
               GuiContainerCreative.access$000().setInventorySlotContents(var5 + var4 * 9, this.itemList.get(var6));
            } else {
               GuiContainerCreative.access$000().setInventorySlotContents(var5 + var4 * 9, (ItemStack)null);
            }
         }
      }
   }

   public GuiContainerCreative$ContainerCreative(EntityPlayer var1) {
      InventoryPlayer var2 = var1.bi;

      for (int var3 = 0; var3 < 5; var3++) {
         for (int var4 = 0; var4 < 9; var4++) {
            this.a(new Slot(GuiContainerCreative.access$000(), var3 * 9 + var4, 9 + var4 * 18, 18 + var3 * 18));
         }
      }

      for (int var5 = 0; var5 < 9; var5++) {
         this.a(new Slot(var2, var5, 9 + var5 * 18, 112));
      }

      this.scrollTo(0.0F);
   }
}
