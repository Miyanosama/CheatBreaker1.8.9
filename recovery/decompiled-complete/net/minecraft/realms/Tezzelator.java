package net.minecraft.realms;

import io.netty.channel.udt.nio.NioUdtByteAcceptorChannel;
import javazoom.jl.decoder.LayerIIIDecoder$Sftable;
import net.minecraft.client.particle.EntityFishWakeFX$Factory;
import net.minecraft.client.renderer.Tessellator;
import org.apache.log4j.jmx.HierarchyDynamicMBean;
import recovered.unidentified.UnidentifiedClass1104;

public class Tezzelator {
   public HierarchyDynamicMBean field_0003;
   public NioUdtByteAcceptorChannel field_0005;
   public LayerIIIDecoder$Sftable field_0002;
   public static Tessellator t = Tessellator.getInstance();
   public UnidentifiedClass1104 field_0000;
   public EntityFishWakeFX$Factory field_0001;
   public static Tezzelator instance = new Tezzelator();

   public void color(float var1, float var2, float var3, float var4) {
      t.getWorldRenderer().color(var1, var2, var3, var4);
   }

   public Tezzelator vertex(double var1, double var3, double var5) {
      t.getWorldRenderer().pos(var1, var3, var5);
      return this;
   }

   public RealmsBufferBuilder color(int var1, int var2, int var3, int var4) {
      return new RealmsBufferBuilder(t.getWorldRenderer().color(var1, var2, var3, var4));
   }

   public void endVertex() {
      t.getWorldRenderer().endVertex();
   }

   public void begin(int var1, RealmsVertexFormat var2) {
      t.getWorldRenderer().begin(var1, var2.getVertexFormat());
   }

   public void offset(double var1, double var3, double var5) {
      t.getWorldRenderer().setTranslation(var1, var3, var5);
   }

   public Tezzelator tex(double var1, double var3) {
      t.getWorldRenderer().tex(var1, var3);
      return this;
   }

   public void end() {
      t.draw();
   }

   public void normal(float var1, float var2, float var3) {
      t.getWorldRenderer().normal(var1, var2, var3);
   }

   public void tex2(short var1, short var2) {
      t.getWorldRenderer().lightmap(var1, var2);
   }
}
