package net.minecraft.stats;

import io.netty.buffer.ByteBufProcessor$6;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Crossing2;
import net.optifine.NaturalProperties;
import net.optifine.expr.ConstantFloat;
import recovered.unidentified.UnidentifiedClass3253;

public class StatBase$2 implements IStatType {
   public NaturalProperties field_0002;
   public StructureNetherBridgePieces$Crossing2 field_0004;
   public ByteBufProcessor$6 field_0001;
   public UnidentifiedClass3253 field_0003;
   public ConstantFloat field_0000;

   @Override
   public String format(int var1) {
      double var2 = var1 / 20.0;
      double var4 = var2 / 60.0;
      double var6 = var4 / 60.0;
      double var8 = var6 / 24.0;
      double var10 = var8 / 365.0;
      return var10 > 0.5
         ? StatBase.access$100().format(var10) + " y"
         : (
            var8 > 0.5
               ? StatBase.access$100().format(var8) + " d"
               : (var6 > 0.5 ? StatBase.access$100().format(var6) + " h" : (var4 > 0.5 ? StatBase.access$100().format(var4) + " m" : var2 + " s"))
         );
   }
}
