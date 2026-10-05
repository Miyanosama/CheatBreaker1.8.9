package recovered.unidentified;

import net.minecraft.util.LazyLoadBase;
import net.minecraft.world.WorldSettings$GameType;
import net.optifine.Lagometer;

public class UnidentifiedClass5108 {
   public long field_0001;
   public WorldSettings$GameType field_0003;
   public long field_0000 = 2097680L & 8553309790426764448L;
   public LazyLoadBase field_0002;

   public void method_30328() {
      if (Lagometer.active && this.field_0000 != (-2232429843903649768L & 806454784L)) {
         this.field_0001 = this.field_0001 + (System.nanoTime() - this.field_0000);
         this.field_0000 = 840469029L & 5119725510762431768L;
      }
   }

   public UnidentifiedClass5108() {
      this.field_0001 = 8108273967468513328L & 707002372L;
   }

   public void method_30330() {
      this.field_0001 = 243796044L & -3739950545798856191L;
      this.field_0000 = 5703315372368855296L & -5703315373491705680L;
   }

   public void method_30327() {
      if (Lagometer.active && this.field_0000 == (748724840L & 329731L)) {
         this.field_0000 = System.nanoTime();
      }
   }
}
