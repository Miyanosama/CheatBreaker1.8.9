package net.minecraft.network.play.client;

import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.optifine.entity.model.ModelAdapterSheepWool;

public enum C19PacketResourcePackStatus$Action {
   DECLINED,
   FAILED_DOWNLOAD,
   SUCCESSFULLY_LOADED,
   ACCEPTED;
   public EntityAIFindEntityNearestPlayer field_0004;
   // $VF: synthetic field
   public static C19PacketResourcePackStatus$Action[] $VALUES = new C19PacketResourcePackStatus$Action[]{
      SUCCESSFULLY_LOADED, DECLINED, FAILED_DOWNLOAD, C19PacketResourcePackStatus$Action.ACCEPTED
   };
   public ModelAdapterSheepWool field_0001;
}
