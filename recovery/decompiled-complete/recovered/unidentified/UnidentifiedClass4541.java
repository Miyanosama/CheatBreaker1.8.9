package recovered.unidentified;

import io.netty.handler.codec.http.multipart.MemoryFileUpload;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.item.ItemMinecart$1;
import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class UnidentifiedClass4541 {
   public ItemMinecart$1 field_0001;
   public EntityBubbleFX field_0000;
   public MemoryFileUpload field_0002;

   static {
      try {
         field_0003[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0003[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0003[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0003[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
