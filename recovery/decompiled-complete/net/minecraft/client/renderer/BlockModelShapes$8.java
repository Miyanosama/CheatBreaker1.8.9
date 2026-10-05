package net.minecraft.client.renderer;

import net.minecraft.block.BlockQuartz$EnumType;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.util.ChatStyle$Serializer;
import org.newsclub.net.unix.AFUNIXSocket;

// $VF: synthetic class
public class BlockModelShapes$8 {
   public RenderMinecart field_0004;
   public ItemInWorldManager field_0001;
   public ChatStyle$Serializer field_0003;
   public AFUNIXSocket field_0000;

   static {
      try {
         field_178257_a[BlockQuartz$EnumType.DEFAULT.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_178257_a[BlockQuartz$EnumType.CHISELED.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_178257_a[BlockQuartz$EnumType.LINES_Y.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_178257_a[BlockQuartz$EnumType.LINES_X.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_178257_a[BlockQuartz$EnumType.LINES_Z.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
