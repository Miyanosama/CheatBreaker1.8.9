package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.VboRenderList;
import net.minecraft.command.server.CommandSaveOn;
import net.optifine.player.CapeUtils;

public class AbstractChannel$AbstractUnsafe$6 extends OneTimeTask {
   public ModelCow __junk5568286130893246320;
   public VboRenderList __junk4802859775993795879;
   public CommandSaveOn __junk5265222706583472681;
   public CapeUtils __junk3561624388963991285;

   @Override
   public void run() {
      AbstractChannel.access$500(this.this$1.this$0).fireChannelUnregistered();
   }

   public AbstractChannel$AbstractUnsafe$6(AbstractChannel$AbstractUnsafe var1) {
      this.this$1 = var1;
      super();
   }
}
