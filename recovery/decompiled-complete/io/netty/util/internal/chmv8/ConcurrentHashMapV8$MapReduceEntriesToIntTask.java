package io.netty.util.internal.chmv8;

import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder$State;
import java.util.Map.Entry;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.network.play.client.C07PacketPlayerDigging;

public class ConcurrentHashMapV8$MapReduceEntriesToIntTask<K, V> extends ConcurrentHashMapV8$BulkTask<K, V, Integer> {
   public ConcurrentHashMapV8$IntByIntToInt reducer;
   public ConcurrentHashMapV8$MapReduceEntriesToIntTask<K, V> nextRight;
   public ConcurrentHashMapV8$MapReduceEntriesToIntTask<K, V> rights;
   public WebSocket08FrameDecoder$State __junk3035641372988432292;
   public C07PacketPlayerDigging __junk4263647648077011692;
   public int result;
   public GuiScreenResourcePacks __junk5307776017602251565;
   public int basis;
   public ConcurrentHashMapV8$ObjectToInt<Entry<K, V>> transformer;
   public CompiledChunk __junk4412796490141580677;

   public Integer getRawResult() {
      return this.result;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$ObjectToInt var1 = this.transformer;
      if (this.transformer != null) {
         ConcurrentHashMapV8$IntByIntToInt var2 = this.reducer;
         if (this.reducer != null) {
            int var3 = this.basis;
            int var4 = this.baseIndex;

            while (this.batch > 0) {
               int var5 = this.baseLimit;
               int var6;
               if ((var6 = this.baseLimit + var4 >>> 1) <= var4) {
                  break;
               }

               this.addToPendingCount(1);
               (this.rights = new ConcurrentHashMapV8$MapReduceEntriesToIntTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var6, var5, this.tab, this.rights, var1, var3, var2
                  ))
                  .fork();
            }

            while ((var7 = this.advance()) != null) {
               var3 = var2.apply(var3, var1.apply(var7));
            }

            this.result = var3;

            for (CountedCompleter var8 = this.firstComplete(); var8 != null; var8 = var8.nextComplete()) {
               ConcurrentHashMapV8$MapReduceEntriesToIntTask var9 = (ConcurrentHashMapV8$MapReduceEntriesToIntTask)var8;

               for (ConcurrentHashMapV8$MapReduceEntriesToIntTask var10 = var9.rights; var10 != null; var10 = var9.rights = var10.nextRight) {
                  var9.result = var2.apply(var9.result, var10.result);
               }
            }
         }
      }
   }

   public ConcurrentHashMapV8$MapReduceEntriesToIntTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceEntriesToIntTask<K, V> var6,
      ConcurrentHashMapV8$ObjectToInt<Entry<K, V>> var7,
      int var8,
      ConcurrentHashMapV8$IntByIntToInt var9
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.transformer = var7;
      this.basis = var8;
      this.reducer = var9;
   }
}
