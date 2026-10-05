package org.java_websocket.enums;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachTransformedKeyTask;
import net.minecraft.block.BlockSandStone;
import net.minecraft.client.gui.spectator.BaseSpectatorGroup;
import net.minecraft.world.biome.BiomeGenJungle;
import org.java_websocket.util.ByteBufferUtils;

public enum HandshakeState {
   NOT_MATCHED,
   MATCHED;

   public BlockSandStone field_0003;
   public BiomeGenJungle field_0006;
   // $VF: synthetic field
   public static HandshakeState[] $VALUES = new HandshakeState[]{HandshakeState.MATCHED, NOT_MATCHED};
   public BaseSpectatorGroup field_0000;
   public ByteBufferUtils field_0001;
   public ConcurrentHashMapV8$ForEachTransformedKeyTask field_0004;
}
