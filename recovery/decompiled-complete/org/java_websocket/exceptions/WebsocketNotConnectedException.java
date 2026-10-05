package org.java_websocket.exceptions;

import com.cheatbreaker.client.module.type.ContainerBlurModule;
import io.netty.util.internal.UnsafeAtomicIntegerFieldUpdater;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class WebsocketNotConnectedException extends RuntimeException {
   public UnsafeAtomicIntegerFieldUpdater field_0002;
   public ContainerBlurModule field_0004;
   public S35PacketUpdateTileEntity field_0001;
   public BlockLeavesBase field_0003;
   public static long field_0000;
}
