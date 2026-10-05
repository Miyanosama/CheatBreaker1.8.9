package net.minecraft.client.gui;

import com.cheatbreaker.client.util.DiscordPresenceManager;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.command.server.CommandBanPlayer;
import net.minecraft.world.gen.structure.StructureVillagePieces$WoodHut;

public class GuiPageButtonList$GuiButtonEntry extends GuiPageButtonList$GuiListEntry {
   public boolean field_178941_a;
   public CommandBanPlayer field_0004;
   public StructureVillagePieces$WoodHut field_0001;
   public DiscordPresenceManager field_0003;
   public BlockPartRotation field_0000;

   public boolean func_178940_a() {
      return this.field_178941_a;
   }

   public GuiPageButtonList$GuiButtonEntry(int var1, String var2, boolean var3, boolean var4) {
      super(var1, var2, var3);
      this.field_178941_a = var4;
   }
}
