package net.minecraft.client.gui;

import com.cheatbreaker.client.module.type.CoordinatesModule;
import io.netty.buffer.PooledDirectByteBuf;
import io.netty.util.concurrent.ImmediateEventExecutor$ImmediateProgressivePromise;
import net.minecraft.block.BlockStainedGlass;

public class GuiPageButtonList$GuiListEntry {
   public String field_178937_b;
   public PooledDirectByteBuf field_0005;
   public ImmediateEventExecutor$ImmediateProgressivePromise field_0004;
   public BlockStainedGlass field_0002;
   public int field_178939_a;
   public CoordinatesModule field_0000;
   public boolean field_178938_c;

   public int func_178935_b() {
      return this.field_178939_a;
   }

   public boolean func_178934_d() {
      return this.field_178938_c;
   }

   public GuiPageButtonList$GuiListEntry(int var1, String var2, boolean var3) {
      this.field_178939_a = var1;
      this.field_178937_b = var2;
      this.field_178938_c = var3;
   }

   public String func_178936_c() {
      return this.field_178937_b;
   }
}
