package net.minecraft.client.renderer.entity;

import io.netty.buffer.ByteBufProcessor$10;
import io.netty.buffer.SwappedByteBuf;
import io.netty.handler.codec.http.HttpContentEncoder$Result;
import javazoom.jl.decoder.Crc16;
import net.minecraft.block.BlockCarrot;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.nbt.JsonToNBT$Primitive;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.varia.LevelMatchFilter;

public class RenderOcelot extends RenderLiving<EntityOcelot> {
   public Crc16 field_0005;
   public BlockCarrot field_0010;
   public static ResourceLocation redOcelotTextures = new ResourceLocation("textures/entity/cat/red.png");
   public static ResourceLocation ocelotTextures = new ResourceLocation("textures/entity/cat/ocelot.png");
   public LevelMatchFilter field_0006;
   public ByteBufProcessor$10 field_0007;
   public static ResourceLocation blackOcelotTextures = new ResourceLocation("textures/entity/cat/black.png");
   public S04PacketEntityEquipment field_0008;
   public JsonToNBT$Primitive field_0009;
   public static ResourceLocation siameseOcelotTextures = new ResourceLocation("textures/entity/cat/siamese.png");
   public SwappedByteBuf field_0002;
   public HttpContentEncoder$Result field_0000;

   public void preRenderCallback(EntityOcelot var1, float var2) {
      super.preRenderCallback(var1, var2);
      if (var1.isTamed()) {
         GlStateManager.scale(0.8F, 0.8F, 0.8F);
      }
   }

   public ResourceLocation getEntityTexture(EntityOcelot var1) {
      switch (var1.getTameSkin()) {
         case 0:
         default:
            return ocelotTextures;
         case 1:
            return blackOcelotTextures;
         case 2:
            return redOcelotTextures;
         case 3:
            return siameseOcelotTextures;
      }
   }

   public RenderOcelot(RenderManager var1, ModelBase var2, float var3) {
      super(var1, var2, var3);
   }
}
