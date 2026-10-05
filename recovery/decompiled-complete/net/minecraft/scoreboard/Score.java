package net.minecraft.scoreboard;

import java.util.Comparator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.world.GameRules$Value;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import org.apache.log4j.chainsaw.ControlPanel$6;

public class Score {
   public static Comparator<Score> scoreComparator = new Score$1();
   public boolean forceUpdate;
   public S3DPacketDisplayScoreboard field_0004;
   public Scoreboard theScoreboard;
   public ScoreObjective theScoreObjective;
   public WorldGenGlowStone1 field_0002;
   public GameRules$Value field_0009;
   public String scorePlayerName;
   public boolean locked;
   public ControlPanel$6 field_0010;
   public int scorePoints;

   public int getScorePoints() {
      return this.scorePoints;
   }

   public void decreaseScore(int var1) {
      if (this.theScoreObjective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.setScorePoints(this.getScorePoints() - var1);
      }
   }

   public void func_96651_a(List<EntityPlayer> var1) {
      this.setScorePoints(this.theScoreObjective.getCriteria().setScore(var1));
   }

   public Score(Scoreboard var1, ScoreObjective var2, String var3) {
      this.theScoreboard = var1;
      this.theScoreObjective = var2;
      this.scorePlayerName = var3;
      this.forceUpdate = true;
   }

   public boolean isLocked() {
      return this.locked;
   }

   public ScoreObjective getObjective() {
      return this.theScoreObjective;
   }

   public String getPlayerName() {
      return this.scorePlayerName;
   }

   public Scoreboard getScoreScoreboard() {
      return this.theScoreboard;
   }

   public void setScorePoints(int var1) {
      int var2 = this.scorePoints;
      this.scorePoints = var1;
      if (var2 != var1 || this.forceUpdate) {
         this.forceUpdate = false;
         this.getScoreScoreboard().func_96536_a(this);
      }
   }

   public void setLocked(boolean var1) {
      this.locked = var1;
   }

   public void increseScore(int var1) {
      if (this.theScoreObjective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.setScorePoints(this.getScorePoints() + var1);
      }
   }

   public void func_96648_a() {
      if (this.theScoreObjective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.increseScore(1);
      }
   }
}
