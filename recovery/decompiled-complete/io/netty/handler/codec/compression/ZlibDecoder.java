package io.netty.handler.codec.compression;

import io.netty.handler.codec.ByteToMessageDecoder;
import net.minecraft.client.particle.EntityDropParticleFX$WaterFactory;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.optifine.CustomItems$1;
import net.optifine.RandomEntityProperties;

public abstract class ZlibDecoder extends ByteToMessageDecoder {
   public EntityDropParticleFX$WaterFactory __junk6165245982854440766;
   public S2FPacketSetSlot __junk4267458825595751501;
   public CustomItems$1 __junk6983776270803201717;
   public RandomEntityProperties __junk8387390941371505376;

   public abstract boolean isClosed();
}
