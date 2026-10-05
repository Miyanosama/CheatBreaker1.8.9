package net.minecraft.scoreboard;

import java.util.Comparator;
import junit.runner.ReloadingTestSuiteLoader;

public class Score$1 implements Comparator<Score> {
   public ReloadingTestSuiteLoader field_0000;

   public int compare(Score var1, Score var2) {
      return var1.getScorePoints() > var2.getScorePoints()
         ? 1
         : (var1.getScorePoints() < var2.getScorePoints() ? -1 : var2.getPlayerName().compareToIgnoreCase(var1.getPlayerName()));
   }
}
