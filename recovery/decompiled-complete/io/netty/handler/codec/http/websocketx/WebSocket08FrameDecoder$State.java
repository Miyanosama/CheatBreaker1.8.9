package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.PoolThreadCache$NormalMemoryRegionCache;
import io.netty.channel.AbstractChannelHandlerContext$17;
import net.minecraft.item.Item$9;
import net.minecraft.world.storage.WorldInfo$2;
import net.optifine.reflect.FieldLocatorType;

public enum WebSocket08FrameDecoder$State {
   FRAME_START,
   MASKING_KEY,
   PAYLOAD,
   CORRUPT;
   // $VF: synthetic field
   public static WebSocket08FrameDecoder$State[] $VALUES = new WebSocket08FrameDecoder$State[]{
      FRAME_START, WebSocket08FrameDecoder$State.MASKING_KEY, WebSocket08FrameDecoder$State.PAYLOAD, WebSocket08FrameDecoder$State.CORRUPT
   };
   public Item$9 __junk3067384241903948487;
   public PoolThreadCache$NormalMemoryRegionCache __junk8219168344344910926;
   public WorldInfo$2 __junk685754395935574651;
   public FieldLocatorType __junk8341906168292561694;
   public AbstractChannelHandlerContext$17 __junk6710018479916932751;
}
