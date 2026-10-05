package net.minecraft.client.gui;

import io.netty.channel.oio.OioByteStreamChannel$2;
import io.netty.handler.timeout.IdleState;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.item.Item;

public class GuiFlatPresets$LayerItem {
   public String field_148233_c;
   public OioByteStreamChannel$2 field_0005;
   public BlockStainedGlass field_0002;
   public IdleState field_0004;
   public int field_179037_b;
   public Item field_148234_a;
   public String field_148232_b;

   public GuiFlatPresets$LayerItem(Item var1, int var2, String var3, String var4) {
      this.field_148234_a = var1;
      this.field_179037_b = var2;
      this.field_148232_b = var3;
      this.field_148233_c = var4;
   }
}
