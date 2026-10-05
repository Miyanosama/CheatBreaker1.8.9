package net.minecraft.entity.player;

import java.util.concurrent.Callable;
import net.minecraft.client.gui.spectator.categories.TeleportToPlayer$1;
import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator$Status;
import net.minecraft.item.ItemLilyPad;
import net.minecraft.item.ItemStack;
import org.apache.log4j.pattern.NameAbbreviator$PatternAbbreviatorFragment;
import recovered.unidentified.UnidentifiedClass1369;

public class InventoryPlayer$1 implements Callable<String> {
   public ItemLilyPad field_0005;
   public TeleportToPlayer$1 field_0002;
   public NameAbbreviator$PatternAbbreviatorFragment field_0004;
   public ChunkCompileTaskGenerator$Status field_0000;
   public UnidentifiedClass1369 field_0006;

   public InventoryPlayer$1(InventoryPlayer var1, ItemStack var2) {
      this.field_0003 = var1;
      this.field_0001 = var2;
      super();
   }

   public String method_29001() {
      return this.field_0001.getDisplayName();
   }
}
