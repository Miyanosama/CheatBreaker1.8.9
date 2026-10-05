package io.netty.util.internal.chmv8;

import com.cheatbreaker.client.module.ModuleManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.realms.RealmsMth;

public class ConcurrentHashMapV8$MapReduceMappingsTask<K, V, U> extends ConcurrentHashMapV8$BulkTask<K, V, U> {
   public ConcurrentHashMapV8$MapReduceMappingsTask<K, V, U> nextRight;
   public ModelResourceLocation __junk8102043254859751170;
   public ConcurrentHashMapV8$BiFun<? super K, ? super V, ? extends U> transformer;
   public ConcurrentHashMapV8$BiFun<? super U, ? super U, ? extends U> reducer;
   public RealmsMth __junk4959608477585857274;
   public ConcurrentHashMapV8$MapReduceMappingsTask<K, V, U> rights;
   public U result;
   public ModuleManager __junk4758213581062022025;

   public ConcurrentHashMapV8$MapReduceMappingsTask(
      ConcurrentHashMapV8$BulkTask<K, V, ?> var1,
      int var2,
      int var3,
      int var4,
      ConcurrentHashMapV8$Node<K, V>[] var5,
      ConcurrentHashMapV8$MapReduceMappingsTask<K, V, U> var6,
      ConcurrentHashMapV8$BiFun<? super K, ? super V, ? extends U> var7,
      ConcurrentHashMapV8$BiFun<? super U, ? super U, ? extends U> var8
   ) {
      super(var1, var2, var3, var4, var5);
      this.nextRight = var6;
      this.transformer = var7;
      this.reducer = var8;
   }

   @Override
   public void compute() {
      ConcurrentHashMapV8$BiFun var1 = this.transformer;
      if (this.transformer != null) {
         ConcurrentHashMapV8$BiFun var2 = this.reducer;
         if (this.reducer != null) {
            int var3 = this.baseIndex;

            while (this.batch > 0) {
               int var4 = this.baseLimit;
               int var5;
               if ((var5 = this.baseLimit + var3 >>> 1) <= var3) {
                  break;
               }

               this.addToPendingCount(1);
               (this.rights = new ConcurrentHashMapV8$MapReduceMappingsTask<>(
                     this, this.batch >>>= 1, this.baseLimit = var5, var4, this.tab, this.rights, var1, var2
                  ))
                  .fork();
            }

            Object var9 = null;

            ConcurrentHashMapV8$Node var10;
            while ((var10 = this.advance()) != null) {
               Object var12;
               if ((var12 = var1.apply(var10.key, var10.val)) != null) {
                  var9 = var9 == null ? var12 : var2.apply(var9, var12);
               }
            }

            this.result = (U)var9;

            for (CountedCompleter var11 = this.firstComplete(); var11 != null; var11 = var11.nextComplete()) {
               ConcurrentHashMapV8$MapReduceMappingsTask var13 = (ConcurrentHashMapV8$MapReduceMappingsTask)var11;

               for (ConcurrentHashMapV8$MapReduceMappingsTask var6 = var13.rights; var6 != null; var6 = var13.rights = var6.nextRight) {
                  Object var8 = var6.result;
                  if (var6.result != null) {
                     Object var7 = var13.result;
                     var13.result = (U)(var13.result == null ? var8 : var2.apply(var7, var8));
                  }
               }
            }
         }
      }
   }

   @Override
   public U getRawResult() {
      return this.result;
   }
}
