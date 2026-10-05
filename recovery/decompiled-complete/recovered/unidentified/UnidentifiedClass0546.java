package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import io.netty.handler.codec.rtsp.RtspHeaders$Names;
import javazoom.jl.converter.RiffFile;
import net.minecraft.client.main.lIlIIIIIllIllIIlIlllllllI;
import net.minecraft.client.renderer.entity.RenderItem$7;
import net.optifine.util.MemoryMonitor;

public class UnidentifiedClass0546 extends AbstractElement {
   public lIlIIIIIllIllIIlIlllllllI field_0004;
   public RenderItem$7 field_0007;
   public String field_0003;
   public boolean field_0006 = false;
   public RiffFile field_0000;
   public MemoryMonitor field_0001;
   public ColorFade field_0008;
   public int field_0005;
   public RtspHeaders$Names field_0002;

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      if (this.field_0006) {
         String var4 = UnidentifiedClass0882.method_05899(this.field_0003).method_29697(50).method_29703(false).method_29693();
         String[] var5 = var4.split("\n");
         int var6 = 0;

         for (String var10 : var5) {
            CheatBreaker.getInstance()
               .playRegular14px
               .drawStringWithShadow(
                  var10,
                  this.x,
                  this.y
                     - CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F
                     + var6 * CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F,
                  this.field_0008.method_25066(this.a_(var1, var2) && var3).getRGB()
               );
            var6++;
         }
      } else {
         double var10002 = this.x;
         double var10003 = this.y;
         CheatBreaker.getInstance()
            .field_0037
            .drawStringWithShadow(this.field_0003, var10002, var10003, this.field_0008.method_25066(this.a_(var1, var2) && var3).getRGB());
      }
   }

   public UnidentifiedClass0546(String var1, int var2) {
      this(var1);
      this.field_0006 = true;
      this.field_0005 = var2;
   }

   public UnidentifiedClass0546(String var1) {
      this.field_0003 = var1;
      this.field_0008 = new ColorFade(-1879048193, -806424850);
   }
}
