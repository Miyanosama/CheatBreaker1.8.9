package com.cheatbreaker.client.util;

import io.netty.buffer.SlicedByteBuf;

public class SessionServer {
   public SessionServer$Status status = SessionServer$Status.UNKNOWN;
   public String url;
   public SlicedByteBuf field_0000;
   public String type;

   public String getType() {
      return this.type;
   }

   public SessionServer$Status getStatus() {
      return this.status;
   }

   public SessionServer(String var1, String var2) {
      this.type = var1;
      this.url = var2;
   }

   public void setStatus(SessionServer$Status var1) {
      this.status = var1;
   }

   public String getUrl() {
      return this.url;
   }
}
