package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.ssl.SslHandler$7;
import net.minecraft.client.multiplayer.WorldClient$3;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.network.NetHandlerLoginServer;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Entrance;
import net.optifine.gui.TooltipProviderOptions;
import net.optifine.shaders.uniform.ShaderParameterFloat;
import org.apache.log4j.helpers.RelativeTimeDateFormat;

public class UnidentifiedClass3311 extends WSPacket {
   public ShaderParameterFloat field_0006;
   public TooltipProviderOptions field_0003;
   public SslHandler$7 field_0005;
   public NioSctpServerChannel field_0000;
   public RelativeTimeDateFormat field_0001;
   public WorldClient$3 field_0007;
   public DatagramPacket field_0004;
   public StructureNetherBridgePieces$Entrance field_0002;
   public NetHandlerLoginServer field_0008;

   @Override
   public void write(PacketBuffer var1) {
   }

   @Override
   public void read(PacketBuffer var1) {
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10127(this);
   }
}
