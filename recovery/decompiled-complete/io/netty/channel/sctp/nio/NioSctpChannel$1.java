package io.netty.channel.sctp.nio;

import io.netty.channel.ChannelPromise;
import java.net.InetAddress;
import net.minecraft.client.stream.NullStream;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.StringTranslate;
import recovered.unidentified.UnidentifiedClass5030;

public class NioSctpChannel$1 implements Runnable {
   public EntityBlaze __junk5607890180333271888;
   public UnidentifiedClass5030 __junk827911452207729692;
   public NullStream __junk7119913849995654079;
   public StringTranslate __junk2096365434611464934;

   @Override
   public void run() {
      this.this$0.bindAddress(this.val$localAddress, this.val$promise);
   }

   public NioSctpChannel$1(NioSctpChannel var1, InetAddress var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$localAddress = var2;
      this.val$promise = var3;
      super();
   }
}
