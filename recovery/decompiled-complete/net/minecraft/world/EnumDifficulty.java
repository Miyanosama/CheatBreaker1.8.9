package net.minecraft.world;

import com.cheatbreaker.client.ui.mainmenu.AccountList;
import net.minecraft.client.renderer.texture.LayeredColorMaskTexture;
import net.optifine.util.MathUtilsTest;

public enum EnumDifficulty {
   HARD(3, "options.difficulty.hard"),
   PEACEFUL(0, "options.difficulty.peaceful"),
   NORMAL(2, "options.difficulty.normal"),
   EASY(1, "options.difficulty.easy");
   public AccountList field_0005;
   // $VF: synthetic field
   public static EnumDifficulty[] $VALUES = new EnumDifficulty[]{EnumDifficulty.PEACEFUL, EnumDifficulty.EASY, EnumDifficulty.NORMAL, HARD};
   public int difficultyId;
   public LayeredColorMaskTexture field_0001;
   public static EnumDifficulty[] difficultyEnums = new EnumDifficulty[values().length];
   public MathUtilsTest field_0006;
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

   public EnumDifficulty(int var3, String var4) {
      this.difficultyId = var3;
      this.difficultyResourceKey = var4;
   }

   public static EnumDifficulty getDifficultyEnum(int var0) {
      return difficultyEnums[var0 % difficultyEnums.length];
   }
}
