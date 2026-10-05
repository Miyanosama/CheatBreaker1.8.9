package javazoom.jl.player.advanced;

import net.minecraft.nbt.NBTBase$NBTPrimitive;
import net.minecraft.world.storage.SaveFormatOld;
import recovered.unidentified.UnidentifiedClass1810;

public class jlap$1 extends Thread {
   public UnidentifiedClass1810 __junk3673923598150495551;
   public NBTBase$NBTPrimitive __junk2448156624454300795;
   public SaveFormatOld __junk4982841333146365767;

   public jlap$1(AdvancedPlayer var1, int var2, int var3) {
      this.val$player = var1;
      this.val$start = var2;
      this.val$end = var3;
      super();
   }

   @Override
   public void run() {
      try {
         this.val$player.play(this.val$start, this.val$end);
      } catch (Exception var2) {
         throw new RuntimeException(var2.getMessage());
      }
   }
}
