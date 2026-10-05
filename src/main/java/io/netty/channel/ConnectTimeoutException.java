package io.netty.channel;

import java.net.ConnectException;
import javax.vecmath.Point4i;

public class ConnectTimeoutException extends ConnectException {
   public static final long serialVersionUID = 2317065249988317463L;

   public ConnectTimeoutException(String var1) {
      super(var1);
   }

   public ConnectTimeoutException() {
   }
}
