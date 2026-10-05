package net.minecraft.block;

import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler$ClientHandshakeStateEvent;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.optifine.entity.model.ModelAdapterEnderChest;
import recovered.unidentified.UnidentifiedClass0798;

// $VF: synthetic class
public class BlockBanner$1 {
   public WebSocketClientProtocolHandler$ClientHandshakeStateEvent field_0003;
   public ModelAdapterEnderChest field_0005;
   public TileEntityMobSpawner field_0002;
   public UnidentifiedClass0798 field_0004;
   public WorldGenMegaPineTree field_0000;

   static {
      try {
         field_180370_a[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_180370_a[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_180370_a[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_180370_a[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
