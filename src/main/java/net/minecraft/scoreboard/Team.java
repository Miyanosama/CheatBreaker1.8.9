package net.minecraft.scoreboard;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;

public abstract class Team {
   public abstract String getRegisteredName();

   public abstract String formatString(String var1);

   public abstract boolean getSeeFriendlyInvisiblesEnabled();

   public abstract boolean getAllowFriendlyFire();

   public abstract Team.EnumVisible getDeathMessageVisibility();

   public boolean isSameTeam(Team var1) {
      return var1 == null ? false : this == var1;
   }

   public abstract Collection<String> getMembershipCollection();

   public abstract Team.EnumVisible getNameTagVisibility();

   public static enum EnumVisible {
      ALWAYS("always", 0),
      NEVER("never", 1),
      HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2),
      HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);
      public String internalName;
      public int id;
      // $VF: synthetic field
      public static Team.EnumVisible[] $VALUES = new Team.EnumVisible[]{ALWAYS, Team.EnumVisible.NEVER, HIDE_FOR_OTHER_TEAMS, HIDE_FOR_OWN_TEAM};
      public static Map<String, Team.EnumVisible> field_178828_g = Maps.newHashMap();

      EnumVisible(String var3, int var4) {
         this.internalName = var3;
         this.id = var4;
      }

      static {
         for (Team.EnumVisible var3 : values()) {
            field_178828_g.put(var3.internalName, var3);
         }
      }

      public static String[] func_178825_a() {
         return field_178828_g.keySet().toArray(new String[field_178828_g.size()]);
      }

      public static Team.EnumVisible func_178824_a(String var0) {
         return field_178828_g.get(var0);
      }
   }
}
