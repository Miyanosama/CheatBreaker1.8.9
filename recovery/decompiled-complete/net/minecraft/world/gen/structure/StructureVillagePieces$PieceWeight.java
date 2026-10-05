package net.minecraft.world.gen.structure;

import io.netty.channel.sctp.SctpNotificationHandler;
import net.minecraft.entity.ai.EntityAIFollowGolem;

public class StructureVillagePieces$PieceWeight {
   public Class<? extends StructureVillagePieces$Village> villagePieceClass;
   public int villagePiecesLimit;
   public int villagePieceWeight;
   public int villagePiecesSpawned;
   public EntityAIFollowGolem field_0000;
   public SctpNotificationHandler field_0001;
   public StructureMineshaftPieces$1 field_0006;

   public StructureVillagePieces$PieceWeight(Class<? extends StructureVillagePieces$Village> var1, int var2, int var3) {
      this.villagePieceClass = var1;
      this.villagePieceWeight = var2;
      this.villagePiecesLimit = var3;
   }

   public boolean canSpawnMoreVillagePieces() {
      return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
   }

   public boolean canSpawnMoreVillagePiecesOfType(int var1) {
      return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
   }
}
