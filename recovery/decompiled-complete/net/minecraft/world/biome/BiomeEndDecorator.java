package net.minecraft.world.biome;

import com.cheatbreaker.client.event.type.RenderWorldEvent;
import io.netty.channel.AbstractChannelHandlerContext$2;
import javazoom.jl.decoder.LayerIIDecoder$SubbandLayer2IntensityStereo;
import net.minecraft.client.model.ModelBox;
import net.minecraft.command.CommandSpreadPlayers$Position;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenSpikes;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeEndDecorator extends BiomeDecorator {
   public RenderWorldEvent field_0006;
   public AbstractChannelHandlerContext$2 field_0005;
   public LayerIIDecoder$SubbandLayer2IntensityStereo field_0003;
   public WorldGenerator spikeGen = new WorldGenSpikes(Blocks.end_stone);
   public EntityAIOwnerHurtTarget field_0000;
   public ModelBox field_0001;
   public CommandSpreadPlayers$Position field_0002;

   @Override
   public void genDecorations(BiomeGenBase var1) {
      this.generateOres();
      if (this.randomGenerator.nextInt(5) == 0) {
         int var2 = this.randomGenerator.nextInt(16) + 8;
         int var3 = this.randomGenerator.nextInt(16) + 8;
         this.spikeGen.generate(this.currentWorld, this.randomGenerator, this.currentWorld.getTopSolidOrLiquidBlock(this.field_180294_c.add(var2, 0, var3)));
      }

      if (this.field_180294_c.getX() == 0 && this.field_180294_c.getZ() == 0) {
         EntityDragon var4 = new EntityDragon(this.currentWorld);
         var4.a_(0.0, 128.0, 0.0, this.randomGenerator.nextFloat() * 360.0F, 0.0F);
         this.currentWorld.spawnEntityInWorld(var4);
      }
   }
}
