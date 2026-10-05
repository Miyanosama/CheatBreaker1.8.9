package recovered.unidentified;

import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Team$EnumVisible;

// $VF: synthetic class
public class UnidentifiedClass0201 {
   public PotionEffect field_0003;
   public EntityMagmaCube field_0002;
   public WebSocketServerProtocolHandshakeHandler field_0004;
   public SlotFurnaceOutput field_0000;
   public GLAllocation field_0001;

   static {
      try {
         field_0005[Team$EnumVisible.NEVER.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0005[Team$EnumVisible.HIDE_FOR_OTHER_TEAMS.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0005[Team$EnumVisible.HIDE_FOR_OWN_TEAM.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
