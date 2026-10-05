package io.netty.util.concurrent;

import net.minecraft.client.model.TexturedQuad;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.optifine.entity.model.ModelAdapterSign;

public class SucceededFuture<V> extends CompleteFuture<V> {
   public V result;

   @Override
   public V getNow() {
      return this.result;
   }

   @Override
   public boolean isSuccess() {
      return true;
   }

   @Override
   public Throwable cause() {
      return null;
   }

   public SucceededFuture(EventExecutor var1, V var2) {
      super(var1);
      this.result = (V)var2;
   }
}
