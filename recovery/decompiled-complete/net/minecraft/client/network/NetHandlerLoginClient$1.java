package net.minecraft.client.network;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import javax.crypto.SecretKey;
import net.minecraft.tileentity.TileEntityComparator;
import net.minecraft.util.FoodStats;
import net.minecraft.util.Matrix4f;
import org.apache.log4j.net.SocketHubAppender;
import org.slf4j.helpers.SubstituteLogger;

public class NetHandlerLoginClient$1 implements GenericFutureListener<Future<? super Void>> {
   public FoodStats field_0005;
   public Matrix4f field_0002;
   public SubstituteLogger field_0004;
   public TileEntityComparator field_0000;
   public SocketHubAppender field_0006;

   public NetHandlerLoginClient$1(NetHandlerLoginClient var1, SecretKey var2) {
      this.field_0003 = var1;
      this.field_0001 = var2;
      super();
   }

   @Override
   public void operationComplete(Future<? super Void> var1) {
      NetHandlerLoginClient.access$000(this.field_0003).enableEncryption(this.field_0001);
   }
}
