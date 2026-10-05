package net.minecraft.tileentity;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.renderer.BlockModelShapes$5;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TileEntityCommandBlock$1 extends CommandBlockLogic {
   public MapItemRenderer field_0000;
   public BlockModelShapes$5 field_0001;

   @Override
   public Vec3 q_() {
      return new Vec3(this.field_145767_a.c.getX() + 0.5, this.field_145767_a.c.getY() + 0.5, this.field_145767_a.c.getZ() + 0.5);
   }

   @Override
   public void func_145757_a(ByteBuf var1) {
      var1.writeInt(this.field_145767_a.c.getX());
      var1.writeInt(this.field_145767_a.c.getY());
      var1.writeInt(this.field_145767_a.c.getZ());
   }

   public TileEntityCommandBlock$1(TileEntityCommandBlock var1) {
      this.field_145767_a = var1;
      super();
   }

   @Override
   public void updateCommand() {
      this.field_145767_a.z().h(this.field_145767_a.c);
   }

   @Override
   public int func_145751_f() {
      return 0;
   }

   @Override
   public Entity p_() {
      return null;
   }

   @Override
   public BlockPos getPosition() {
      return this.field_145767_a.c;
   }

   @Override
   public void setCommand(String var1) {
      super.setCommand(var1);
      this.field_145767_a.markDirty();
   }

   @Override
   public World s_() {
      return this.field_145767_a.z();
   }
}
