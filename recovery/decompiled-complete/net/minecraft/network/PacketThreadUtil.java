package net.minecraft.network;

import net.minecraft.block.BlockSoulSand;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.src.Config;
import net.minecraft.util.IThreadListener;
import net.optifine.http.HttpRequest;
import net.optifine.util.FrameEvent;

public class PacketThreadUtil {
   public HttpRequest field_0001;
   public FrameEvent field_0003;
   public BlockSoulSand field_0000;
   public static int lastDimensionId = Integer.MIN_VALUE;

   public static void clientPreProcessPacket(Packet var0) {
      if (var0 instanceof S08PacketPlayerPosLook) {
         Config.getRenderGlobal().onPlayerPositionSet();
      }

      if (var0 instanceof S07PacketRespawn) {
         S07PacketRespawn var1 = (S07PacketRespawn)var0;
         lastDimensionId = var1.getDimensionID();
      } else if (var0 instanceof S01PacketJoinGame) {
         S01PacketJoinGame var2 = (S01PacketJoinGame)var0;
         lastDimensionId = var2.getDimension();
      } else {
         lastDimensionId = Integer.MIN_VALUE;
      }
   }

   public static <T extends INetHandler> void checkThreadAndEnqueue(Packet<T> var0, T var1, IThreadListener var2) {
      if (!var2.isCallingFromMinecraftThread()) {
         var2.addScheduledTask(new PacketThreadUtil$1(var0, var1));
         throw ThreadQuickExitException.INSTANCE;
      } else {
         clientPreProcessPacket(var0);
      }
   }
}
