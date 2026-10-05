package net.minecraft.entity.boss;

import io.netty.channel.group.ChannelMatchers$ClassMatcher;
import net.minecraft.client.renderer.BlockModelShapes$1;
import net.minecraft.command.server.CommandDeOp;
import net.minecraft.server.management.UserList$1;
import net.optifine.BetterGrass;

public class BossStatus {
   public CommandDeOp field_0004;
   public UserList$1 field_0007;
   public static float healthScale;
   public BetterGrass field_0006;
   public BlockModelShapes$1 field_0000;
   public ChannelMatchers$ClassMatcher field_0001;
   public static String bossName;
   public static boolean hasColorModifier;
   public static int statusBarTime;

   public static void setBossStatus(IBossDisplayData var0, boolean var1) {
      healthScale = var0.getHealth() / var0.getMaxHealth();
      statusBarTime = 100;
      bossName = var0.getDisplayName().getFormattedText();
      hasColorModifier = var1;
   }
}
