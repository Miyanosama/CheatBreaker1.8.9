package net.minecraft.world.gen.structure;

import net.minecraft.client.particle.EffectRenderer$3;
import net.optifine.BlockDir;
import org.apache.log4j.net.SMTPAppender;

public class StructureStrongholdPieces$1 extends StructureStrongholdPieces$PieceWeight {
   public EffectRenderer$3 field_0001;
   public BlockDir field_0002;
   public SMTPAppender field_0000;

   public StructureStrongholdPieces$1(Class var1, int var2, int var3) {
      super(var1, var2, var3);
   }

   @Override
   public boolean canSpawnMoreStructuresOfType(int var1) {
      return super.canSpawnMoreStructuresOfType(var1) && var1 > 4;
   }
}
