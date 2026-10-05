package com.cheatbreaker.client.util;

public class SessionServer {
   public SessionServer.Status status = SessionServer.Status.UNKNOWN;
   public String url;
   public String type;

   public String getType() {
      return this.type;
   }

   public SessionServer.Status getStatus() {
      return this.status;
   }

   public SessionServer(String var1, String var2) {
      this.type = var1;
      this.url = var2;
   }

   public void setStatus(SessionServer.Status var1) {
      this.status = var1;
   }

   public String getUrl() {
      return this.url;
   }

   public static enum Status {
      UP("green"),
      DOWN("red"),
      BUSY("yellow"),
      UNKNOWN("unknown");
      // $VF: synthetic field
      public static SessionServer.Status[] recoveredField106 = new SessionServer.Status[]{
         SessionServer.Status.UP, SessionServer.Status.DOWN, SessionServer.Status.BUSY, SessionServer.Status.UNKNOWN
      };
      public String recoveredField108;

      public String method_21555() {
         return this.recoveredField108;
      }

      public static SessionServer.Status getStatusByName(String var0) {
         for (SessionServer.Status var4 : values()) {
            if (var4.method_21555().equalsIgnoreCase(var0)) {
               return var4;
            }
         }

         return null;
      }

      Status(String var3) {
         this.recoveredField108 = var3;
      }
   }
}
