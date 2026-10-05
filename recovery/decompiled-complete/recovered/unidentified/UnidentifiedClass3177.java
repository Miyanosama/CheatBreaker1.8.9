package recovered.unidentified;

import com.cheatbreaker.client.util.dash.Station;
import io.netty.handler.codec.http.HttpObjectAggregator$AggregatedFullHttpResponse;
import java.util.LinkedList;
import java.util.Queue;

public class UnidentifiedClass3177 extends Thread {
   public Queue<Station> field_0000 = new LinkedList<>();
   public HttpObjectAggregator$AggregatedFullHttpResponse field_0001;

   public void method_19837(Station var1) {
      synchronized (this.field_0000) {
         this.field_0000.offer(var1);
         this.field_0000.notify();
      }
   }

   @Override
   public void run() {
      try {
         while (true) {
            synchronized (this.field_0000) {
               this.field_0000.wait();
               Station var2 = this.field_0000.poll();
               if (var2 != null) {
                  var2.getData();
               }
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }
   }
}
