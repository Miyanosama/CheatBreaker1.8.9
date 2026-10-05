package net.minecraft.item;

import io.netty.channel.DefaultChannelHandlerContext;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.management.PlayerProfileCache$1;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.MobSpawnerBaseLogic$WeightedRandomMinecart;
import net.minecraft.world.World;
import net.optifine.shaders.uniform.ShaderUniform2i;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$28;

public class ItemWritableBook extends Item {
   public MobSpawnerBaseLogic$WeightedRandomMinecart field_0002;
   public PlayerProfileCache$1 field_0003;
   public ShaderUniform2i field_0004;
   public LogBrokerMonitor$28 field_0005;
   public DefaultChannelHandlerContext field_0001;
   public BlockButtonStone field_0000;

   public static boolean isNBTValid(NBTTagCompound var0) {
      if (var0 == null) {
         return false;
      } else if (!var0.hasKey("pages", 9)) {
         return false;
      } else {
         NBTTagList var1 = var0.getTagList("pages", 8);

         for (int var2 = 0; var2 < var1.tagCount(); var2++) {
            String var3 = var1.getStringTagAt(var2);
            if (var3 == null) {
               return false;
            }

            if (var3.length() > 32767) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      var3.displayGUIBook(var1);
      var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return var1;
   }

   public ItemWritableBook() {
      this.c(1);
   }
}
