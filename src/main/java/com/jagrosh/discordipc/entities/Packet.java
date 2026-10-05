package com.jagrosh.discordipc.entities;

import java.nio.ByteBuffer;
import org.json.JSONObject;

public class Packet {
   public JSONObject recoveredField3498;
   public Packet$OpCode recoveredField3499;

   public Packet(Packet$OpCode var1, JSONObject var2) {
      this.recoveredField3499 = var1;
      this.recoveredField3498 = var2;
   }

   public Packet$OpCode method_13359() {
      return this.recoveredField3499;
   }

   @Override
   public String toString() {
      return "Pkt:" + this.method_13359() + this.method_13358().toString();
   }

   public byte[] method_13360() {
      byte[] var1 = this.recoveredField3498.toString().getBytes();
      ByteBuffer var2 = ByteBuffer.allocate(var1.length + 8);
      var2.putInt(Integer.reverseBytes(this.recoveredField3499.ordinal()));
      var2.putInt(Integer.reverseBytes(var1.length));
      var2.put(var1);
      return var2.array();
   }

   public JSONObject method_13358() {
      return this.recoveredField3498;
   }
}
