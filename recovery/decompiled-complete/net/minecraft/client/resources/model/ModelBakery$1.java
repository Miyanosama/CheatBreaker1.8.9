package net.minecraft.client.resources.model;

import io.netty.handler.codec.socks.SocksProtocolVersion;
import java.util.Comparator;
import net.minecraft.client.audio.SoundManager$1;
import net.minecraft.client.gui.GuiSlotRealmsProxy;

public class ModelBakery$1 implements Comparator<ModelResourceLocation> {
   public SocksProtocolVersion field_0003;
   public GuiSlotRealmsProxy field_0000;
   public SoundManager$1 field_0002;

   public ModelBakery$1(ModelBakery var1) {
      this.this$0 = var1;
      super();
   }

   public int compare(ModelResourceLocation var1, ModelResourceLocation var2) {
      return var1.toString().compareTo(var2.toString());
   }
}
