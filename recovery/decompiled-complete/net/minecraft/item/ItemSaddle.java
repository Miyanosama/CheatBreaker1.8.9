package net.minecraft.item;

import net.minecraft.block.BlockHopper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$13;
import recovered.unidentified.UnidentifiedClass0706;
import recovered.unidentified.UnidentifiedClass3475;

public class ItemSaddle extends Item {
   public LogBrokerMonitor$13 field_0001;
   public UnidentifiedClass3475 field_0002;
   public S05PacketSpawnPosition field_0003;
   public BlockHopper field_0004;
   public UnidentifiedClass0706 field_0000;

   @Override
   public boolean itemInteractionForEntity(ItemStack var1, EntityPlayer var2, EntityLivingBase var3) {
      if (var3 instanceof EntityPig) {
         EntityPig var4 = (EntityPig)var3;
         if (!var4.getSaddled() && !var4.o_()) {
            var4.setSaddled(true);
            var4.o.a(var4, "mob.horse.leather", 0.5F, 1.0F);
            var1.stackSize--;
         }

         return true;
      } else {
         return false;
      }
   }

   public ItemSaddle() {
      this.h = 1;
      this.setCreativeTab(CreativeTabs.tabTransport);
   }

   @Override
   public boolean hitEntity(ItemStack var1, EntityLivingBase var2, EntityLivingBase var3) {
      this.itemInteractionForEntity(var1, (EntityPlayer)null, var2);
      return true;
   }
}
