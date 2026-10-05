package io.netty.channel.local;

import net.minecraft.client.gui.achievement.GuiStats$StatsMobsList;
import net.minecraft.potion.PotionEffect;

public class LocalChannel$4 implements Runnable {
   public PotionEffect __junk945537486065876636;
   public GuiStats$StatsMobsList __junk4737850614458477886;

   @Override
   public void run() {
      this.val$peer.unsafe().close(this.this$0.unsafe().voidPromise());
   }

   public LocalChannel$4(LocalChannel var1, LocalChannel var2) {
      this.this$0 = var1;
      this.val$peer = var2;
      super();
   }
}
