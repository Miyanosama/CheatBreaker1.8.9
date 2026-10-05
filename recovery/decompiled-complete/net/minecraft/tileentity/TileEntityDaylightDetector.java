package net.minecraft.tileentity;

import com.cheatbreaker.client.module.type.ToggleSprintModule;
import io.netty.handler.codec.compression.JdkZlibEncoder;
import io.netty.handler.stream.ChunkedNioFile;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.item.ItemBucket;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.util.ITickable;
import recovered.unidentified.UnidentifiedClass4040;

public class TileEntityDaylightDetector extends TileEntity implements ITickable {
   public ItemBucket field_0002;
   public JdkZlibEncoder field_0005;
   public S3CPacketUpdateScore field_0001;
   public ChunkedNioFile field_0004;
   public ToggleSprintModule field_0000;
   public UnidentifiedClass4040 field_0003;

   @Override
   public void update() {
      if (this.b != null && !this.b.D && this.b.K() % (201612820L & 1897922710L) == (1476465664L & -2728966917841991082L)) {
         this.blockType = this.w();
         if (this.blockType instanceof BlockDaylightDetector) {
            ((BlockDaylightDetector)this.blockType).updatePower(this.b, this.c);
         }
      }
   }
}
