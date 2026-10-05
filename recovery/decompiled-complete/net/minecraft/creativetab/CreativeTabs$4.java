package net.minecraft.creativetab;

import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.entity.monster.EntityIronGolem$AINearestAttackableTargetNonCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class CreativeTabs$4 extends CreativeTabs {
   public WorldVertexBufferUploader field_0001;
   public EntityIronGolem$AINearestAttackableTargetNonCreeper field_0000;

   @Override
   public Item getTabIconItem() {
      return Item.getItemFromBlock(Blocks.chest);
   }

   public CreativeTabs$4(int var1, String var2) {
      super(var1, var2);
   }
}
