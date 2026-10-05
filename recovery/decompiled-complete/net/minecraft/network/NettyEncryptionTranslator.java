package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import javax.crypto.Cipher;
import net.minecraft.client.multiplayer.WorldClient$2;
import net.minecraft.world.gen.structure.StructureVillagePieces$House1;
import net.optifine.entity.model.ModelAdapterLeadKnot;

public class NettyEncryptionTranslator {
   public WorldClient$2 field_0003;
   public ModelAdapterLeadKnot field_0005;
   public Cipher cipher;
   public byte[] field_150506_c;
   public StructureVillagePieces$House1 field_0000;
   public byte[] field_150505_b = new byte[0];

   public byte[] func_150502_a(ByteBuf var1) {
      int var2 = var1.readableBytes();
      if (this.field_150505_b.length < var2) {
         this.field_150505_b = new byte[var2];
      }

      var1.readBytes(this.field_150505_b, 0, var2);
      return this.field_150505_b;
   }

   public NettyEncryptionTranslator(Cipher var1) {
      this.field_150506_c = new byte[0];
      this.cipher = var1;
   }

   public ByteBuf decipher(ChannelHandlerContext var1, ByteBuf var2) {
      int var3 = var2.readableBytes();
      byte[] var4 = this.func_150502_a(var2);
      ByteBuf var5 = var1.alloc().heapBuffer(this.cipher.getOutputSize(var3));
      var5.writerIndex(this.cipher.update(var4, 0, var3, var5.array(), var5.arrayOffset()));
      return var5;
   }

   public void cipher(ByteBuf var1, ByteBuf var2) {
      int var3 = var1.readableBytes();
      byte[] var4 = this.func_150502_a(var1);
      int var5 = this.cipher.getOutputSize(var3);
      if (this.field_150506_c.length < var5) {
         this.field_150506_c = new byte[var5];
      }

      var2.writeBytes(this.field_150506_c, 0, this.cipher.update(var4, 0, var3, this.field_150506_c));
   }
}
