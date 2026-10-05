package io.netty.channel.sctp.oio;

import com.sun.nio.sctp.SctpServerChannel;
import io.netty.channel.sctp.DefaultSctpServerChannelConfig;
import net.optifine.entity.model.anim.ModelUpdater;
import org.apache.log4j.jmx.LayoutDynamicMBean;

public class OioSctpServerChannel$OioSctpServerChannelConfig extends DefaultSctpServerChannelConfig {
   public ModelUpdater __junk8046943807973342996;
   public LayoutDynamicMBean __junk8148692440617319942;

   public OioSctpServerChannel$OioSctpServerChannelConfig(OioSctpServerChannel var1, OioSctpServerChannel var2, SctpServerChannel var3) {
      this.this$0 = var1;
      super(var2, var3);
   }

   @Override
   public void autoReadCleared() {
      OioSctpServerChannel.access$100(this.this$0, false);
   }
}
