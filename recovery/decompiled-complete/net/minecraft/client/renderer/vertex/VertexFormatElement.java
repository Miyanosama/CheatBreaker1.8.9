package net.minecraft.client.renderer.vertex;

import io.netty.channel.socket.oio.DefaultOioServerSocketChannelConfig;
import net.minecraft.entity.passive.EntityRabbit$RabbitJumpHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VertexFormatElement {
   public VertexFormatElement$EnumUsage usage;
   public static Logger LOGGER = LogManager.getLogger();
   public DefaultOioServerSocketChannelConfig field_0002;
   public int elementCount;
   public EntityRabbit$RabbitJumpHelper field_0000;
   public VertexFormatElement$EnumType type;
   public int index;

   public VertexFormatElement$EnumUsage getUsage() {
      return this.usage;
   }

   @Override
   public int hashCode() {
      int var1 = this.type.hashCode();
      var1 = 31 * var1 + this.usage.hashCode();
      var1 = 31 * var1 + this.index;
      return 31 * var1 + this.elementCount;
   }

   public VertexFormatElement$EnumType getType() {
      return this.type;
   }

   public int getIndex() {
      return this.index;
   }

   public VertexFormatElement(int var1, VertexFormatElement$EnumType var2, VertexFormatElement$EnumUsage var3, int var4) {
      if (!this.func_177372_a(var1, var3)) {
         LOGGER.warn("Multiple vertex elements of the same type other than UVs are not supported. Forcing type to UV.");
         this.usage = VertexFormatElement$EnumUsage.UV;
      } else {
         this.usage = var3;
      }

      this.type = var2;
      this.index = var1;
      this.elementCount = var4;
   }

   public int getSize() {
      return this.type.getSize() * this.elementCount;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         VertexFormatElement var2 = (VertexFormatElement)var1;
         return this.elementCount != var2.elementCount
            ? false
            : (this.index != var2.index ? false : (this.type != var2.type ? false : this.usage == var2.usage));
      } else {
         return false;
      }
   }

   public int getElementCount() {
      return this.elementCount;
   }

   public boolean func_177372_a(int var1, VertexFormatElement$EnumUsage var2) {
      return var1 == 0 || var2 == VertexFormatElement$EnumUsage.UV;
   }

   @Override
   public String toString() {
      return this.elementCount + "," + this.usage.getDisplayName() + "," + this.type.getDisplayName();
   }

   public boolean isPositionElement() {
      return this.usage == VertexFormatElement$EnumUsage.POSITION;
   }
}
