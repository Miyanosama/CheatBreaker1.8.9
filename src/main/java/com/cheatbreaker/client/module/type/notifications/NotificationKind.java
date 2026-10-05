package com.cheatbreaker.client.module.type.notifications;

public enum NotificationKind {
      INFO,
      ERROR,
      NEUTRAL;
   public static NotificationKind[] recoveredField3594 = new NotificationKind[]{
      NotificationKind.INFO, ERROR, NEUTRAL
   };

   public static NotificationKind method_24002(String var0) {
      return Enum.valueOf(NotificationKind.class, var0);
   }
}
