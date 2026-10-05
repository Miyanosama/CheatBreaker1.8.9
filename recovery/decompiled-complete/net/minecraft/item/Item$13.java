package net.minecraft.item;

import com.google.common.base.Function;
import io.netty.util.internal.UnsafeAtomicIntegerFieldUpdater;
import javazoom.jl.decoder.Decoder;
import net.minecraft.block.BlockSand$EnumType;
import net.minecraft.client.model.ModelMinecart;
import net.optifine.gui.GuiScreenCapeOF;
import org.apache.log4j.lf5.viewer.LogTable$LogTableListSelectionListener;

public class Item$13 implements Function<ItemStack, String> {
   public ModelMinecart field_0002;
   public GuiScreenCapeOF field_0004;
   public UnsafeAtomicIntegerFieldUpdater field_0001;
   public LogTable$LogTableListSelectionListener field_0003;
   public Decoder field_0000;

   public String apply(ItemStack var1) {
      return BlockSand$EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
