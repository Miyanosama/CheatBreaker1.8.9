package io.netty.channel;

import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.command.server.CommandStop;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.util.LazyLoadBase;

public class ChannelOutboundBuffer$Entry$1 extends Recycler<ChannelOutboundBuffer$Entry> {
   public CommandStop __junk5113173809679300861;
   public ModifiableAttributeInstance __junk8879906985404477040;
   public LazyLoadBase __junk381683387392016094;

   public ChannelOutboundBuffer$Entry newObject(Recycler$Handle var1) {
      return new ChannelOutboundBuffer$Entry(var1, null);
   }
}
