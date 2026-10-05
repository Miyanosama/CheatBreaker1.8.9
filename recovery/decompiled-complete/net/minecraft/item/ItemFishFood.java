package net.minecraft.item;

import io.netty.handler.traffic.TrafficCounter$TrafficMonitoringTask;
import java.util.List;
import javazoom.jl.player.AudioDeviceBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureVillagePieces$House2;
import net.optifine.entity.model.ModelAdapterCreeper;

public class ItemFishFood extends ItemFood {
   public StructureVillagePieces$House2 field_0001;
   public boolean cooked;
   public ModelAdapterCreeper field_0003;
   public TrafficCounter$TrafficMonitoringTask field_0004;
   public AudioDeviceBase field_0000;

   @Override
   public void getSubItems(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (ItemFishFood$FishType var7 : ItemFishFood$FishType.values()) {
         if (!this.cooked || var7.canCook()) {
            var3.add(new ItemStack(this, 1, var7.getMetadata()));
         }
      }
   }

   @Override
   public float getSaturationModifier(ItemStack var1) {
      ItemFishFood$FishType var2 = ItemFishFood$FishType.byItemStack(var1);
      return this.cooked && var2.canCook() ? var2.getCookedSaturationModifier() : var2.getUncookedSaturationModifier();
   }

   @Override
   public int getHealAmount(ItemStack var1) {
      ItemFishFood$FishType var2 = ItemFishFood$FishType.byItemStack(var1);
      return this.cooked && var2.canCook() ? var2.getCookedHealAmount() : var2.getUncookedHealAmount();
   }

   @Override
   public String getUnlocalizedName(ItemStack var1) {
      ItemFishFood$FishType var2 = ItemFishFood$FishType.byItemStack(var1);
      return this.getUnlocalizedName() + "." + var2.getUnlocalizedName() + "." + (this.cooked && var2.canCook() ? "cooked" : "raw");
   }

   public ItemFishFood(boolean var1) {
      super(0, 0.0F, false);
      this.cooked = var1;
   }

   @Override
   public void onFoodEaten(ItemStack var1, World var2, EntityPlayer var3) {
      ItemFishFood$FishType var4 = ItemFishFood$FishType.byItemStack(var1);
      if (var4 == ItemFishFood$FishType.PUFFERFISH) {
         var3.c(new PotionEffect(Potion.poison.id, 1200, 3));
         var3.c(new PotionEffect(Potion.hunger.id, 300, 2));
         var3.c(new PotionEffect(Potion.confusion.id, 300, 1));
      }

      super.onFoodEaten(var1, var2, var3);
   }

   @Override
   public String getPotionEffect(ItemStack var1) {
      return ItemFishFood$FishType.byItemStack(var1) == ItemFishFood$FishType.PUFFERFISH ? "+0-1+2+3+13&4-4" : null;
   }
}
