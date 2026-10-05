package com.jagrosh.discordipc.entities;

import java.util.function.Consumer;

public class Callback {
   public Consumer<Packet> recoveredField1027;
   public Consumer<String> recoveredField1028;

   public boolean method_13346() {
      return this.recoveredField1027 == null && this.recoveredField1028 == null;
   }

   public Callback(Consumer<Packet> var1) {
      this(var1, null);
   }

   public Callback(Runnable var1, Consumer<String> var2) {
      this(var1x -> var1.run(), var2);
   }

   public void method_13349(String var1) {
      if (this.recoveredField1028 != null) {
         this.recoveredField1028.accept(var1);
      }
   }

   public void method_13347(Packet var1) {
      if (this.recoveredField1027 != null) {
         this.recoveredField1027.accept(var1);
      }
   }

   public Callback(Consumer<Packet> var1, Consumer<String> var2) {
      this.recoveredField1027 = var1;
      this.recoveredField1028 = var2;
   }

   public Callback(Runnable var1) {
      this(var1x -> var1.run(), null);
   }

   public Callback() {
      this((Consumer<Packet>)null, null);
   }
}
