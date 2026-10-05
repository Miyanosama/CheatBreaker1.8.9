package net.optifine.expr;

import javazoom.jl.decoder.Equalizer;
import net.minecraft.inventory.AnimalChest;
import org.apache.log4j.xml.DOMConfigurator$4;
import recovered.unidentified.UnidentifiedClass1385;

public class ParseException extends Exception {
   public AnimalChest field_0001;
   public UnidentifiedClass1385 field_0003;
   public Equalizer field_0000;
   public DOMConfigurator$4 field_0002;

   public ParseException(String var1) {
      super(var1);
   }

   public ParseException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
