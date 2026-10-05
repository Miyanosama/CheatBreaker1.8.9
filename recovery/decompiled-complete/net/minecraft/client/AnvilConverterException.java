package net.minecraft.client;

import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.inventory.Slot;
import net.minecraft.util.IntHashMap;

public class AnvilConverterException extends Exception {
   public IntHashMap field_0001;
   public Slot field_0002;
   public VertexFormat field_0000;

   public AnvilConverterException(String var1) {
      super(var1);
   }
}
