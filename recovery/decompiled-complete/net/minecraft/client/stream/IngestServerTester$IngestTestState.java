package net.minecraft.client.stream;

import com.cheatbreaker.client.util.worldborder.WorldBorder;
import io.netty.buffer.ByteBufProcessor$1;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public enum IngestServerTester$IngestTestState {
   ConnectingToServer,
   Cancelled,
   Finished,
   Cancelling,
   Failed,
   DoneTestingServer,
   Starting,
   TestingServer,
   Uninitalized;
   public S08PacketPlayerPosLook field_0005;
   public ByteBufProcessor$1 field_0010;
   // $VF: synthetic field
   public static IngestServerTester$IngestTestState[] $VALUES = new IngestServerTester$IngestTestState[]{
      IngestServerTester$IngestTestState.Uninitalized, Starting, ConnectingToServer, TestingServer, DoneTestingServer, Finished, Cancelling, Cancelled, Failed
   };
   public WorldBorder field_0006;
}
