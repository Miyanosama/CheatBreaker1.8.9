package net.minecraft.client.audio;

import io.netty.channel.epoll.IovArray;
import javax.vecmath.Tuple2d;
import net.minecraft.client.renderer.chunk.VisGraph$1;
import net.optifine.entity.model.ModelAdapterEndermite;
import org.json.JSONPointerException;
import paulscode.sound.SoundSystemLogger;

public class SoundManager$1$1 extends SoundSystemLogger {
   public IovArray field_0003;
   public VisGraph$1 field_0005;
   public ModelAdapterEndermite field_0004;
   public JSONPointerException field_0000;
   public Tuple2d field_0001;

   public SoundManager$1$1(SoundManager$1 var1) {
      this.field_177950_a = var1;
      super();
   }

   public void errorMessage(String var1, String var2, int var3) {
      if (!var2.isEmpty()) {
         SoundManager.access$000().error("Error in class '" + var1 + "'");
         SoundManager.access$000().error(var2);
      }
   }

   public void message(String var1, int var2) {
      if (!var1.isEmpty()) {
         SoundManager.access$000().info(var1);
      }
   }

   public void importantMessage(String var1, int var2) {
      if (!var1.isEmpty()) {
         SoundManager.access$000().warn(var1);
      }
   }
}
