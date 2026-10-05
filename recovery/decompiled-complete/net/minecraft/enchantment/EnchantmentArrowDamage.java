package net.minecraft.enchantment;

import io.netty.channel.AbstractChannel$AbstractUnsafe$3;
import javazoom.jl.decoder.LayerIDecoder;
import net.minecraft.block.BlockWorkbench$InterfaceCraftingTable;
import net.minecraft.item.ItemMap;
import net.minecraft.nbt.JsonToNBT$List;
import net.minecraft.util.ResourceLocation;

public class EnchantmentArrowDamage extends Enchantment {
   public BlockWorkbench$InterfaceCraftingTable field_0000;
   public ItemMap field_0004;
   public LayerIDecoder field_0002;
   public JsonToNBT$List field_0003;
   public AbstractChannel$AbstractUnsafe$3 field_0001;

   @Override
   public int getMaxEnchantability(int var1) {
      return this.getMinEnchantability(var1) + 15;
   }

   public EnchantmentArrowDamage(int var1, ResourceLocation var2, int var3) {
      super(var1, var2, var3, EnumEnchantmentType.BOW);
      this.setName("arrowDamage");
   }

   @Override
   public int getMinEnchantability(int var1) {
      return 1 + (var1 - 1) * 10;
   }

   @Override
   public int getMaxLevel() {
      return 5;
   }
}
