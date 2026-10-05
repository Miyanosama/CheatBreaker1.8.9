package net.minecraft.world;

import io.netty.util.concurrent.SingleThreadEventExecutor$PurgeTask;
import java.util.concurrent.Callable;
import junit.swingui.TestRunner$13;
import net.minecraft.client.renderer.EntityRenderer$4;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Stairs;
import org.apache.log4j.pattern.LiteralPatternConverter;
import org.java_websocket.SocketChannelIOHelper;
import recovered.unidentified.UnidentifiedClass1617;
import recovered.unidentified.UnidentifiedClass3248;

public class World$3 implements Callable<String> {
   public UnidentifiedClass1617 field_0004;
   public TestRunner$13 field_0007;
   public SocketChannelIOHelper field_0003;
   public LiteralPatternConverter field_0006;
   public StructureStrongholdPieces$Stairs field_0000;
   public EntityRenderer$4 field_0008;
   public SingleThreadEventExecutor$PurgeTask field_0005;
   public UnidentifiedClass3248 field_0002;

   public String call() {
      return this.field_77440_a.j.size() + " total; " + this.field_77440_a.j.toString();
   }

   public World$3(World var1) {
      this.field_77440_a = var1;
      super();
   }
}
