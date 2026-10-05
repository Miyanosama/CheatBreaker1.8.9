package net.minecraft.world;

import net.minecraft.item.Item$9;
import net.minecraft.server.management.UserListOps;
import net.minecraft.util.BlockPos;

public class Teleporter$PortalPosition extends BlockPos {
   public long lastUpdateTime;
   public UserListOps field_0000;
   public Item$9 field_0003;

   public Teleporter$PortalPosition(Teleporter var1, BlockPos var2, long var3) {
      this.field_85088_e = var1;
      super(var2.getX(), var2.getY(), var2.getZ());
      this.lastUpdateTime = var3;
   }
}
