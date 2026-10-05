package io.netty.channel.oio;

import io.netty.buffer.ByteBufProcessor$1;
import java.io.OutputStream;
import java.nio.channels.ClosedChannelException;
import net.minecraft.entity.item.EntityMinecart$1;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import org.apache.log4j.chainsaw.ControlPanel$6;
import recovered.unidentified.UnidentifiedClass1798;

public class OioByteStreamChannel$2 extends OutputStream {
   public ByteBufProcessor$1 __junk2673911897192624064;
   public ControlPanel$6 __junk5407807847985489025;
   public UnidentifiedClass1798 __junk7119664184672549386;
   public EntityMinecart$1 __junk3156692846764645815;
   public S33PacketUpdateSign __junk6639534653181479191;

   @Override
   public void write(int var1) {
      throw new ClosedChannelException();
   }
}
