package net.minecraft.block;

import com.cheatbreaker.client.module.type.PerspectiveModule;
import junit.extensions.ExceptionTestCase;
import net.minecraft.util.EntitySelectors$4;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.feature.WorldGenSpikes;
import org.apache.log4j.net.SyslogAppender;

// $VF: synthetic class
public class BlockPistonBase$1 {
   public PerspectiveModule field_0003;
   public WorldGenSpikes field_0005;
   public SyslogAppender field_0004;
   public EntitySelectors$4 field_0000;
   public ExceptionTestCase field_0001;

   static {
      try {
         field_177243_a[EnumFacing.DOWN.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_177243_a[EnumFacing.UP.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_177243_a[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_177243_a[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_177243_a[EnumFacing.WEST.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_177243_a[EnumFacing.EAST.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
