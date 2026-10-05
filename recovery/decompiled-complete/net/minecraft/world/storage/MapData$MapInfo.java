package net.minecraft.world.storage;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceMappingsTask;
import net.minecraft.block.BlockPrismarine$EnumType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S34PacketMaps;
import org.slf4j.helpers.NOPMDCAdapter;

public class MapData$MapInfo {
   public int minY;
   public int maxX;
   public int field_0004;
   public ConcurrentHashMapV8$MapReduceMappingsTask field_0008;
   public int minX;
   public BlockPrismarine$EnumType field_0002;
   public boolean field_176105_d;
   public NOPMDCAdapter field_0003;
   public EntityPlayer entityplayerObj;
   public int maxY;
   public int field_0006;

   public Packet getPacket(ItemStack var1) {
      if (this.field_176105_d) {
         this.field_176105_d = false;
         return new S34PacketMaps(
            var1.getMetadata(),
            this.field_176107_c.scale,
            this.field_176107_c.mapDecorations.values(),
            this.field_176107_c.colors,
            this.minX,
            this.minY,
            this.maxX + 1 - this.minX,
            this.maxY + 1 - this.minY
         );
      } else {
         return this.field_0006++ % 5 == 0
            ? new S34PacketMaps(
               var1.getMetadata(), this.field_176107_c.scale, this.field_176107_c.mapDecorations.values(), this.field_176107_c.colors, 0, 0, 0, 0
            )
            : null;
      }
   }

   public MapData$MapInfo(MapData var1, EntityPlayer var2) {
      this.field_176107_c = var1;
      super();
      this.field_176105_d = true;
      this.minX = 0;
      this.minY = 0;
      this.maxX = 127;
      this.maxY = 127;
      this.entityplayerObj = var2;
   }

   public void update(int var1, int var2) {
      if (this.field_176105_d) {
         this.minX = Math.min(this.minX, var1);
         this.minY = Math.min(this.minY, var2);
         this.maxX = Math.max(this.maxX, var1);
         this.maxY = Math.max(this.maxY, var2);
      } else {
         this.field_176105_d = true;
         this.minX = var1;
         this.minY = var2;
         this.maxX = var1;
         this.maxY = var2;
      }
   }
}
