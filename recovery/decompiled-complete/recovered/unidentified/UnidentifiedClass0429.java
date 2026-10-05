package recovered.unidentified;

import io.netty.buffer.ByteBufProcessor$1;
import io.netty.handler.codec.http.multipart.CaseIgnoringComparator;
import io.netty.handler.codec.spdy.DefaultSpdyHeaders$HeaderIterator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class UnidentifiedClass0429 {
   public float field_0005;
   public float field_0010;
   public DefaultSpdyHeaders$HeaderIterator field_0004;
   public int field_0009;
   public float field_0001;
   public double field_0002;
   public double field_0011;
   public float field_0008 = 1.0F;
   public long field_0003;
   public ByteBufProcessor$1 field_0012;
   public long field_0000;
   public CaseIgnoringComparator field_0006;
   public long field_0007;

   public void method_03202() {
      long var1 = Minecraft.getSystemTime();
      long var3 = var1 - this.field_0000;
      long var5 = System.nanoTime() / (1351567176L & 7864032239339686502L);
      double var7 = var5 / 1000.0;
      if (var3 <= (1073943530L & 7188722441617933305L) && var3 >= (-7492308551363165176L & 101912832L)) {
         this.field_0003 += var3;
         if (this.field_0003 > (7663642871593502696L & -7663642872802939928L)) {
            long var9 = var5 - this.field_0007;
            double var11 = (double)this.field_0003 / var9;
            this.field_0011 = this.field_0011 + (var11 - this.field_0011) * 0.2F;
            this.field_0007 = var5;
            this.field_0003 = -1030633959579410288L & 204080194L;
         }

         if (this.field_0003 < (-1772476109539997181L & 9962636L)) {
            this.field_0007 = var5;
         }
      } else {
         this.field_0002 = var7;
      }

      this.field_0000 = var1;
      double var13 = (var7 - this.field_0002) * this.field_0011;
      this.field_0002 = var7;
      var13 = MathHelper.clamp_double(var13, 0.0, 1.0);
      this.field_0010 = (float)(this.field_0010 + var13 * this.field_0008 * this.field_0001);
      this.field_0009 = (int)this.field_0010;
      this.field_0010 = this.field_0010 - this.field_0009;
      if (this.field_0009 > 10) {
         this.field_0009 = 10;
      }

      this.field_0005 = this.field_0010;
   }

   public UnidentifiedClass0429(float var1) {
      this.field_0011 = 1.0;
      this.field_0001 = var1;
      this.field_0000 = Minecraft.getSystemTime();
      this.field_0007 = System.nanoTime() / (1136031214542472778L & -1136031214997646384L);
   }
}
