package org.apache.log4j.lf5.viewer;

import io.netty.util.concurrent.AbstractFuture;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.optifine.entity.model.ModelAdapterRabbit;
import org.json.JSONException;

public class LogBrokerMonitor$17 implements ActionListener {
   public AbstractFuture field_0003;
   public JSONException field_0006;
   public WorldGenSand field_0002;
   public ModelAdapterRabbit field_0005;
   public EnumParticleTypes field_0000;
   public LogBrokerMonitor this$0;
   public EntityLeashKnot field_0007;
   public S0APacketUseBed field_0004;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.requestOpenURL();
   }

   public LogBrokerMonitor$17(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
