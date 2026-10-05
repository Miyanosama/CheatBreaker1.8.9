package recovered.unidentified;

import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.handler.codec.socks.SocksRequestType;
import io.netty.util.concurrent.AbstractEventExecutorGroup;
import junit.framework.Assert;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.GuiCommandBlock;

public class UnidentifiedClass0064 {
   public String field_0006;
   public CBGuiAnchor field_0011;
   public AbstractEventExecutorGroup field_0005;
   public GuiCommandBlock field_0010;
   public static String field_0001;
   public SocksRequestType field_0002;
   public static String field_0012;
   public String field_0009;
   public int field_0003;
   public int field_0013;
   public int field_0000;
   public EpollSocketChannel field_0007;
   public static String field_0008;
   public ChatLine field_0004;

   public String method_00585(String var1) {
      String var2 = "[" + var1.substring(this.field_0000, var1.length() - this.field_0013 + 1) + "]";
      if (this.field_0000 > 0) {
         var2 = this.method_00584() + var2;
      }

      if (this.field_0013 > 0) {
         var2 = var2 + this.method_00586();
      }

      return var2;
   }

   public String method_00584() {
      return (this.field_0000 > this.field_0003 ? "..." : "") + this.field_0006.substring(Math.max(0, this.field_0000 - this.field_0003), this.field_0000);
   }

   public void method_00587() {
      this.field_0000 = 0;
      int var1 = Math.min(this.field_0006.length(), this.field_0009.length());

      while (this.field_0000 < var1 && this.field_0006.charAt(this.field_0000) == this.field_0009.charAt(this.field_0000)) {
         this.field_0000++;
      }
   }

   public boolean method_00583() {
      return this.field_0006.equals(this.field_0009);
   }

   public String method_00586() {
      int var1 = Math.min(this.field_0006.length() - this.field_0013 + 1 + this.field_0003, this.field_0006.length());
      return this.field_0006.substring(this.field_0006.length() - this.field_0013 + 1, var1)
         + (this.field_0006.length() - this.field_0013 + 1 < this.field_0006.length() - this.field_0003 ? "..." : "");
   }

   public void method_00582() {
      int var1 = this.field_0006.length() - 1;

      for (int var2 = this.field_0009.length() - 1;
         var2 >= this.field_0000 && var1 >= this.field_0000 && this.field_0006.charAt(var1) == this.field_0009.charAt(var2);
         var1--
      ) {
         var2--;
      }

      this.field_0013 = this.field_0006.length() - var1;
   }

   public String method_00588(String var1) {
      if (this.field_0006 != null && this.field_0009 != null && !this.method_00583()) {
         this.method_00587();
         this.method_00582();
         String var2 = this.method_00585(this.field_0006);
         String var3 = this.method_00585(this.field_0009);
         return Assert.format(var1, var2, var3);
      } else {
         return Assert.format(var1, this.field_0006, this.field_0009);
      }
   }

   public UnidentifiedClass0064(int var1, String var2, String var3) {
      this.field_0003 = var1;
      this.field_0006 = var2;
      this.field_0009 = var3;
   }
}
