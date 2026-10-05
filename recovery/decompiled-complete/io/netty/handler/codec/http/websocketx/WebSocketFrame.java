package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.util.internal.StringUtil;
import net.minecraft.creativetab.CreativeTabs$5;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.optifine.entity.model.ModelAdapterMooshroom;
import net.optifine.http.FileUploadThread;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

public abstract class WebSocketFrame extends DefaultByteBufHolder {
   public StructureBoundingBox __junk6491344247741221272;
   public FileUploadThread __junk3012238139350898821;
   public EntityLeashKnot __junk7262755663838830638;
   public boolean finalFragment;
   public CreativeTabs$5 __junk8930579973624013762;
   public int rsv;
   public WebsocketNotConnectedException __junk2727078364555212823;
   public ModelAdapterMooshroom __junk5565892872630713493;

   public abstract WebSocketFrame copy();

   @Override
   public String toString() {
      return StringUtil.simpleClassName(this) + "(data: " + this.content().toString() + ')';
   }

   public WebSocketFrame(ByteBuf var1) {
      this(true, 0, var1);
   }

   public WebSocketFrame retain() {
      super.retain();
      return this;
   }

   public int rsv() {
      return this.rsv;
   }

   public WebSocketFrame retain(int var1) {
      super.retain(var1);
      return this;
   }

   public boolean isFinalFragment() {
      return this.finalFragment;
   }

   public WebSocketFrame(boolean var1, int var2, ByteBuf var3) {
      super(var3);
      this.finalFragment = var1;
      this.rsv = var2;
   }

   public abstract WebSocketFrame duplicate();
}
