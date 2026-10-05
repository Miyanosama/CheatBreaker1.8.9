package net.minecraft.client.renderer;

import com.cheatbreaker.client.module.staff.StaffModule;
import com.cheatbreaker.client.ui.element.type.ColorPickerElement;
import io.netty.channel.epoll.Epoll;
import io.netty.util.ThreadDeathWatcher$1;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumUsage;
import net.minecraft.entity.ai.EntityAITempt;

// $VF: synthetic class
public class WorldVertexBufferUploader$1 {
   public ThreadDeathWatcher$1 field_0003;
   public StaffModule field_0002;
   public EntityAITempt field_0004;
   public Epoll field_0000;
   public ColorPickerElement field_0001;

   static {
      try {
         field_0005[VertexFormatElement$EnumUsage.POSITION.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0005[VertexFormatElement$EnumUsage.UV.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0005[VertexFormatElement$EnumUsage.COLOR.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0005[VertexFormatElement$EnumUsage.NORMAL.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
