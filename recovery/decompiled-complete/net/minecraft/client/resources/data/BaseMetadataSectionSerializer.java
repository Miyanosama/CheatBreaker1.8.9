package net.minecraft.client.resources.data;

import io.netty.channel.epoll.IovArray;
import net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook;
import net.minecraft.world.ColorizerFoliage;

public abstract class BaseMetadataSectionSerializer<T extends IMetadataSection> implements IMetadataSectionSerializer<T> {
   public C03PacketPlayer$C05PacketPlayerLook field_0002;
   public ColorizerFoliage field_0000;
   public IovArray field_0001;
}
