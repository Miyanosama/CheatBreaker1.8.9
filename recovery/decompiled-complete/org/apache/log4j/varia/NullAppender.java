package org.apache.log4j.varia;

import net.minecraft.block.BlockWall$EnumType;
import net.minecraft.client.particle.EntityFishWakeFX$Factory;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.village.VillageSiege;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import recovered.unidentified.UnidentifiedClass1318;

public class NullAppender extends AppenderSkeleton {
   public static NullAppender instance = new NullAppender();
   public Framebuffer field_0000;
   public EntityFishWakeFX$Factory field_0002;
   public BlockWall$EnumType field_0003;
   public UnidentifiedClass1318 field_0001;
   public VillageSiege field_0004;

   public void activateOptions() {
   }

   public void append(LoggingEvent var1) {
   }

   public boolean requiresLayout() {
      return false;
   }

   public void close() {
   }

   public NullAppender method_08782() {
      return instance;
   }

   public static NullAppender method_08784() {
      return instance;
   }

   public void doAppend(LoggingEvent var1) {
   }
}
