package io.netty.handler.codec;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import net.minecraft.item.ItemEnderEye;
import net.minecraft.world.biome.BiomeGenHell;
import com.cheatbreaker.client.ui.selection.SelectionOptionElement;

public class EncoderException extends CodecException {
   public static final long serialVersionUID = -5086121160476476774L;

   public EncoderException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public EncoderException(Throwable var1) {
      super(var1);
   }

   public EncoderException() {
   }

   public EncoderException(String var1) {
      super(var1);
   }
}
