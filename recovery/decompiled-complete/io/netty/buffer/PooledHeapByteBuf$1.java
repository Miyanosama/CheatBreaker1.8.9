package io.netty.buffer;

import io.netty.handler.codec.http.websocketx.Utf8Validator;
import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.client.gui.stream.GuiIngestServers$ServerList;
import net.minecraft.world.gen.structure.MapGenStructureData;

public class PooledHeapByteBuf$1 extends Recycler<PooledHeapByteBuf> {
   public MapGenStructureData __junk758918458394160315;
   public Utf8Validator __junk2628004917459081109;
   public GuiIngestServers$ServerList __junk8472084577923280285;

   public PooledHeapByteBuf newObject(Recycler$Handle var1) {
      return new PooledHeapByteBuf(var1, 0, null);
   }
}
