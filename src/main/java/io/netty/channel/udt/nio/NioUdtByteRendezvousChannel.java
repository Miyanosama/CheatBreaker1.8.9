package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.cheatbreaker.client.ui.element.profile.ProfilesListElement;
import io.netty.channel.group.ChannelMatchers;
import net.minecraft.command.PlayerNotFoundException;

public class NioUdtByteRendezvousChannel extends NioUdtByteConnectorChannel {

   public NioUdtByteRendezvousChannel() {
      super(NioUdtProvider.newRendezvousChannelUDT(TypeUDT.STREAM));
   }
}
