package net.minecraft.util;

import com.google.common.base.Predicate;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.VboRenderList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.java_websocket.enums.ReadyState;

public class EntitySelectors$4 implements Predicate<Entity> {
   public ReadyState field_0001;
   public GuiMainMenu field_0002;
   public VboRenderList field_0000;

   public boolean apply(Entity var1) {
      return !(var1 instanceof EntityPlayer) || !((EntityPlayer)var1).isSpectator();
   }
}
