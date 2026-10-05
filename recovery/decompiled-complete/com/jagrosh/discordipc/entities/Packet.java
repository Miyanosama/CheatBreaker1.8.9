package com.jagrosh.discordipc.entities;

import io.netty.handler.codec.spdy.SpdyHeaderBlockZlibEncoder;
import io.netty.util.concurrent.DefaultPromise$4;
import java.nio.ByteBuffer;
import net.minecraft.block.BlockNote;
import org.json.JSONObject;

public class Packet {
   public JSONObject field_0003;
   public RichPresence field_0005;
   public DefaultPromise$4 field_0002;
   public SpdyHeaderBlockZlibEncoder field_0004;
   public BlockNote field_0000;
   public Packet$OpCode field_0001;

   public Packet(Packet$OpCode var1, JSONObject var2) {
      this.field_0001 = var1;
      this.field_0003 = var2;
   }

   public Packet$OpCode method_13359() {
      return this.field_0001;
   }

   @Override
   public String toString() {
      return "Pkt:" + this.method_13359() + this.method_13358().toString();
   }

   public byte[] method_13360() {
      byte[] var1 = this.field_0003.toString().getBytes();
      ByteBuffer var2 = ByteBuffer.allocate(var1.length + 8);
      var2.putInt(Integer.reverseBytes(this.field_0001.ordinal()));
      var2.putInt(Integer.reverseBytes(var1.length));
      var2.put(var1);
      return var2.array();
   }

   public JSONObject method_13358() {
      return this.field_0003;
   }
}
