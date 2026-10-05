package net.minecraft.world.gen.structure;

import com.cheatbreaker.client.module.type.NametagModule;
import net.minecraft.item.ItemCoal;
import net.minecraft.world.storage.WorldInfo$4;
import net.optifine.CustomLoadingScreen;

public class StructureStrongholdPieces$PieceWeight {
   public WorldInfo$4 field_0005;
   public int pieceWeight;
   public int instancesLimit;
   public int instancesSpawned;
   public Class<? extends StructureStrongholdPieces$Stronghold> pieceClass;
   public ItemCoal field_0003;
   public CustomLoadingScreen field_0007;
   public NametagModule field_0000;

   public StructureStrongholdPieces$PieceWeight(Class<? extends StructureStrongholdPieces$Stronghold> var1, int var2, int var3) {
      this.pieceClass = var1;
      this.pieceWeight = var2;
      this.instancesLimit = var3;
   }

   public boolean canSpawnMoreStructuresOfType(int var1) {
      return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
   }

   public boolean canSpawnMoreStructures() {
      return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
   }
}
