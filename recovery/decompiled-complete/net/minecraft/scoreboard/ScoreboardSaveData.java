package net.minecraft.scoreboard;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldSavedData;
import net.optifine.shaders.config.ShaderOptionScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScoreboardSaveData extends WorldSavedData {
   public Scoreboard theScoreboard;
   public ShaderOptionScreen field_0003;
   public static Logger logger = LogManager.getLogger();
   public NBTTagCompound delayedInitNbt;

   public NBTTagList method_25766() {
      NBTTagList var1 = new NBTTagList();

      for (Score var3 : this.theScoreboard.getScores()) {
         if (var3.getObjective() != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setString("Name", var3.getPlayerName());
            var4.setString("Objective", var3.getObjective().getName());
            var4.setInteger("Score", var3.getScorePoints());
            var4.setBoolean("Locked", var3.isLocked());
            var1.appendTag(var4);
         }
      }

      return var1;
   }

   public void readDisplayConfig(NBTTagCompound var1) {
      for (int var2 = 0; var2 < 19; var2++) {
         if (var1.hasKey("slot_" + var2, 8)) {
            String var3 = var1.getString("slot_" + var2);
            ScoreObjective var4 = this.theScoreboard.getObjective(var3);
            this.theScoreboard.setObjectiveInDisplaySlot(var2, var4);
         }
      }
   }

   public void readScores(NBTTagList var1) {
      for (int var2 = 0; var2 < var1.tagCount(); var2++) {
         NBTTagCompound var3 = var1.getCompoundTagAt(var2);
         ScoreObjective var4 = this.theScoreboard.getObjective(var3.getString("Objective"));
         String var5 = var3.getString("Name");
         if (var5.length() > 40) {
            var5 = var5.substring(0, 40);
         }

         Score var6 = this.theScoreboard.getValueFromObjective(var5, var4);
         var6.setScorePoints(var3.getInteger("Score"));
         if (var3.hasKey("Locked")) {
            var6.setLocked(var3.getBoolean("Locked"));
         }
      }
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      if (this.theScoreboard == null) {
         this.delayedInitNbt = var1;
      } else {
         this.readObjectives(var1.getTagList("Objectives", 10));
         this.readScores(var1.getTagList("PlayerScores", 10));
         if (var1.hasKey("DisplaySlots", 10)) {
            this.readDisplayConfig(var1.getCompoundTag("DisplaySlots"));
         }

         if (var1.hasKey("Teams", 9)) {
            this.readTeams(var1.getTagList("Teams", 10));
         }
      }
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      if (this.theScoreboard == null) {
         logger.warn("Tried to save scoreboard without having a scoreboard...");
      } else {
         var1.setTag("Objectives", this.method_25764());
         var1.setTag("PlayerScores", this.method_25766());
         var1.setTag("Teams", this.method_25772());
         this.func_96497_d(var1);
      }
   }

   public void func_96502_a(ScorePlayerTeam var1, NBTTagList var2) {
      for (int var3 = 0; var3 < var2.tagCount(); var3++) {
         this.theScoreboard.addPlayerToTeam(var2.getStringTagAt(var3), var1.getRegisteredName());
      }
   }

   public void readObjectives(NBTTagList var1) {
      for (int var2 = 0; var2 < var1.tagCount(); var2++) {
         NBTTagCompound var3 = var1.getCompoundTagAt(var2);
         IScoreObjectiveCriteria var4 = IScoreObjectiveCriteria.INSTANCES.get(var3.getString("CriteriaName"));
         if (var4 != null) {
            String var5 = var3.getString("Name");
            if (var5.length() > 16) {
               var5 = var5.substring(0, 16);
            }

            ScoreObjective var6 = this.theScoreboard.addScoreObjective(var5, var4);
            var6.setDisplayName(var3.getString("DisplayName"));
            var6.setRenderType(IScoreObjectiveCriteria$EnumRenderType.func_178795_a(var3.getString("RenderType")));
         }
      }
   }

   public NBTTagList method_25772() {
      NBTTagList var1 = new NBTTagList();

      for (ScorePlayerTeam var3 : this.theScoreboard.getTeams()) {
         NBTTagCompound var4 = new NBTTagCompound();
         var4.setString("Name", var3.getRegisteredName());
         var4.setString("DisplayName", var3.getTeamName());
         if (var3.getChatFormat().getColorIndex() >= 0) {
            var4.setString("TeamColor", var3.getChatFormat().getFriendlyName());
         }

         var4.setString("Prefix", var3.getColorPrefix());
         var4.setString("Suffix", var3.getColorSuffix());
         var4.setBoolean("AllowFriendlyFire", var3.getAllowFriendlyFire());
         var4.setBoolean("SeeFriendlyInvisibles", var3.getSeeFriendlyInvisiblesEnabled());
         var4.setString("NameTagVisibility", var3.getNameTagVisibility().internalName);
         var4.setString("DeathMessageVisibility", var3.getDeathMessageVisibility().internalName);
         NBTTagList var5 = new NBTTagList();

         for (String var7 : var3.getMembershipCollection()) {
            var5.appendTag(new NBTTagString(var7));
         }

         var4.setTag("Players", var5);
         var1.appendTag(var4);
      }

      return var1;
   }

   public ScoreboardSaveData(String var1) {
      super(var1);
   }

   public void readTeams(NBTTagList var1) {
      for (int var2 = 0; var2 < var1.tagCount(); var2++) {
         NBTTagCompound var3 = var1.getCompoundTagAt(var2);
         String var4 = var3.getString("Name");
         if (var4.length() > 16) {
            var4 = var4.substring(0, 16);
         }

         ScorePlayerTeam var5 = this.theScoreboard.createTeam(var4);
         String var6 = var3.getString("DisplayName");
         if (var6.length() > 32) {
            var6 = var6.substring(0, 32);
         }

         var5.setTeamName(var6);
         if (var3.hasKey("TeamColor", 8)) {
            var5.setChatFormat(EnumChatFormatting.getValueByName(var3.getString("TeamColor")));
         }

         var5.setNamePrefix(var3.getString("Prefix"));
         var5.setNameSuffix(var3.getString("Suffix"));
         if (var3.hasKey("AllowFriendlyFire", 99)) {
            var5.setAllowFriendlyFire(var3.getBoolean("AllowFriendlyFire"));
         }

         if (var3.hasKey("SeeFriendlyInvisibles", 99)) {
            var5.setSeeFriendlyInvisiblesEnabled(var3.getBoolean("SeeFriendlyInvisibles"));
         }

         if (var3.hasKey("NameTagVisibility", 8)) {
            Team$EnumVisible var7 = Team$EnumVisible.func_178824_a(var3.getString("NameTagVisibility"));
            if (var7 != null) {
               var5.setNameTagVisibility(var7);
            }
         }

         if (var3.hasKey("DeathMessageVisibility", 8)) {
            Team$EnumVisible var8 = Team$EnumVisible.func_178824_a(var3.getString("DeathMessageVisibility"));
            if (var8 != null) {
               var5.setDeathMessageVisibility(var8);
            }
         }

         this.func_96502_a(var5, var3.getTagList("Players", 8));
      }
   }

   public ScoreboardSaveData() {
      this("scoreboard");
   }

   public NBTTagList method_25764() {
      NBTTagList var1 = new NBTTagList();

      for (ScoreObjective var3 : this.theScoreboard.getScoreObjectives()) {
         if (var3.getCriteria() != null) {
            NBTTagCompound var4 = new NBTTagCompound();
            var4.setString("Name", var3.getName());
            var4.setString("CriteriaName", var3.getCriteria().getName());
            var4.setString("DisplayName", var3.getDisplayName());
            var4.setString("RenderType", var3.getRenderType().func_178796_a());
            var1.appendTag(var4);
         }
      }

      return var1;
   }

   public void setScoreboard(Scoreboard var1) {
      this.theScoreboard = var1;
      if (this.delayedInitNbt != null) {
         this.readFromNBT(this.delayedInitNbt);
      }
   }

   public void func_96497_d(NBTTagCompound var1) {
      NBTTagCompound var2 = new NBTTagCompound();
      boolean var3 = false;

      for (int var4 = 0; var4 < 19; var4++) {
         ScoreObjective var5 = this.theScoreboard.getObjectiveInDisplaySlot(var4);
         if (var5 != null) {
            var2.setString("slot_" + var4, var5.getName());
            var3 = true;
         }
      }

      if (var3) {
         var1.setTag("DisplaySlots", var2);
      }
   }
}
