package net.minecraft.nbt;

import java.util.concurrent.Callable;
import junit.swingui.FailureRunView$1;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.storage.WorldInfo$8;
import net.optifine.shaders.EntityAliases;

public class NBTTagCompound$2 implements Callable<String> {
   public EntityAliases field_0003;
   public CreativeTabs field_0005;
   public WorldInfo$8 field_0004;
   public FailureRunView$1 field_0001;

   public String call() {
      return NBTBase.NBT_TYPES[this.field_82588_a];
   }

   public NBTTagCompound$2(NBTTagCompound var1, int var2) {
      this.field_82587_b = var1;
      this.field_82588_a = var2;
      super();
   }
}
