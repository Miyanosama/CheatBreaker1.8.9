package io.netty.buffer;

import io.netty.util.ResourceLeakDetector$Level;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.server.MinecraftServer$3;
import net.minecraft.util.ChatComponentText;

// $VF: synthetic class
public class AbstractByteBufAllocator$1 {
   public MinecraftServer$3 __junk3307983315214262796;
   public SoundHandler __junk3596583281874317919;
   public ChatComponentText __junk3878171355194696571;

   static {
      try {
         $SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector$Level.SIMPLE.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector$Level.ADVANCED.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$util$ResourceLeakDetector$Level[ResourceLeakDetector$Level.PARANOID.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
