package org.java_websocket.exceptions;

import io.netty.buffer.PoolChunkList;
import java.io.UnsupportedEncodingException;

public class InvalidEncodingException extends RuntimeException {
   public PoolChunkList field_0000;
   public UnsupportedEncodingException encodingException;

   public UnsupportedEncodingException getEncodingException() {
      return this.encodingException;
   }

   public InvalidEncodingException(UnsupportedEncodingException var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.encodingException = var1;
      }
   }
}
