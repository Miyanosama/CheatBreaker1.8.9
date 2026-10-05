package io.netty.handler.stream;

import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;
import net.minecraft.client.stream.MetadataCombat;
import net.minecraft.item.crafting.RecipesBanners$1;
import net.minecraft.scoreboard.Team;
import net.minecraft.world.storage.SaveHandlerMP;
import recovered.unidentified.UnidentifiedClass3697;

public class ChunkedWriteHandler$PendingWrite {
   public SaveHandlerMP __junk4722006246711176006;
   public ChannelPromise promise;
   public long progress;
   public RecipesBanners$1 __junk674901957113933788;
   public UnidentifiedClass3697 __junk3032556196764515820;
   public Object msg;
   public Team __junk8070694685900437022;
   public MetadataCombat __junk1431897445642586936;

   public ChunkedWriteHandler$PendingWrite(Object var1, ChannelPromise var2) {
      this.msg = var1;
      this.promise = var2;
   }

   public void progress(int var1) {
      this.progress += var1;
      if (this.promise instanceof ChannelProgressivePromise) {
         ((ChannelProgressivePromise)this.promise).tryProgress(this.progress, -1L & -1L);
      }
   }

   public void success() {
      if (!this.promise.isDone()) {
         if (this.promise instanceof ChannelProgressivePromise) {
            ((ChannelProgressivePromise)this.promise).tryProgress(this.progress, this.progress);
         }

         this.promise.trySuccess();
      }
   }

   public void fail(Throwable var1) {
      ReferenceCountUtil.release(this.msg);
      this.promise.tryFailure(var1);
   }
}
