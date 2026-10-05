package net.minecraftforge.client.model;

import javax.vecmath.Matrix4f;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.world.demo.DemoWorldServer;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.spi.NullWriter;

public class TRSRTransformation {
   public DemoWorldServer field_0001;
   public C09PacketHeldItemChange field_0002;
   public NullWriter field_0000;

   public static boolean isInteger(Matrix4f var0) {
      return false;
   }

   public TRSRTransformation(Matrix4f var1) {
      throw new NotImplementedException("Forge dummy class");
   }
}
