package com.jagrosh.discordipc.entities;

import java.util.function.Consumer;
import net.minecraft.client.gui.GuiScreenCustomizePresets$Info;
import net.minecraft.entity.passive.EntityAmbientCreature;

public class Callback {
   public Consumer<Packet> field_0001;
   public GuiScreenCustomizePresets$Info field_0003;
   public Consumer<String> field_0000;
   public EntityAmbientCreature field_0002;

   public boolean method_13346() {
      return this.field_0001 == null && this.field_0000 == null;
   }

   public Callback(Consumer<Packet> var1) {
      this(var1, null);
   }

   public Callback(Runnable var1, Consumer<String> var2) {
      this(var1x -> var1.run(), var2);
   }

   public void method_13349(String var1) {
      if (this.field_0000 != null) {
         this.field_0000.accept(var1);
      }
   }

   public void method_13347(Packet var1) {
      if (this.field_0001 != null) {
         this.field_0001.accept(var1);
      }
   }

   public Callback(Consumer<Packet> var1, Consumer<String> var2) {
      this.field_0001 = var1;
      this.field_0000 = var2;
   }

   public Callback(Runnable var1) {
      this(var1x -> var1.run(), null);
   }

   public Callback() {
      this((Consumer<Packet>)null, null);
   }
}
