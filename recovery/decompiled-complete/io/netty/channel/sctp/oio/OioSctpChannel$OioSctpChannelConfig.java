package io.netty.channel.sctp.oio;

import com.cheatbreaker.client.ui.module.CBPositionEnum;
import com.sun.nio.sctp.SctpChannel;
import io.netty.channel.sctp.DefaultSctpChannelConfig;
import junit.swingui.TestHierarchyRunView;
import net.minecraft.init.Bootstrap$11;

public class OioSctpChannel$OioSctpChannelConfig extends DefaultSctpChannelConfig {
   public Bootstrap$11 __junk5898632913529992138;
   public CBPositionEnum __junk3330353194628762443;
   public TestHierarchyRunView __junk2087161512425930105;

   @Override
   public void autoReadCleared() {
      OioSctpChannel.access$100(this.this$0, false);
   }

   public OioSctpChannel$OioSctpChannelConfig(OioSctpChannel var1, OioSctpChannel var2, SctpChannel var3) {
      this.this$0 = var1;
      super(var2, var3);
   }
}
