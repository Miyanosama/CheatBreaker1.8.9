package net.minecraft.network;

import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.src.Config;
import net.minecraft.util.IThreadListener;

public class PacketThreadUtil {
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

   public static <T extends INetHandler> void checkThreadAndEnqueue(final Packet<T> var0, final T var1, IThreadListener var2) throws net.minecraft.network.ThreadQuickExitException {
      if (!var2.isCallingFromMinecraftThread()) {
         var2.addScheduledTask(new Runnable() {
            @Override
            public void run() {
               PacketThreadUtil.clientPreProcessPacket(var0);
               var0.processPacket(var1);
            }
         });
         throw ThreadQuickExitException.INSTANCE;
      } else {
         clientPreProcessPacket(var0);
      }
   }
}
