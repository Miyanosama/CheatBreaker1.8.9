package net.minecraft.item;

import io.netty.handler.codec.compression.ZlibEncoder;
import net.minecraft.client.particle.EntityFirework$Factory;
import net.minecraft.crash.CrashReport$5;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Bootstrap$9;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.optifine.shaders.ShadersTex;
import org.java_websocket.enums.ReadyState;
import org.json.CookieList;

public class ItemEnderPearl extends Item {
   public CookieList field_0002;
   public EntityFirework$Factory field_0004;
   public ReadyState field_0005;
   public CrashReport$5 field_0006;
   public ShadersTex field_0001;
   public Bootstrap$9 field_0000;
   public ZlibEncoder field_0003;

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if (var3.bA.isCreativeMode) {
         return var1;
      } else {
         var1.stackSize--;
         var2.a(var3, "random.bow", 0.5F, 0.4F / (g.nextFloat() * 0.4F + 0.8F));
         if (!var2.D) {
            var2.spawnEntityInWorld(new EntityEnderPearl(var2, var3));
         }

         var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
         return var1;
      }
   }

   public ItemEnderPearl() {
      this.h = 16;
      this.setCreativeTab(CreativeTabs.tabMisc);
   }
}
