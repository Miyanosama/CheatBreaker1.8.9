package net.minecraft.block;

import com.google.common.base.Predicate;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiKeyBindingList$KeyEntry;
import net.minecraft.world.storage.WorldInfo$3;

public class BlockNewLog$1 implements Predicate<BlockPlanks$EnumType> {
   public EntityPlayerSP field_0001;
   public WorldInfo$3 field_0002;
   public GuiKeyBindingList$KeyEntry field_0000;

   public boolean apply(BlockPlanks$EnumType var1) {
      return var1.getMetadata() >= 4;
   }
}
