package com.jagrosh.discordipc;

import io.netty.buffer.PooledByteBuf;
import io.netty.buffer.PooledUnsafeDirectByteBuf;
import io.netty.handler.ssl.SslHandshakeCompletionEvent;

public enum IPCClient$Event {
   field_0005(true),
   field_0004(false),
   field_0007(false),
   field_0003(true),
   field_0011(false),
   field_0000(false),
   field_0006(true);
   public PooledByteBuf field_0009;
   public SslHandshakeCompletionEvent field_0008;
   public PooledUnsafeDirectByteBuf field_0001;
   public boolean field_0002;
   // $VF: synthetic field
   public static IPCClient$Event[] field_0010 = new IPCClient$Event[]{
      IPCClient$Event.field_0000,
      IPCClient$Event.field_0011,
      IPCClient$Event.field_0007,
      IPCClient$Event.field_0006,
      IPCClient$Event.field_0003,
      field_0005,
      field_0004
   };

   public boolean method_13329() {
      return this.field_0002;
   }

   public static IPCClient$Event method_13330(String var0) {
      if (var0 == null) {
         return field_0000;
      } else {
         for (IPCClient$Event var4 : values()) {
            if (var4 != field_0004 && var4.name().equalsIgnoreCase(var0)) {
               return var4;
            }
         }

         return field_0004;
      }
   }

   public IPCClient$Event(boolean var3) {
      this.field_0002 = var3;
   }

   public static IPCClient$Event method_13331(String var0) {
      return Enum.valueOf(IPCClient$Event.class, var0);
   }
}
