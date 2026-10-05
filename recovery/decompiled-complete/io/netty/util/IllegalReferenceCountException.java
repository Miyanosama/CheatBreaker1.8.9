package io.netty.util;

import io.netty.bootstrap.AbstractBootstrap$2;
import io.netty.channel.ChannelDuplexHandler;
import net.minecraft.block.BlockRail;
import org.apache.log4j.pattern.FormattingInfo;

public class IllegalReferenceCountException extends IllegalStateException {
   public BlockRail __junk459006364825793563;
   public FormattingInfo __junk154579750048805503;
   public static long serialVersionUID;
   public AbstractBootstrap$2 __junk3092435815605439666;
   public ChannelDuplexHandler __junk544744089278371399;

   public IllegalReferenceCountException() {
   }

   public IllegalReferenceCountException(int var1) {
      this("refCnt: " + var1);
   }

   public IllegalReferenceCountException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public IllegalReferenceCountException(int var1, int var2) {
      this("refCnt: " + var1 + ", " + (var2 > 0 ? "increment: " + var2 : "decrement: " + -var2));
   }

   public IllegalReferenceCountException(String var1) {
      super(var1);
   }

   public IllegalReferenceCountException(Throwable var1) {
      super(var1);
   }
}
