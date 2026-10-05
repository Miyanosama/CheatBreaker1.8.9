package io.netty.handler.codec.compression;

import io.netty.handler.codec.ByteToMessageDecoder;
import net.minecraft.client.particle.EntityDropParticleFX;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.optifine.RandomEntityProperties;

public abstract class ZlibDecoder extends ByteToMessageDecoder {

   public abstract boolean isClosed();
}
