package net.minecraft.network;

import com.cheatbreaker.client.module.ModuleManager;
import io.netty.channel.DefaultChannelHandlerContext;
import io.netty.handler.codec.marshalling.ThreadLocalMarshallerProvider;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Corridor2;

public class NetworkManager$InboundHandlerTuplePacketListener {
   public ModuleManager field_0003;
   public GenericFutureListener<? extends Future<? super Void>>[] field_0006;
   public GuiOverlayDebug field_0002;
   public StructureNetherBridgePieces$Corridor2 field_0005;
   public ThreadLocalMarshallerProvider field_0000;
   public GuiScreenHorseInventory field_0001;
   public Packet field_0007;
   public DefaultChannelHandlerContext field_0004;

   public NetworkManager$InboundHandlerTuplePacketListener(Packet var1, GenericFutureListener<? extends Future<? super Void>>... var2) {
      this.field_0007 = var1;
      this.field_0006 = var2;
   }
}
