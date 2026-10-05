package io.netty.channel.sctp.nio;

import io.netty.channel.ChannelPromise;
import io.netty.channel.PendingWriteQueue$PendingWrite$1;
import java.net.InetAddress;
import net.minecraft.client.Minecraft$1;
import net.minecraft.enchantment.EnchantmentHelper$ModifierDamage;
import net.minecraft.tileentity.TileEntity$2;
import recovered.unidentified.UnidentifiedClass4866;

public class NioSctpServerChannel$2 implements Runnable {
   public UnidentifiedClass4866 __junk3496312215013630396;
   public TileEntity$2 __junk4878871550543572284;
   public PendingWriteQueue$PendingWrite$1 __junk716528973899953536;
   public Minecraft$1 __junk5128055278746490628;
   public EnchantmentHelper$ModifierDamage __junk4613803546650114256;

   @Override
   public void run() {
      this.this$0.unbindAddress(this.val$localAddress, this.val$promise);
   }

   public NioSctpServerChannel$2(NioSctpServerChannel var1, InetAddress var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$localAddress = var2;
      this.val$promise = var3;
      super();
   }
}
