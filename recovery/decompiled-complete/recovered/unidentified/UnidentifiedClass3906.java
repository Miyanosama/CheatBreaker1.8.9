package recovered.unidentified;

import io.netty.channel.AbstractChannelHandlerContext$4;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlower$EnumFlowerColor;
import net.minecraft.server.management.UserListEntry;

public class UnidentifiedClass3906 extends BlockFlower {
   public UserListEntry field_0000;
   public AbstractChannelHandlerContext$4 field_0001;

   @Override
   public BlockFlower$EnumFlowerColor getBlockType() {
      return BlockFlower$EnumFlowerColor.RED;
   }
}
