package net.minecraft.profiler;

import com.cheatbreaker.client.util.dash.Station;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.TimerTask;
import net.minecraft.realms.RealmsVertexFormatElement;
import net.minecraft.util.HttpUtil;

public class PlayerUsageSnooper$1 extends TimerTask {
   public RealmsVertexFormatElement field_0001;
   public Station field_0002;

   public PlayerUsageSnooper$1(PlayerUsageSnooper var1) {
      this.field_76344_a = var1;
      super();
   }

   @Override
   public void run() {
      if (PlayerUsageSnooper.access$000(this.field_76344_a).isSnooperEnabled()) {
         HashMap var1;
         synchronized (PlayerUsageSnooper.access$100(this.field_76344_a)) {
            var1 = Maps.newHashMap(PlayerUsageSnooper.access$200(this.field_76344_a));
            if (PlayerUsageSnooper.access$300(this.field_76344_a) == 0) {
               var1.putAll(PlayerUsageSnooper.access$400(this.field_76344_a));
            }

            var1.put("snooper_count", PlayerUsageSnooper.access$308(this.field_76344_a));
            var1.put("snooper_token", PlayerUsageSnooper.access$500(this.field_76344_a));
         }

         HttpUtil.postMap(PlayerUsageSnooper.access$600(this.field_76344_a), var1, true);
      }
   }
}
