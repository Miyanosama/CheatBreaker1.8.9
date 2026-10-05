package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import io.netty.handler.timeout.IdleStateHandler$ReaderIdleTimeoutTask;
import io.netty.util.concurrent.SingleThreadEventExecutor$3;
import javax.vecmath.Tuple3i;
import javazoom.jl.player.jlp;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.passive.EntityRabbit$AIEvilAttack;
import net.minecraft.scoreboard.ScoreHealthCriteria;

public class UnidentifiedClass4579 implements IPCListener {
   public Tuple3i field_0003;
   public jlp field_0006;
   public EntityRabbit$AIEvilAttack field_0002;
   public EntityFireworkRocket field_0000;
   public ScoreHealthCriteria field_0001;
   public IdleStateHandler$ReaderIdleTimeoutTask field_0007;
   public SingleThreadEventExecutor$3 field_0004;

   public UnidentifiedClass4579(CheatBreaker var1) {
      this.field_0005 = var1;
      super();
   }

   @Override
   public void method_13333(IPCClient var1) {
      CheatBreaker.getInstance()
         .method_19780(
            Minecraft.getMinecraft().getSession().getUsername(),
            CheatBreaker.method_19781(CheatBreaker.getInstance()).method_10921(CheatBreaker.getInstance().method_19773())
         );
   }
}
