package net.optifine.config;

import com.cheatbreaker.client.util.SessionServer;
import io.netty.buffer.ByteBufProcessor$7;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$SimpleTopRoom;

public class RangeInt {
   public ByteBufProcessor$7 field_0003;
   public EntityAIFindEntityNearestPlayer field_0005;
   public StructureOceanMonumentPieces$SimpleTopRoom field_0002;
   public int max;
   public int min;
   public SessionServer field_0001;

   @Override
   public String toString() {
      return "min: " + this.min + ", max: " + this.max;
   }

   public RangeInt(int var1, int var2) {
      this.min = Math.min(var1, var2);
      this.max = Math.max(var1, var2);
   }

   public int getMin() {
      return this.min;
   }

   public boolean isInRange(int var1) {
      return var1 < this.min ? false : var1 <= this.max;
   }

   public int getMax() {
      return this.max;
   }
}
