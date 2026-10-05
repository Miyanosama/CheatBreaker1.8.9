package net.minecraft.nbt;

import com.cheatbreaker.client.module.type.TextHudModule;
import java.util.concurrent.Callable;
import net.minecraft.block.BlockRailDetector$1;
import net.optifine.expr.FunctionBool;

public class NBTTagCompound$1 implements Callable<String> {
   public BlockRailDetector$1 field_0004;
   public FunctionBool field_0001;
   public TextHudModule field_0000;

   public String call() {
      return NBTBase.NBT_TYPES[((NBTBase)NBTTagCompound.access$000(this.field_82584_b).get(this.field_82585_a)).getId()];
   }

   public NBTTagCompound$1(NBTTagCompound var1, String var2) {
      this.field_82584_b = var1;
      this.field_82585_a = var2;
      super();
   }
}
