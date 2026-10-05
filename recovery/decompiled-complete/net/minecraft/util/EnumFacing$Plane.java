package net.minecraft.util;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.client.renderer.block.model.BlockPart$1;
import net.minecraft.world.Teleporter;
import net.optifine.ChunkPosComparator;
import net.optifine.util.MathUtils;
import recovered.unidentified.UnidentifiedClass4511;

public enum EnumFacing$Plane implements Predicate<EnumFacing>, Iterable<EnumFacing> {
   HORIZONTAL,
   VERTICAL;
   public ChunkPosComparator field_0003;
   public UnidentifiedClass4511 field_0002;
   public MathUtils field_0005;
   // $VF: synthetic field
   public static EnumFacing$Plane[] $VALUES = new EnumFacing$Plane[]{HORIZONTAL, EnumFacing$Plane.VERTICAL};
   public BlockPart$1 field_0001;
   public Teleporter field_0007;

   public boolean apply(EnumFacing var1) {
      return var1 != null && var1.getAxis().getPlane() == this;
   }

   public EnumFacing[] facings() {
      switch (EnumFacing$1.$SwitchMap$net$minecraft$util$EnumFacing$Plane[this.ordinal()]) {
         case 1:
            return new EnumFacing[]{EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST};
         case 2:
            return new EnumFacing[]{EnumFacing.UP, EnumFacing.DOWN};
         default:
            throw new Error("Someone's been tampering with the universe!");
      }
   }

   public EnumFacing random(Random var1) {
      EnumFacing[] var2 = this.facings();
      return var2[var1.nextInt(var2.length)];
   }

   @Override
   public Iterator<EnumFacing> iterator() {
      return Iterators.forArray(this.facings());
   }
}
