package com.cheatbreaker.client.nethandler.server;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;

public class PacketWorldBorder extends Packet {
   public String recoveredField1538;
   public int recoveredField1539 = -13421569;
   public boolean recoveredField1540;
   public String recoveredField1541;
   public double recoveredField1542;
   public double recoveredField1543;
   public double recoveredField1544;
   public boolean recoveredField1545;
   public double recoveredField1546;

   @Override
   public void read(ByteBufWrapper var1) {
      this.recoveredField1538 = var1.readOptional(var1::readString);
      this.recoveredField1541 = var1.readString();
      this.recoveredField1545 = var1.buf().readBoolean();
      this.recoveredField1540 = var1.buf().readBoolean();
      this.recoveredField1539 = var1.buf().readInt();
      this.recoveredField1546 = var1.buf().readDouble();
      this.recoveredField1543 = var1.buf().readDouble();
      this.recoveredField1542 = var1.buf().readDouble();
      this.recoveredField1544 = var1.buf().readDouble();
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeOptional(this.recoveredField1538, var1::writeString);
      var1.writeString(this.recoveredField1541);
      var1.buf().writeBoolean(this.recoveredField1545);
      var1.buf().writeBoolean(this.recoveredField1540);
      var1.buf().writeInt(this.recoveredField1539);
      var1.buf().writeDouble(this.recoveredField1546);
      var1.buf().writeDouble(this.recoveredField1543);
      var1.buf().writeDouble(this.recoveredField1542);
      var1.buf().writeDouble(this.recoveredField1544);
   }

   public double method_24759() {
      return this.recoveredField1546;
   }

   public String method_24756() {
      return this.recoveredField1541;
   }

   public boolean method_24758() {
      return this.recoveredField1540;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11463(this);
   }

   public String method_24753() {
      return this.recoveredField1538;
   }

   public int method_24754() {
      return this.recoveredField1539;
   }

   public double method_24760() {
      return this.recoveredField1544;
   }

   public PacketWorldBorder(String var1, String var2, boolean var3, boolean var4, int var5, double var6, double var8, double var10, double var12) {
      this.recoveredField1538 = var1;
      this.recoveredField1541 = var2;
      this.recoveredField1545 = var3;
      this.recoveredField1540 = var4;
      this.recoveredField1539 = var5;
      this.recoveredField1546 = var6;
      this.recoveredField1543 = var8;
      this.recoveredField1542 = var10;
      this.recoveredField1544 = var12;
   }

   public double method_24757() {
      return this.recoveredField1543;
   }

   public PacketWorldBorder() {
   }

   public double method_24755() {
      return this.recoveredField1542;
   }

   public boolean method_24761() {
      return this.recoveredField1545;
   }
}
