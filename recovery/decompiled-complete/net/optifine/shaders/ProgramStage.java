package net.optifine.shaders;

import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.command.server.CommandMessage;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget$1;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import org.java_websocket.exceptions.IncompleteHandshakeException;

public enum ProgramStage {
   GBUFFERS("gbuffers"),
   SHADOW("shadow"),
   NONE(""),
   COMPOSITE("composite"),
   DEFERRED("deferred");

   // $VF: synthetic field
   public static ProgramStage[] $VALUES = new ProgramStage[]{
      ProgramStage.NONE, ProgramStage.SHADOW, ProgramStage.GBUFFERS, ProgramStage.DEFERRED, ProgramStage.COMPOSITE
   };
   public EntityBreakingFX field_0001;
   public CommandMessage field_0002;
   public IncompleteHandshakeException field_0010;
   public GenLayerBiomeEdge field_0003;
   public EntityAINearestAttackableTarget$1 field_0011;
   public String name;

   public String getName() {
      return this.name;
   }

   public ProgramStage(String var3) {
      this.name = var3;
   }
}
