package net.minecraft.block;

import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandshakeHandler;
import net.minecraft.item.ItemArmor$ArmorMaterial;
import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class BlockWallSign$1 {
   public ItemArmor$ArmorMaterial field_0002;
   public WebSocketClientProtocolHandshakeHandler field_0000;

   static {
      try {
         field_177331_a[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_177331_a[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_177331_a[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_177331_a[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
