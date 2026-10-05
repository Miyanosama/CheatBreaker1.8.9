package net.minecraft.util;

import com.cheatbreaker.client.ui.module.CBModulePosition;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.renderer.block.model.BreakingFour;
import net.minecraft.network.EnumConnectionState$2;
import net.minecraft.network.play.server.S44PacketWorldBorder$Action;

public enum Session$Type {
   MOJANG("mojang"),
   LEGACY("legacy");
   // $VF: synthetic field
   public static Session$Type[] $VALUES = new Session$Type[]{Session$Type.LEGACY, Session$Type.MOJANG};
   public BreakingFour field_0007;
   public String sessionType;
   public CBModulePosition field_0000;
   public EnumConnectionState$2 field_0001;
   public S44PacketWorldBorder$Action field_0008;
   public static Map<String, Session$Type> SESSION_TYPES = Maps.newHashMap();

   public Session$Type(String var3) {
      this.sessionType = var3;
   }

   public static Session$Type setSessionType(String var0) {
      return SESSION_TYPES.get(var0.toLowerCase());
   }

   static {
      for (Session$Type var3 : values()) {
         SESSION_TYPES.put(var3.sessionType, var3);
      }
   }
}
