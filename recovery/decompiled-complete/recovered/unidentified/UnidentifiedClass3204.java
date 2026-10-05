package recovered.unidentified;

import io.netty.handler.codec.socks.SocksCmdType;
import io.netty.util.internal.NativeLibraryLoader;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.network.play.server.S33PacketUpdateSign;

public class UnidentifiedClass3204 implements CharSequence {
   public CharSequence field_0003;
   public SocksCmdType field_0005;
   public S33PacketUpdateSign field_0002;
   public NativeLibraryLoader field_0004;
   public GuiEditSign field_0000;
   public CharSequence field_0001;

   @Override
   public char charAt(int var1) {
      return var1 < this.field_0001.length() ? this.field_0001.charAt(var1) : this.field_0003.charAt(var1 - this.field_0001.length());
   }

   public UnidentifiedClass3204(CharSequence var1, CharSequence var2) {
      this.field_0001 = var1;
      this.field_0003 = var2;
   }

   @Override
   public int length() {
      int var1 = this.field_0001.length() + this.field_0003.length() - 1;

      while (var1 > 0 && Character.isWhitespace(this.charAt(var1))) {
         var1--;
      }

      return var1 + 1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = this.length();

      for (int var3 = 0; var3 < var2; var3++) {
         var1.append(this.charAt(var3));
      }

      return var1.toString();
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      return new UnidentifiedClass1256(this, var2, var1);
   }
}
