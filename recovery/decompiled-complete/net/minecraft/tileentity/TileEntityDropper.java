package net.minecraft.tileentity;

import io.netty.handler.codec.rtsp.RtspResponseStatuses;
import net.minecraft.block.BlockCactus;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import recovered.unidentified.UnidentifiedClass0144;

public class TileEntityDropper extends TileEntityDispenser {
   public MovingSound field_0002;
   public UnidentifiedClass0144 field_0003;
   public BlockCactus field_0004;
   public RtspResponseStatuses field_0001;
   public TileEntitySpecialRenderer field_0000;

   @Override
   public String z_() {
      return this.u_() ? this.a : "container.dropper";
   }

   @Override
   public String getGuiID() {
      return "minecraft:dropper";
   }
}
