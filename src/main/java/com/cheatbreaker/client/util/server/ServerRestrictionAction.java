package com.cheatbreaker.client.util.server;

public enum ServerRestrictionAction {
      WARN,
      WARN_STATUS,
      BLOCK;
   public static ServerRestrictionAction[] recoveredField1017 = new ServerRestrictionAction[]{
      ServerRestrictionAction.WARN, ServerRestrictionAction.WARN_STATUS, ServerRestrictionAction.BLOCK
   };

   public static ServerRestrictionAction method_06239(String var0) {
      return Enum.valueOf(ServerRestrictionAction.class, var0);
   }
}
