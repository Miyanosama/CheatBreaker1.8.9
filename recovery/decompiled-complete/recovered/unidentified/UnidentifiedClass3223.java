package recovered.unidentified;

import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.module.SomeRandomAssEnum;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker13;
import net.minecraft.block.BlockDoor$EnumHingePosition;
import net.optifine.util.TextureUtils$1;

public class UnidentifiedClass3223 {
   public float field_0006;
   public float field_0011;
   public BlockDoor$EnumHingePosition field_0005;
   public int field_0010;
   public AbstractModule field_0001;
   public CBModulesGui field_0002;
   public int field_0012;
   public float field_0009;
   public CBGuiAnchor field_0003;
   public float field_0013;
   public float field_0000;
   public TextureUtils$1 field_0007;
   public SomeRandomAssEnum field_0008;
   public WebSocketServerHandshaker13 field_0004;

   public UnidentifiedClass3223(CBModulesGui var1, AbstractModule var2, SomeRandomAssEnum var3, int var4, int var5) {
      this.field_0002 = var1;
      this.field_0001 = var2;
      this.field_0013 = var2.getXTranslation();
      this.field_0011 = var2.getYTranslation();
      this.field_0009 = var2.field_0041 * var2.method_28770();
      this.field_0000 = var2.field_0012 * var2.method_28770();
      this.field_0012 = var4;
      this.field_0010 = var5;
      this.field_0008 = var3;
      this.field_0006 = (Float)var2.field_0044.getValue();
      this.field_0003 = var2.getGuiAnchor();
   }
}
