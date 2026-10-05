package org.java_websocket.exceptions;

import java.io.IOException;
import org.java_websocket.WebSocket;

public class WrappedIOException extends Exception {
   public IOException ioException;
   public WebSocket connection;

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
