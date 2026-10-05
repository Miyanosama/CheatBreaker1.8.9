package net.minecraft.client.stream;

import com.cheatbreaker.client.ui.overlay.element.AliasesElement;
import io.netty.channel.AbstractChannelHandlerContext$WriteTask$1;
import net.minecraft.world.gen.ChunkProviderGenerate;
import recovered.unidentified.UnidentifiedClass0696;
import tv.twitch.broadcast.IStatCallbacks;
import tv.twitch.broadcast.RTMPState;
import tv.twitch.broadcast.StatType;

public class IngestServerTester$2 implements IStatCallbacks {
   public ChunkProviderGenerate field_0002;
   public UnidentifiedClass0696 field_0004;
   public AbstractChannelHandlerContext$WriteTask$1 field_0001;
   public AliasesElement field_0003;

   public void statCallback(StatType var1, long var2) {
      switch (IngestServerTester$3.field_176003_a[var1.ordinal()]) {
         case 1:
            this.field_176001_a.field_153051_i = RTMPState.lookupValue((int)var2);
            break;
         case 2:
            this.field_176001_a.field_153050_h = var2;
      }
   }

   public IngestServerTester$2(IngestServerTester var1) {
      this.field_176001_a = var1;
      super();
   }
}
