package net.minecraft.client.resources;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.SettableFuture;
import java.io.File;
import net.minecraft.network.play.client.C16PacketClientStatus$EnumState;

public class ResourcePackRepository$3 implements FutureCallback<Object> {
   public C16PacketClientStatus$EnumState field_0000;

   public void onSuccess(Object var1) {
      this.this$0.setResourcePackInstance(this.val$file1);
      this.val$settablefuture.set(null);
   }

   public void onFailure(Throwable var1) {
      this.val$settablefuture.setException(var1);
   }

   public ResourcePackRepository$3(ResourcePackRepository var1, File var2, SettableFuture var3) {
      this.this$0 = var1;
      this.val$file1 = var2;
      this.val$settablefuture = var3;
      super();
   }
}
