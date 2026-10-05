package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.util.IntHashMap;

public class ItemAnvilBlock extends ItemMultiTexture {
   public IntHashMap field_0000;
   public EntityEgg field_0001;
   public GuiVideoSettings field_0002;

   public ItemAnvilBlock(Block var1) {
      super(var1, var1, new String[]{"intact", "slightlyDamaged", "veryDamaged"});
   }

   @Override
   public int getMetadata(int var1) {
      return var1 << 2;
   }
}
