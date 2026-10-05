package net.optifine.player;

import io.netty.channel.socket.nio.NioDatagramChannelConfig;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker00;
import io.netty.handler.timeout.IdleStateHandler$WriterIdleTimeoutTask;
import net.minecraft.block.BlockSlab$EnumBlockHalf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.world.gen.structure.StructureVillagePieces$Field2;
import net.minecraft.world.storage.SaveFormatComparator;

public class ModelPlayerItem extends ModelBase {
   public SaveFormatComparator field_0003;
   public BlockSlab$EnumBlockHalf field_0005;
   public StructureVillagePieces$Field2 field_0002;
   public IdleStateHandler$WriterIdleTimeoutTask field_0004;
   public WebSocketServerHandshaker00 field_0000;
   public NioDatagramChannelConfig field_0001;

   public ModelPlayerItem() {
      this.r = false;
   }
}
