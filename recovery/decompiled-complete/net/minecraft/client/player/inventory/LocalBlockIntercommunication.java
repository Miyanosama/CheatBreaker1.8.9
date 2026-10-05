package net.minecraft.client.player.inventory;

import net.minecraft.client.gui.inventory.GuiBeacon$Button;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;
import net.optifine.entity.model.anim.ModelResolver;
import recovered.unidentified.UnidentifiedClass0002;
import recovered.unidentified.UnidentifiedClass3613;

public class LocalBlockIntercommunication implements IInteractionObject {
   public RenderIronGolem field_0003;
   public GuiBeacon$Button field_0005;
   public UnidentifiedClass3613 field_0002;
   public UnidentifiedClass0002 field_0004;
   public IChatComponent displayName;
   public ModelResolver field_0001;
   public String guiID;

   @Override
   public IChatComponent getDisplayName() {
      return this.displayName;
   }

   public LocalBlockIntercommunication(String var1, IChatComponent var2) {
      this.guiID = var1;
      this.displayName = var2;
   }

   @Override
   public String getGuiID() {
      return this.guiID;
   }

   @Override
   public Container createContainer(InventoryPlayer var1, EntityPlayer var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String z_() {
      return this.displayName.getUnformattedText();
   }

   @Override
   public boolean u_() {
      return true;
   }
}
