package io.netty.buffer;

import net.minecraft.block.BlockSandStone$EnumType;
import net.minecraft.client.gui.GuiLanguage;
import org.apache.log4j.pattern.PatternParser;

public class CompositeByteBuf$Component {
   public int endOffset;
   public GuiLanguage __junk5749444090821735814;
   public int offset;
   public PatternParser __junk3092618769711087685;
   public BlockSandStone$EnumType __junk1145642659829047689;
   public int length;
   public ByteBuf buf;

   public void freeIfNecessary() {
      this.buf.release();
   }

   public CompositeByteBuf$Component(ByteBuf var1) {
      this.buf = var1;
      this.length = var1.readableBytes();
   }
}
