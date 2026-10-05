package org.apache.log4j.lf5.viewer;

import io.netty.channel.sctp.oio.OioSctpServerChannel$2;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$BaseIterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.network.play.server.S42PacketCombatEvent$Event;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$StairsStraight;
import org.apache.log4j.chainsaw.ControlPanel$4;

public class LogBrokerMonitor$24 implements ActionListener {
   public ControlPanel$4 field_0003;
   public LogBrokerMonitor this$0;
   public OioSctpServerChannel$2 field_0002;
   public StructureStrongholdPieces$StairsStraight field_0004;
   public ModelGhast field_0000;
   public S42PacketCombatEvent$Event field_0001;
   public ConcurrentHashMapV8$BaseIterator field_0006;

   public LogBrokerMonitor$24(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0.showPropertiesDialog("LogFactor5 Properties");
   }
}
