package net.minecraft.client.renderer.entity;

import net.minecraft.scoreboard.Team;

// $VF: synthetic class
public class RendererLivingEntity$EnumSwitch {
   public static int[] recoveredField72 = new int[Team.EnumVisible.values().length];

   static {
      try {
         recoveredField72[Team.EnumVisible.NEVER.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField72[Team.EnumVisible.HIDE_FOR_OTHER_TEAMS.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField72[Team.EnumVisible.HIDE_FOR_OWN_TEAM.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
