package net.minecraft.item;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemRecord extends Item {
   public static Map<String, ItemRecord> RECORDS = Maps.newHashMap();
   public String recordName;

   public ItemRecord(String var1) {
      this.recordName = var1;
      this.h = 1;
      this.setCreativeTab(CreativeTabs.tabMisc);
      RECORDS.put("records." + var1, this);
   }

   @Override
   public void addInformation(ItemStack var1, EntityPlayer var2, List<String> var3, boolean var4) {
      var3.add(this.getRecordNameLocal());
   }

   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      IBlockState var9 = var3.getBlockState(var4);
      if (var9.getBlock() != Blocks.jukebox || var9.getValue(BlockJukebox.HAS_RECORD)) {
         return false;
      } else if (var3.D) {
         return true;
      } else {
         ((BlockJukebox)Blocks.jukebox).insertRecord(var3, var4, var9, var1);
         var3.playAuxSFXAtEntity((EntityPlayer)null, 1005, var4, Item.getIdFromItem(this));
         var1.stackSize--;
         var2.triggerAchievement(StatList.field_181740_X);
         return true;
      }
   }

   public static ItemRecord getRecord(String var0) {
      return RECORDS.get(var0);
   }

   public String getRecordNameLocal() {
      return StatCollector.translateToLocal("item.record." + this.recordName + ".desc");
   }

   @Override
   public EnumRarity getRarity(ItemStack var1) {
      return EnumRarity.RARE;
   }
}
