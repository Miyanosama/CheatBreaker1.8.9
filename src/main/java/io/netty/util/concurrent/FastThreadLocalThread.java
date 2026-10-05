package io.netty.util.concurrent;

import io.netty.channel.udt.nio.NioUdtMessageConnectorChannel;
import io.netty.util.internal.InternalThreadLocalMap;
import javazoom.jl.decoder.SynthesisFilter;
import net.minecraft.realms.RealmsButton;

public class FastThreadLocalThread extends Thread {
   public InternalThreadLocalMap threadLocalMap;

   public InternalThreadLocalMap threadLocalMap() {
      return this.threadLocalMap;
   }

   public FastThreadLocalThread(ThreadGroup var1, Runnable var2, String var3, long var4) {
      super(var1, var2, var3, var4);
   }

   public FastThreadLocalThread(ThreadGroup var1, String var2) {
      super(var1, var2);
   }

   public FastThreadLocalThread(String var1) {
      super(var1);
   }

   public FastThreadLocalThread() {
   }

   public FastThreadLocalThread(Runnable var1) {
      super(var1);
   }

   public FastThreadLocalThread(Runnable var1, String var2) {
      super(var1, var2);
   }

   public FastThreadLocalThread(ThreadGroup var1, Runnable var2) {
      super(var1, var2);
   }

   public FastThreadLocalThread(ThreadGroup var1, Runnable var2, String var3) {
      super(var1, var2, var3);
   }

   public void setThreadLocalMap(InternalThreadLocalMap var1) {
      this.threadLocalMap = var1;
   }
}
