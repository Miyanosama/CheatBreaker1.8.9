package io.netty.util.internal.logging;

import io.netty.handler.codec.http.DefaultHttpHeaders$HeaderIterator;
import java.io.OutputStream;

public class Slf4JLoggerFactory$1 extends OutputStream {
   public DefaultHttpHeaders$HeaderIterator __junk3463310784442401427;

   @Override
   public void write(int var1) {
      this.val$buf.append((char)var1);
   }

   public Slf4JLoggerFactory$1(Slf4JLoggerFactory var1, StringBuffer var2) {
      this.this$0 = var1;
      this.val$buf = var2;
      super();
   }
}
