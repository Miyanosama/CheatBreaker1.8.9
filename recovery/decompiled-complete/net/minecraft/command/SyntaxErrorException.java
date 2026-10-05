package net.minecraft.command;

import io.netty.handler.codec.socks.SocksAuthRequestDecoder;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.item.ItemEnchantedBook;

public class SyntaxErrorException extends CommandException {
   public SoundCategory field_0000;
   public SocksAuthRequestDecoder field_0002;
   public ItemEnchantedBook field_0001;

   public SyntaxErrorException() {
      this("commands.generic.snytax");
   }

   public SyntaxErrorException(String var1, Object... var2) {
      super(var1, var2);
   }
}
