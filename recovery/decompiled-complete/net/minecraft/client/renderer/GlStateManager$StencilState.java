package net.minecraft.client.renderer;

import io.netty.buffer.AbstractDerivedByteBuf;
import io.netty.util.internal.IntegerHolder;
import net.minecraft.block.BlockPackedIce;
import net.minecraft.client.resources.data.FontMetadataSection;

public class GlStateManager$StencilState {
   public int field_179077_c;
   public int field_179075_e;
   public IntegerHolder field_0003;
   public int field_179076_b;
   public GlStateManager$StencilFunc field_179078_a = new GlStateManager$StencilFunc(null);
   public FontMetadataSection field_0001;
   public int field_179074_d;
   public AbstractDerivedByteBuf field_0005;
   public BlockPackedIce field_0002;

   public GlStateManager$StencilState() {
      this.field_179076_b = -1;
      this.field_179077_c = 7680;
      this.field_179074_d = 7680;
      this.field_179075_e = 7680;
   }
}
