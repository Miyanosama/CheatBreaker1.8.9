package net.minecraft.command.server;

import com.cheatbreaker.client.util.dash.DashHook;
import io.netty.channel.PendingWriteQueue$1;
import java.util.concurrent.Callable;
import javax.vecmath.TexCoord4f;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.entity.passive.EntityRabbit$RabbitJumpHelper;

public class CommandBlockLogic$1 implements Callable<String> {
   public DashHook field_0003;
   public EntityRabbit$RabbitJumpHelper field_0005;
   public TexCoord4f field_0004;
   public PendingWriteQueue$1 field_0000;
   public BlockCommandBlock field_0001;

   public CommandBlockLogic$1(CommandBlockLogic var1) {
      this.field_180325_a = var1;
      super();
   }

   public String call() {
      return this.field_180325_a.getCommand();
   }
}
