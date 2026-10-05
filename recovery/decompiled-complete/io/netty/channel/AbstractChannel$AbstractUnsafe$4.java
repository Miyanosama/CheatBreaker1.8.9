package io.netty.channel;

import io.netty.buffer.ReadOnlyByteBufferBuf;
import io.netty.handler.codec.spdy.DefaultSpdyHeaders$HeaderIterator;
import io.netty.util.internal.OneTimeTask;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$EntrySetView;
import net.minecraft.item.crafting.RecipesDyes;
import net.minecraft.network.play.server.S42PacketCombatEvent$1;
import org.java_websocket.server.WebSocketServer;
import org.newsclub.net.unix.AFUNIXSocketImpl;

public class AbstractChannel$AbstractUnsafe$4 extends OneTimeTask {
   public WebSocketServer __junk8687478809282203135;
   public RecipesDyes __junk8754336040061660525;
   public ConcurrentHashMapV8$EntrySetView __junk5484166996549968920;
   public ReadOnlyByteBufferBuf __junk7551571267137010980;
   public AFUNIXSocketImpl __junk3640307374414619255;
   public S42PacketCombatEvent$1 __junk4940191878297250207;
   public DefaultSpdyHeaders$HeaderIterator __junk5580792901722922625;

   public AbstractChannel$AbstractUnsafe$4(AbstractChannel$AbstractUnsafe var1, ChannelPromise var2) {
      this.this$1 = var1;
      this.val$promise = var2;
      super();
   }

   @Override
   public void run() {
      this.this$1.close(this.val$promise);
   }
}
