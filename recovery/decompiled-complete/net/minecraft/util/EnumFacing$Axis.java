package net.minecraft.util;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.entity.item.EntityMinecart$EnumMinecartType;
import net.minecraft.entity.monster.EntitySlime$AISlimeHop;

public enum EnumFacing$Axis implements IStringSerializable, Predicate<EnumFacing> {
   Z("z", EnumFacing$Plane.HORIZONTAL),
   Y("y", EnumFacing$Plane.VERTICAL),
   X("x", EnumFacing$Plane.HORIZONTAL);
   public EnumFacing$Plane plane;
   public EntitySlime$AISlimeHop field_0003;
   public String name;
   public EntityMinecart$EnumMinecartType field_0000;
   // $VF: synthetic field
   public static EnumFacing$Axis[] $VALUES = new EnumFacing$Axis[]{EnumFacing$Axis.X, EnumFacing$Axis.Y, Z};
   public static Map<String, EnumFacing$Axis> NAME_LOOKUP = Maps.newHashMap();
   public AbstractResourcePack field_0005;

   public boolean isVertical() {
      return this.plane == EnumFacing$Plane.VERTICAL;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public boolean apply(EnumFacing var1) {
      return var1 != null && var1.getAxis() == this;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public boolean isHorizontal() {
      return this.plane == EnumFacing$Plane.HORIZONTAL;
   }

   public static EnumFacing$Axis byName(String var0) {
      return var0 == null ? null : NAME_LOOKUP.get(var0.toLowerCase());
   }

   public String getName2() {
      return this.name;
   }

   static {
      for (EnumFacing$Axis var3 : values()) {
         NAME_LOOKUP.put(var3.getName2().toLowerCase(), var3);
      }
   }

   public EnumFacing$Plane getPlane() {
      return this.plane;
   }

   public EnumFacing$Axis(String var3, EnumFacing$Plane var4) {
      this.name = var3;
      this.plane = var4;
   }
}
