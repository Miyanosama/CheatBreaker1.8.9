package net.minecraft.inventory;

import io.netty.channel.AbstractChannelHandlerContext$WriteTask$1;
import io.netty.channel.sctp.oio.OioSctpServerChannel;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;

public class ContainerBrewingStand$Potion extends Slot {
   public EntityPlayer player;
   public AbstractChannelHandlerContext$WriteTask$1 field_0004;
   public EntityAIHurtByTarget field_0001;
   public WorldVertexBufferUploader field_0003;
   public OioSctpServerChannel field_0000;

   @Override
   public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
      if (var2.getItem() == Items.potionitem && var2.getMetadata() > 0) {
         this.player.triggerAchievement(AchievementList.potion);
      }

      super.onPickupFromSlot(var1, var2);
   }

   public static boolean canHoldPotion(ItemStack var0) {
      return var0 != null && (var0.getItem() == Items.potionitem || var0.getItem() == Items.glass_bottle);
   }

   @Override
   public boolean isItemValid(ItemStack var1) {
      return canHoldPotion(var1);
   }

   @Override
   public int getSlotStackLimit() {
      return 1;
   }

   public ContainerBrewingStand$Potion(EntityPlayer var1, IInventory var2, int var3, int var4, int var5) {
      super(var2, var3, var4, var5);
      this.player = var1;
   }
}
