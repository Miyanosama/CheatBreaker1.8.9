package net.minecraft.item;

import com.cheatbreaker.client.ui.element.type.ColorPickerColorElement;
import io.netty.util.concurrent.GlobalEventExecutor$PurgeTask;
import net.minecraft.block.BlockAnvil$Anvil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.optifine.shaders.config.ScreenShaderOptions;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.lf5.viewer.LogFactor5InputDialog$2;

public class ItemEgg extends Item {
   public GlobalEventExecutor$PurgeTask field_0002;
   public ColorPickerColorElement field_0003;
   public ScreenShaderOptions field_0004;
   public BlockAnvil$Anvil field_0005;
   public LogFactor5InputDialog$2 field_0001;
   public LogLog field_0000;

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if (!var3.bA.isCreativeMode) {
         var1.stackSize--;
      }

      var2.a(var3, "random.bow", 0.5F, 0.4F / (g.nextFloat() * 0.4F + 0.8F));
      if (!var2.D) {
         var2.spawnEntityInWorld(new EntityEgg(var2, var3));
      }

      var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return var1;
   }

   public ItemEgg() {
      this.h = 16;
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }
}
