package io.netty.util.internal.chmv8;

import com.cheatbreaker.client.ui.module.CBModulePosition;
import io.netty.handler.codec.compression.Snappy$State;
import javazoom.jl.decoder.LayerIDecoder$Subband;
import net.minecraft.client.particle.EntityCloudFX$Factory;
import net.minecraft.nbt.NBTTagInt;
import net.optifine.Log;

public class ConcurrentHashMapV8$ForEachTransformedValueTask<K, V, U> extends ConcurrentHashMapV8$BulkTask<K, V, Void> {
   public LayerIDecoder$Subband __junk401886716876168945;
   public ConcurrentHashMapV8$Action<? super U> action;
   public ConcurrentHashMapV8$Fun<? super V, ? extends U> transformer;
   public Snappy$State __junk3694165605155721383;
   public EntityCloudFX$Factory __junk2409271784581529160;
   public NBTTagInt __junk2199071897214828858;
   public Log __junk1063685922179675306;
   public CBModulePosition __junk8598672969312353556;

   @Override
   public void compute() {
      ConcurrentHashMapV8$Fun var1 = this.transformer;
      if (this.transformer != null) {
         ConcurrentHashMapV8$Action var2 = this.action;
         if (this.action != null) {
            int var3 = this.baseIndex;

            while (this.batch > 0) {
               int var4 = this.baseLimit;
               int var5;
               if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                  break;
               }

               this.addToPendingCount(1);
               new ConcurrentHashMapV8$ForEachTransformedValueTask(this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, var1, var2).fork();
            }

            while ((var6 = this.advance()) != null) {
               Object var7;
               if ((var7 = var1.apply(var6.val)) != null) {
                  var2.apply(var7);
               }
            }

            this.propagateCompletion();
         }
      }
   }

   public ConcurrentHashMapV8$ForEachTransformedValueTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$Fun<? super V, ? extends U> var6,
      ConcurrentHashMapV8$Action<? super U> var7
   ) {
      super(var1, var2, var3, var4, var5);
      this.transformer = var6;
      this.action = var7;
   }
}
