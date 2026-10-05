package org.slf4j.helpers;

import io.netty.channel.epoll.EpollEventLoopGroup;
import net.minecraft.util.Vec4b;
import org.java_websocket.util.Charsetfunctions;

public class FormattingTuple {
   public Charsetfunctions field_0003;
   public Object[] argArray;
   public EpollEventLoopGroup field_0002;
   public Vec4b field_0004;
   public String message;
   public static FormattingTuple NULL = new FormattingTuple(null);
   public Throwable throwable;

   public String getMessage() {
      return this.message;
   }

   public FormattingTuple(String var1) {
      this(var1, null, null);
   }

   public Throwable getThrowable() {
      return this.throwable;
   }

   public Object[] getArgArray() {
      return this.argArray;
   }

   public FormattingTuple(String var1, Object[] var2, Throwable var3) {
      this.message = var1;
      this.throwable = var3;
      this.argArray = var2;
   }
}
