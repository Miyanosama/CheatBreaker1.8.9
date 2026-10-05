package io.netty.util.concurrent;

import io.netty.handler.ssl.OpenSslEngine$1;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs;
import net.optifine.entity.model.ModelAdapterSign;

public class SucceededFuture<V> extends CompleteFuture<V> {
   public V result;
   public ModelAdapterSign __junk5175420139976478953;
   public OpenSslEngine$1 __junk1832044059984838132;
   public MapGenStructureIO __junk7393108030926296122;
   public StructureStrongholdPieces$Stairs __junk4844995829680797099;
   public TexturedQuad __junk7811037895967607665;

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
