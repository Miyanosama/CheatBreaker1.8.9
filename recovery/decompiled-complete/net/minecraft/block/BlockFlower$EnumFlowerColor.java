package net.minecraft.block;

import com.jagrosh.discordipc.entities.DiscordBuild;
import io.netty.util.concurrent.PromiseTask$RunnableAdapter;
import net.minecraft.init.Blocks;

public enum BlockFlower$EnumFlowerColor {
   RED,
   YELLOW;

   public PromiseTask$RunnableAdapter field_0001;
   public DiscordBuild field_0000;

   public BlockFlower getBlock() {
      return this == YELLOW ? Blocks.yellow_flower : Blocks.red_flower;
   }
}
