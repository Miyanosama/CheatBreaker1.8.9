package net.minecraft.block.material;

import com.cheatbreaker.client.ui.fading.ColorFade;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.handler.codec.serialization.CompactObjectOutputStream;
import junit.swingui.TestRunner$2;
import net.minecraft.client.particle.EntitySmokeFX$Factory;
import net.minecraft.client.renderer.EnumFaceDirection$VertexInformation;
import net.minecraft.network.play.client.C03PacketPlayer$C06PacketPlayerPosLook;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import org.apache.log4j.config.PropertySetterException;

public class MaterialPortal extends Material {
   public MapGenMineshaft field_0001;
   public ColorFade field_0003;
   public EnumFaceDirection$VertexInformation field_0004;
   public EpollDatagramChannel field_0000;
   public EntitySmokeFX$Factory field_0002;
   public CompactObjectOutputStream field_0007;
   public TestRunner$2 field_0005;
   public PropertySetterException field_0008;
   public C03PacketPlayer$C06PacketPlayerPosLook field_0006;

   @Override
   public boolean isSolid() {
      return false;
   }

   @Override
   public boolean blocksLight() {
      return false;
   }

   public MaterialPortal(MapColor var1) {
      super(var1);
   }

   @Override
   public boolean blocksMovement() {
      return false;
   }
}
