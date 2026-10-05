package net.minecraft.block;

import com.google.common.base.Predicate;
import io.netty.handler.codec.http.multipart.MixedAttribute;
import javax.vecmath.SingularMatrixException;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.storage.DerivedWorldInfo;
import recovered.unidentified.UnidentifiedClass0999;

public class BlockStem$1 implements Predicate<EnumFacing> {
   public SingularMatrixException field_0001;
   public MixedAttribute field_0003;
   public DerivedWorldInfo field_0000;
   public UnidentifiedClass0999 field_0002;

   public boolean apply(EnumFacing var1) {
      return var1 != EnumFacing.DOWN;
   }
}
