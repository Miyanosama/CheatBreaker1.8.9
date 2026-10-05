package io.netty.buffer;

import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.client.renderer.entity.layers.LayerEnderDragonEyes;
import net.minecraft.command.CommandTitle;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import org.apache.log4j.AsyncAppender;

public class ByteBufUtil$ThreadLocalDirectByteBuf$1 extends Recycler<ByteBufUtil$ThreadLocalDirectByteBuf> {
   public LayerEnderDragonEyes __junk962930612385407058;
   public CommandTitle __junk7583582990252922438;
   public AsyncAppender __junk1184516734071668751;
   public SimpleLeakAwareByteBuf __junk1589243808415002829;
   public S25PacketBlockBreakAnim __junk9093497286173107133;

   public ByteBufUtil$ThreadLocalDirectByteBuf newObject(Recycler$Handle var1) {
      return new ByteBufUtil$ThreadLocalDirectByteBuf(var1, null);
   }
}
