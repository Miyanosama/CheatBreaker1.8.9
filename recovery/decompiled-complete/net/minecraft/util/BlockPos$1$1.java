package net.minecraft.util;

import com.google.common.collect.AbstractIterator;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.particle.EntityFirework$Factory;

public class BlockPos$1$1 extends AbstractIterator<BlockPos> {
   public BlockPos lastReturned;
   public SoundRegistry field_0004;
   public BlockDispenser field_0003;
   public EntityFirework$Factory field_0000;

   public BlockPos computeNext() {
      if (this.lastReturned == null) {
         this.lastReturned = this.field_179310_a.field_179307_a;
         return this.lastReturned;
      } else if (this.lastReturned.equals(this.field_179310_a.field_179306_b)) {
         return (BlockPos)this.endOfData();
      } else {
         int var1 = this.lastReturned.getX();
         int var2 = this.lastReturned.getY();
         int var3 = this.lastReturned.getZ();
         if (var1 < this.field_179310_a.field_179306_b.getX()) {
            var1++;
         } else if (var2 < this.field_179310_a.field_179306_b.getY()) {
            var1 = this.field_179310_a.field_179307_a.getX();
            var2++;
         } else if (var3 < this.field_179310_a.field_179306_b.getZ()) {
            var1 = this.field_179310_a.field_179307_a.getX();
            var2 = this.field_179310_a.field_179307_a.getY();
            var3++;
         }

         this.lastReturned = new BlockPos(var1, var2, var3);
         return this.lastReturned;
      }
   }

   public BlockPos$1$1(BlockPos$1 var1) {
      this.field_179310_a = var1;
      super();
      this.lastReturned = null;
   }
}
