package net.minecraft.block;

import com.cheatbreaker.client.util.worldborder.WorldBorderManager;
import io.netty.channel.ThreadPerChannelEventLoop$1;
import io.netty.channel.socket.nio.ProtocolFamilyConverter;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing$Axis;
import net.optifine.model.ModelSprite;
import org.java_websocket.extensions.DefaultExtension;

public abstract class BlockRotatedPillar extends Block {
   public static PropertyEnum<EnumFacing$Axis> N = PropertyEnum.create("axis", EnumFacing$Axis.class);
   public DefaultExtension field_0002;
   public WorldBorderManager field_0005;
   public ThreadPerChannelEventLoop$1 field_0003;
   public ProtocolFamilyConverter field_0000;
   public ModelSprite field_0001;

   public BlockRotatedPillar(Material var1) {
      super(var1, var1.getMaterialMapColor());
   }

   public BlockRotatedPillar(Material var1, MapColor var2) {
      super(var1, var2);
   }
}
