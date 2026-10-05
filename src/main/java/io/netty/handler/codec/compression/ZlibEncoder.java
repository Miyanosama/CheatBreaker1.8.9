package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.world.biome.BiomeColorHelper;
import com.cheatbreaker.client.ui.mainmenu.MainMenuMode$EnumSwitch;

public abstract class ZlibEncoder extends MessageToByteEncoder<ByteBuf> {

   public abstract boolean isClosed();

   public ZlibEncoder() {
      super(false);
   }

   public abstract ChannelFuture close(ChannelPromise var1);

   public abstract ChannelFuture close();
}
