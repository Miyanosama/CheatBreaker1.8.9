package io.netty.util.internal;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.item.ItemHoe$1;

public class PendingWrite$1 extends Recycler<PendingWrite> {
   public ItemHoe$1 __junk4126212274517286163;
   public TextWebSocketFrame __junk207959760895919663;

   public PendingWrite newObject(Recycler$Handle var1) {
      return new PendingWrite(var1, null);
   }
}
