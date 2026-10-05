package com.jagrosh.discordipc;

import io.netty.handler.codec.serialization.ClassResolvers;
import net.minecraft.client.particle.EntityCritFX$Factory;
import org.apache.log4j.varia.NullAppender;
import org.java_websocket.framing.FramedataImpl1;

// $VF: synthetic class
public class IPCClient$1 {
   public ClassResolvers field_0002;
   public FramedataImpl1 field_0004;
   public NullAppender field_0003;
   public EntityCritFX$Factory field_0000;

   static {
      try {
         field_0001[IPCClient$Event.field_0000.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_0001[IPCClient$Event.field_0007.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_0001[IPCClient$Event.field_0006.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0001[IPCClient$Event.field_0003.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0001[IPCClient$Event.field_0005.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0001[IPCClient$Event.field_0004.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
