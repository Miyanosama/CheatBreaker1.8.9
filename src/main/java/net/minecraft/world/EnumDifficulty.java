package net.minecraft.world;

public enum EnumDifficulty {
      PEACEFUL(0, "options.difficulty.peaceful"),
      EASY(1, "options.difficulty.easy"),
      NORMAL(2, "options.difficulty.normal"),
      HARD(3, "options.difficulty.hard");
   public static EnumDifficulty[] $VALUES = new EnumDifficulty[]{EnumDifficulty.PEACEFUL, EnumDifficulty.EASY, EnumDifficulty.NORMAL, HARD};
   public int difficultyId;
   public static EnumDifficulty[] difficultyEnums = new EnumDifficulty[values().length];
   public String difficultyResourceKey;

   public String getDifficultyResourceKey() {
      return this.difficultyResourceKey;
   }

   public int getDifficultyId() {
      return this.difficultyId;
   }

   static {
      for (EnumDifficulty var3 : values()) {
         difficultyEnums[var3.difficultyId] = var3;
      }
   }

   EnumDifficulty(int var3, String var4) {
      this.difficultyId = var3;
      this.difficultyResourceKey = var4;
   }

   public static EnumDifficulty getDifficultyEnum(int var0) {
      return difficultyEnums[var0 % difficultyEnums.length];
   }
}
