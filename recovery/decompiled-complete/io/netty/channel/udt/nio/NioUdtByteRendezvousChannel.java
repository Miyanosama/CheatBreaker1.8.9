package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.cheatbreaker.client.ui.element.profile.ProfilesListElement;
import io.netty.channel.group.ChannelMatchers$ClassMatcher;
import io.netty.handler.codec.http.HttpObjectAggregator$1;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.Entity$3;

public class NioUdtByteRendezvousChannel extends NioUdtByteConnectorChannel {
   public ProfilesListElement __junk8169470900591304786;
   public ChannelMatchers$ClassMatcher __junk385220043929514943;
   public PlayerNotFoundException __junk2992533646668453127;
   public Entity$3 __junk595520011804026813;
   public HttpObjectAggregator$1 __junk6621215044743128831;
   public NioUdtByteRendezvousChannel __junk6842762665095240275;

   public NioUdtByteRendezvousChannel() {
      super(NioUdtProvider.newRendezvousChannelUDT(TypeUDT.STREAM));
   }
}
