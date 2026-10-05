package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import io.netty.channel.group.CombinedIterator;
import javax.vecmath.Tuple4d;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.stream.IngestServerTester$2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.world.gen.structure.StructureVillagePieces$Field1;

public class EntityAINearestAttackableTarget$1 implements Predicate<T> {
   public IngestServerTester$2 field_0006;
   public StructureVillagePieces$Field1 field_0002;
   public BlockFlower field_0005;
   public C00Handshake field_0000;
   public Tuple4d field_0007;
   public CombinedIterator field_0004;

   public boolean apply(T var1) {
      if (this.field_111103_c != null && !this.field_111103_c.apply(var1)) {
         return false;
      } else {
         if (var1 instanceof EntityPlayer) {
            double var2 = this.field_111102_d.f();
            if (var1.isSneaking()) {
               var2 *= 0.8F;
            }

            if (var1.isInvisible()) {
               float var4 = ((EntityPlayer)var1).getArmorVisibility();
               if (var4 < 0.1F) {
                  var4 = 0.1F;
               }

               var2 *= 0.7F * var4;
            }

            if (var1.g(this.field_111102_d.e) > var2) {
               return false;
            }
         }

         return this.field_111102_d.a(var1, false);
      }
   }

   public EntityAINearestAttackableTarget$1(EntityAINearestAttackableTarget var1, Predicate var2) {
      this.field_111102_d = var1;
      this.field_111103_c = var2;
      super();
   }
}
