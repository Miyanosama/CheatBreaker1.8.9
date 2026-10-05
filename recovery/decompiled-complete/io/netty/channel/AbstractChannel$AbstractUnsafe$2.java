package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import net.minecraft.client.gui.GuiLanguage;
import org.slf4j.helpers.NamedLoggerBase;
import recovered.unidentified.UnidentifiedClass0798;

public class AbstractChannel$AbstractUnsafe$2 extends OneTimeTask {
   public UnidentifiedClass0798 __junk9036787983096831527;
   public GuiLanguage __junk7505200611277140423;
   public NamedLoggerBase __junk1819199837926463100;

   @Override
   public void run() {
      AbstractChannel.access$500(this.this$1.this$0).fireChannelActive();
   }

   public AbstractChannel$AbstractUnsafe$2(AbstractChannel$AbstractUnsafe var1) {
      this.this$1 = var1;
      super();
   }
}
