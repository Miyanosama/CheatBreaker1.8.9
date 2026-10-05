package net.minecraft.client.gui.spectator;

import io.netty.handler.codec.socks.SocksCommonUtils;
import io.netty.util.concurrent.AbstractEventExecutor;
import net.minecraft.entity.passive.EntityRabbit$AIAvoidEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.optifine.entity.model.ModelAdapterChestLarge;
import net.optifine.entity.model.ModelAdapterEnderCrystal;
import org.apache.log4j.AsyncAppender;
import org.apache.log4j.helpers.Transform;

public class SpectatorMenu$1 implements ISpectatorMenuObject {
   public AsyncAppender field_0003;
   public EntityRabbit$AIAvoidEntity field_0006;
   public SocksCommonUtils field_0002;
   public AbstractEventExecutor field_0005;
   public Item field_0000;
   public ModelAdapterEnderCrystal field_0001;
   public Transform field_0007;
   public ModelAdapterChestLarge field_0004;

   @Override
   public boolean func_178662_A_() {
      return false;
   }

   @Override
   public void func_178663_a(float var1, int var2) {
   }

   @Override
   public void func_178661_a(SpectatorMenu var1) {
   }

   @Override
   public IChatComponent getSpectatorName() {
      return new ChatComponentText("");
   }
}
