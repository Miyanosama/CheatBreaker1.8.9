package org.java_websocket.exceptions;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$CounterHashCode;
import java.io.IOException;
import net.minecraft.block.BlockOldLog;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.optifine.CustomSky;
import org.java_websocket.WebSocket;

public class WrappedIOException extends Exception {
   public IOException ioException;
   public ConcurrentHashMapV8$CounterHashCode field_0005;
   public WebSocket connection;
   public CustomSky field_0004;
   public BlockOldLog field_0000;
   public DefaultPlayerSkin field_0001;

   public WebSocket getConnection() {
      return this.connection;
   }

   public WrappedIOException(WebSocket var1, IOException var2) {
      this.connection = var1;
      this.ioException = var2;
   }

   public IOException getIOException() {
      return this.ioException;
   }
}
