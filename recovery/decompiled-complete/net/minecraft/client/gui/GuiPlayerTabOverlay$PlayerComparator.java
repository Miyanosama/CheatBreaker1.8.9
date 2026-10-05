package net.minecraft.client.gui;

import com.google.common.collect.ComparisonChain;
import java.util.Comparator;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.world.WorldSettings$GameType;
import recovered.unidentified.UnidentifiedClass3688;

public class GuiPlayerTabOverlay$PlayerComparator implements Comparator<NetworkPlayerInfo> {
   public UnidentifiedClass3688 field_0000;

   public int compare(NetworkPlayerInfo var1, NetworkPlayerInfo var2) {
      ScorePlayerTeam var3 = var1.getPlayerTeam();
      ScorePlayerTeam var4 = var2.getPlayerTeam();
      return ComparisonChain.start()
         .compareTrueFirst(var1.getGameType() != WorldSettings$GameType.SPECTATOR, var2.getGameType() != WorldSettings$GameType.SPECTATOR)
         .compare(var3 != null ? var3.getRegisteredName() : "", var4 != null ? var4.getRegisteredName() : "")
         .compare(var1.getGameProfile().getName(), var2.getGameProfile().getName())
         .result();
   }

   public GuiPlayerTabOverlay$PlayerComparator() {
   }
}
