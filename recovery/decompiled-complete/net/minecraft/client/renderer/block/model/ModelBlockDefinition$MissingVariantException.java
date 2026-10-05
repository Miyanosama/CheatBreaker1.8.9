package net.minecraft.client.renderer.block.model;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$CounterCell;
import net.minecraft.client.renderer.chunk.ChunkCompileTaskGenerator$Type;
import net.minecraft.entity.Entity$3;
import net.minecraft.world.pathfinder.NodeProcessor;
import net.optifine.gui.GuiPerformanceSettingsOF;
import recovered.unidentified.UnidentifiedClass4617;

public class ModelBlockDefinition$MissingVariantException extends RuntimeException {
   public NodeProcessor field_0003;
   public Entity$3 field_0005;
   public ConcurrentHashMapV8$CounterCell field_0002;
   public GuiPerformanceSettingsOF field_0004;
   public UnidentifiedClass4617 field_0000;
   public ChunkCompileTaskGenerator$Type field_0006;

   public ModelBlockDefinition$MissingVariantException(ModelBlockDefinition var1) {
      this.field_178438_a = var1;
      super();
   }
}
