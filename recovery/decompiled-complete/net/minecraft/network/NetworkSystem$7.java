package net.minecraft.network;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.block.BlockSilverfish$EnumType;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChatComponentText;

public class NetworkSystem$7 implements GenericFutureListener<Future<? super Void>> {
   public BlockSilverfish$EnumType field_0004;
   public TileEntityMobSpawner field_0003;

   public NetworkSystem$7(NetworkSystem var1, NetworkManager var2, ChatComponentText var3) {
      this.field_0002 = var1;
      this.field_0000 = var2;
      this.field_0001 = var3;
      super();
   }

   @Override
   public void operationComplete(Future<? super Void> var1) {
      this.field_0000.closeChannel(this.field_0001);
   }
}
