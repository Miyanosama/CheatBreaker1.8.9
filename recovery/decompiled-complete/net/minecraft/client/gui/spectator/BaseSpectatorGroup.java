package net.minecraft.client.gui.spectator;

import com.cheatbreaker.client.ui.element.type.custom.KeybindElement;
import com.google.common.collect.Lists;
import io.netty.handler.timeout.ReadTimeoutHandler$ReadTimeoutTask;
import java.util.List;
import net.minecraft.client.gui.spectator.categories.TeleportToPlayer;
import net.minecraft.client.gui.spectator.categories.TeleportToTeam;
import net.minecraft.client.resources.data.IMetadataSerializer$1;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import recovered.unidentified.UnidentifiedClass0173;

public class BaseSpectatorGroup implements ISpectatorMenuView {
   public ReadTimeoutHandler$ReadTimeoutTask field_0003;
   public UnidentifiedClass0173 field_0005;
   public KeybindElement field_0002;
   public IMetadataSerializer$1 field_0004;
   public List<ISpectatorMenuObject> field_178671_a = Lists.newArrayList();
   public EntityEnderPearl field_0001;

   @Override
   public IChatComponent func_178670_b() {
      return new ChatComponentText("Press a key to select a command, and again to use it.");
   }

   public BaseSpectatorGroup() {
      this.field_178671_a.add(new TeleportToPlayer());
      this.field_178671_a.add(new TeleportToTeam());
   }

   @Override
   public List<ISpectatorMenuObject> func_178669_a() {
      return this.field_178671_a;
   }
}
