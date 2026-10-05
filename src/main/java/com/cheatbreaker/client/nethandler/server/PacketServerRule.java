package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import com.cheatbreaker.client.nethandler.obj.ServerRule;

public class PacketServerRule extends Packet {
   public ServerRule recoveredField341;
   public float recoveredField342;
   public boolean recoveredField343;
   public String recoveredField344 = "";
   public int recoveredField345;

   public boolean method_13131() {
      return this.recoveredField343;
   }

   public ServerRule method_13129() {
      return this.recoveredField341;
   }

   public PacketServerRule(ServerRule var1, float var2) {
      this(var1);
      this.recoveredField342 = var2;
   }

   public PacketServerRule(ServerRule var1) {
      this.recoveredField341 = var1;
   }

   public PacketServerRule(ServerRule var1, int var2) {
      this(var1);
      this.recoveredField345 = var2;
   }

   public PacketServerRule() {
   }

   public PacketServerRule(ServerRule var1, boolean var2) {
      this(var1);
      this.recoveredField343 = var2;
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeString(this.recoveredField341.getRuleName());
      var1.buf().writeBoolean(this.recoveredField343);
      var1.buf().writeInt(this.recoveredField345);
      var1.buf().writeFloat(this.recoveredField342);
      var1.writeString(this.recoveredField344);
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11450(this);
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.recoveredField341 = ServerRule.getRuleName(var1.readString());
      this.recoveredField343 = var1.buf().readBoolean();
      this.recoveredField345 = var1.buf().readInt();
      this.recoveredField342 = var1.buf().readFloat();
      this.recoveredField344 = var1.readString();
   }

   public String method_13130() {
      return this.recoveredField344;
   }

   public float method_13127() {
      return this.recoveredField342;
   }

   @Override
   public byte[] readBlob(ByteBufWrapper var1) {
      return super.readBlob(var1);
   }

   public int method_13128() {
      return this.recoveredField345;
   }

   public PacketServerRule(ServerRule var1, String var2) {
      this(var1);
      this.recoveredField344 = var2;
   }
}
