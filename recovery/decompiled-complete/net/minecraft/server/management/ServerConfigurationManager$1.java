package net.minecraft.server.management;

import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.network.play.server.S44PacketWorldBorder$Action;
import net.minecraft.profiler.PlayerUsageSnooper$1;
import net.minecraft.world.border.IBorderListener;
import net.minecraft.world.border.WorldBorder;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.entity.model.ModelAdapterPig;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$24;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$32;

public class ServerConfigurationManager$1 implements IBorderListener {
   public LogBrokerMonitor$24 field_0003;
   public LogBrokerMonitor$32 field_0005;
   public ModelAdapter field_0002;
   public PlayerUsageSnooper$1 field_0000;
   public ModelAdapterPig field_0001;

   @Override
   public void onTransitionStarted(WorldBorder var1, double var2, double var4, long var6) {
      this.field_177697_a.sendPacketToAllPlayers(new S44PacketWorldBorder(var1, S44PacketWorldBorder$Action.LERP_SIZE));
   }

   @Override
   public void onWarningTimeChanged(WorldBorder var1, int var2) {
      this.field_177697_a.sendPacketToAllPlayers(new S44PacketWorldBorder(var1, S44PacketWorldBorder$Action.SET_WARNING_TIME));
   }

   @Override
   public void onCenterChanged(WorldBorder var1, double var2, double var4) {
      this.field_177697_a.sendPacketToAllPlayers(new S44PacketWorldBorder(var1, S44PacketWorldBorder$Action.SET_CENTER));
   }

   @Override
   public void onSizeChanged(WorldBorder var1, double var2) {
      this.field_177697_a.sendPacketToAllPlayers(new S44PacketWorldBorder(var1, S44PacketWorldBorder$Action.SET_SIZE));
   }

   @Override
   public void method_02434(WorldBorder var1, double var2) {
   }

   public ServerConfigurationManager$1(ServerConfigurationManager var1) {
      this.field_177697_a = var1;
      super();
   }

   @Override
   public void method_02429(WorldBorder var1, double var2) {
   }

   @Override
   public void onWarningDistanceChanged(WorldBorder var1, int var2) {
      this.field_177697_a.sendPacketToAllPlayers(new S44PacketWorldBorder(var1, S44PacketWorldBorder$Action.SET_WARNING_BLOCKS));
   }
}
