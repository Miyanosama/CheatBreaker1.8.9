package net.minecraft.scoreboard;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import net.minecraft.util.EnumChatFormatting;

public class ScorePlayerTeam extends Team {
   public Team.EnumVisible nameTagVisibility;
   public boolean canSeeFriendlyInvisibles;
   public boolean allowFriendlyFire;
   public String teamNameSPT;
   public Team.EnumVisible deathMessageVisibility;
   public String registeredName;
   public String colorSuffix;
   public Set<String> membershipSet = Sets.newHashSet();
   public Scoreboard theScoreboard;
   public EnumChatFormatting chatFormat;
   public String namePrefixSPT = "";

   public String getColorPrefix() {
      return this.namePrefixSPT;
   }

   public EnumChatFormatting getChatFormat() {
      return this.chatFormat;
   }

   public void func_98298_a(int var1) {
      this.setAllowFriendlyFire((var1 & 1) > 0);
      this.setSeeFriendlyInvisiblesEnabled((var1 & 2) > 0);
   }

   public String getTeamName() {
      return this.teamNameSPT;
   }

   public void setNameSuffix(String var1) {
      this.colorSuffix = var1;
      this.theScoreboard.sendTeamUpdate(this);
   }

   public int func_98299_i() {
      int var1 = 0;
      if (this.getAllowFriendlyFire()) {
         var1 |= 1;
      }

      if (this.getSeeFriendlyInvisiblesEnabled()) {
         var1 |= 2;
      }

      return var1;
   }

   public ScorePlayerTeam(Scoreboard var1, String var2) {
      this.colorSuffix = "";
      this.allowFriendlyFire = true;
      this.canSeeFriendlyInvisibles = true;
      this.nameTagVisibility = Team.EnumVisible.ALWAYS;
      this.deathMessageVisibility = Team.EnumVisible.ALWAYS;
      this.chatFormat = EnumChatFormatting.RESET;
      this.theScoreboard = var1;
      this.registeredName = var2;
      this.teamNameSPT = var2;
   }

   @Override
   public String formatString(String var1) {
      return this.getColorPrefix() + var1 + this.getColorSuffix();
   }

   public void setSeeFriendlyInvisiblesEnabled(boolean var1) {
      this.canSeeFriendlyInvisibles = var1;
      this.theScoreboard.sendTeamUpdate(this);
   }

   public void setDeathMessageVisibility(Team.EnumVisible var1) {
      this.deathMessageVisibility = var1;
      this.theScoreboard.sendTeamUpdate(this);
   }

   @Override
   public boolean getSeeFriendlyInvisiblesEnabled() {
      return this.canSeeFriendlyInvisibles;
   }

   @Override
   public Collection<String> getMembershipCollection() {
      return this.membershipSet;
   }

   public String getColorSuffix() {
      return this.colorSuffix;
   }

   @Override
   public String getRegisteredName() {
      return this.registeredName;
   }

   public void setNamePrefix(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Prefix cannot be null");
      } else {
         this.namePrefixSPT = var1;
         this.theScoreboard.sendTeamUpdate(this);
      }
   }

   public void setNameTagVisibility(Team.EnumVisible var1) {
      this.nameTagVisibility = var1;
      this.theScoreboard.sendTeamUpdate(this);
   }

   public void setChatFormat(EnumChatFormatting var1) {
      this.chatFormat = var1;
   }

   @Override
   public Team.EnumVisible getNameTagVisibility() {
      return this.nameTagVisibility;
   }

   @Override
   public boolean getAllowFriendlyFire() {
      return this.allowFriendlyFire;
   }

   public void setAllowFriendlyFire(boolean var1) {
      this.allowFriendlyFire = var1;
      this.theScoreboard.sendTeamUpdate(this);
   }

   @Override
   public Team.EnumVisible getDeathMessageVisibility() {
      return this.deathMessageVisibility;
   }

   public void setTeamName(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Name cannot be null");
      } else {
         this.teamNameSPT = var1;
         this.theScoreboard.sendTeamUpdate(this);
      }
   }

   public static String formatPlayerName(Team var0, String var1) {
      return var0 == null ? var1 : var0.formatString(var1);
   }
}
