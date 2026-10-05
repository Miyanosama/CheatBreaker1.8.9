package net.minecraft.entity;

import io.netty.channel.ChannelFlushPromiseNotifier;
import io.netty.util.internal.MpscLinkedQueue$1;
import net.minecraft.client.renderer.entity.RenderPotion;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumType;
import org.apache.log4j.jmx.LayoutDynamicMBean;
import recovered.unidentified.UnidentifiedClass4000;

public enum EntityLiving$SpawnPlacementType {
   IN_AIR,
   IN_WATER,
   ON_GROUND;
   public LayoutDynamicMBean field_0007;
   public UnidentifiedClass4000 field_0003;
   public ChannelFlushPromiseNotifier field_0006;
   // $VF: synthetic field
   public static EntityLiving$SpawnPlacementType[] $VALUES = new EntityLiving$SpawnPlacementType[]{
      EntityLiving$SpawnPlacementType.ON_GROUND, IN_AIR, EntityLiving$SpawnPlacementType.IN_WATER
   };
   public VertexFormatElement$EnumType field_0001;
   public RenderPotion field_0008;
   public MpscLinkedQueue$1 field_0002;
}
