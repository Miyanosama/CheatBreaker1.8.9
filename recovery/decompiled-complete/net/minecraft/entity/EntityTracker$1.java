package net.minecraft.entity;

import com.cheatbreaker.client.network.messages.Message;
import io.netty.channel.DefaultChannelConfig;
import io.netty.util.concurrent.SingleThreadEventExecutor$1;
import java.util.concurrent.Callable;
import net.minecraft.client.network.NetHandlerPlayClient$3;
import net.minecraft.command.CommandServerKick;

public class EntityTracker$1 implements Callable<String> {
   public CommandServerKick field_0003;
   public NetHandlerPlayClient$3 field_0005;
   public SingleThreadEventExecutor$1 field_0002;
   public DefaultChannelConfig field_0004;
   public Message field_0000;

   public EntityTracker$1(EntityTracker var1, int var2) {
      this.field_96569_b = var1;
      this.field_96570_a = var2;
      super();
   }

   public String call() {
      String var1 = "Once per " + this.field_96570_a + " ticks";
      if (this.field_96570_a == Integer.MAX_VALUE) {
         var1 = "Maximum (" + var1 + ")";
      }

      return var1;
   }
}
