package recovered.unidentified;

import io.netty.channel.group.ChannelMatchers$ClassMatcher;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.client.stream.MetadataPlayerDeath;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.optifine.expr.FunctionFloat;
import net.optifine.model.ModelUtils;

public class UnidentifiedClass0243 extends Item {
   public MetadataPlayerDeath field_0001;
   public FunctionFloat field_0002;
   public ChannelMatchers$ClassMatcher field_0003;
   public ModelUtils field_0004;
   public RenderChicken field_0000;

   @Override
   public EnumAction getItemUseAction(ItemStack var1) {
      return EnumAction.DRINK;
   }

   public UnidentifiedClass0243() {
      this.c(1);
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   @Override
   public ItemStack onItemUseFinish(ItemStack var1, World var2, EntityPlayer var3) {
      if (!var3.bA.isCreativeMode) {
         var1.stackSize--;
      }

      if (!var2.D) {
         var3.clearActivePotions();
      }

      var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return var1.stackSize <= 0 ? new ItemStack(Items.bucket) : var1;
   }

   @Override
   public int getMaxItemUseDuration(ItemStack var1) {
      return 32;
   }

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
      return var1;
   }
}
