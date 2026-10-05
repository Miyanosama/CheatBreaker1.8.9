package net.minecraft.client.main;

import net.minecraft.block.BlockBarrier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.chunk.VboChunkFactory;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import recovered.unidentified.UnidentifiedClass0913;

public class lIlIIIIIllIllIIlIlllllllI extends Thread {
   public UnidentifiedClass0913 field_0001;
   public VboChunkFactory field_0003;
   public BlockBarrier field_0000;
   public StructureBoundingBox field_0002;

   @Override
   public void run() {
      Minecraft.stopIntegratedServer();
   }

   public lIlIIIIIllIllIIlIlllllllI(String var1) {
      super(var1);
   }
}
