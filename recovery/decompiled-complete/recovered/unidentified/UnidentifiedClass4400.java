package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import io.netty.bootstrap.AbstractBootstrap;
import java.util.Objects;
import java.util.stream.Collectors;

public class UnidentifiedClass4400 extends UnidentifiedClass4535 {
   public AbstractBootstrap field_0000;
   public UnidentifiedClass0849 field_0001;

   public UnidentifiedClass4400(int var1) {
      super(
         var1,
         CheatBreaker.getInstance()
            .method_19783()
            .method_01369()
            .stream()
            .map(CheatBreaker.getInstance().method_19783()::method_01372)
            .filter(Objects::nonNull)
            .limit(67121224L & 675776538L)
            .map(var0 -> new UnidentifiedClass0127(var0, var0.method_02056(), var0.method_02053()))
            .collect(Collectors.toList())
      );
      this.field_0003 = var0 -> {};
   }

   @Override
   public boolean b_() {
      return false;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
   }
}
