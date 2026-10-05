package io.netty.handler.codec.http.websocketx;

import io.netty.util.concurrent.MultithreadEventExecutorGroup$GenericEventExecutorChooser;
import net.minecraft.entity.EntityLiving$SpawnPlacementType;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S34PacketMaps;

// $VF: synthetic class
public class WebSocket08FrameDecoder$1 {
   public MultithreadEventExecutorGroup$GenericEventExecutorChooser __junk5011359123473889802;
   public EntityLiving$SpawnPlacementType __junk6325007981983176837;
   public S34PacketMaps __junk3323266182612399904;
   public S11PacketSpawnExperienceOrb __junk406641136638028376;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State[WebSocket08FrameDecoder$State.FRAME_START.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State[WebSocket08FrameDecoder$State.MASKING_KEY.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State[WebSocket08FrameDecoder$State.PAYLOAD.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$websocketx$WebSocket08FrameDecoder$State[WebSocket08FrameDecoder$State.CORRUPT.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
