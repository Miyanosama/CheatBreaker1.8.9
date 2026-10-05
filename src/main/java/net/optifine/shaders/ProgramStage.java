package net.optifine.shaders;

public enum ProgramStage {
      NONE(""),
      SHADOW("shadow"),
      GBUFFERS("gbuffers"),
      DEFERRED("deferred"),
      COMPOSITE("composite");

   public static ProgramStage[] $VALUES = new ProgramStage[]{
      ProgramStage.NONE, ProgramStage.SHADOW, ProgramStage.GBUFFERS, ProgramStage.DEFERRED, ProgramStage.COMPOSITE
   };
   public String name;

   public String getName() {
      return this.name;
   }

   ProgramStage(String var3) {
      this.name = var3;
   }
}
