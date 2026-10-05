package io.netty.channel;

import io.netty.handler.codec.http.HttpContentEncoder$State;
import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.client.model.ModelSlime;

public class AbstractChannelHandlerContext$WriteAndFlushTask$1 extends Recycler<AbstractChannelHandlerContext$WriteAndFlushTask> {
   public HttpContentEncoder$State __junk3125677721223034996;
   public ModelSlime __junk2746261787712189580;

   public AbstractChannelHandlerContext$WriteAndFlushTask newObject(Recycler$Handle var1) {
      return new AbstractChannelHandlerContext$WriteAndFlushTask(var1, null);
   }
}
