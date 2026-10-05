package org.java_websocket.exceptions;

import net.minecraft.block.BlockBanner$1;

public class NotSendableException extends RuntimeException {
   public static long field_0000;
   public BlockBanner$1 field_0001;

   public NotSendableException(Throwable var1) {
      super(var1);
   }

   public NotSendableException(String var1) {
      super(var1);
   }

   public NotSendableException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
