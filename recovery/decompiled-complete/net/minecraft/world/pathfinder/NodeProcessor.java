package net.minecraft.world.pathfinder;

import com.cheatbreaker.client.ui.serverlist.PinnedServerEntry;
import net.minecraft.block.BlockDirectional;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public abstract class NodeProcessor {
   public int c;
   public PinnedServerEntry field_0007;
   public RenderVillager field_0003;
   public IntHashMap<PathPoint> pointMap = new IntHashMap<>();
   public BlockDirectional field_0000;
   public S42PacketCombatEvent field_0001;
   public int d;
   public int e;
   public IBlockAccess a;
   public S03PacketTimeUpdate field_0009;

   public PathPoint openPoint(int var1, int var2, int var3) {
      int var4 = PathPoint.makeHash(var1, var2, var3);
      PathPoint var5 = this.pointMap.lookup(var4);
      if (var5 == null) {
         var5 = new PathPoint(var1, var2, var3);
         this.pointMap.addKey(var4, var5);
      }

      return var5;
   }

   public abstract int findPathOptions(PathPoint[] var1, Entity var2, PathPoint var3, PathPoint var4, float var5);

   public abstract PathPoint getPathPointToCoords(Entity var1, double var2, double var4, double var6);

   public abstract PathPoint getPathPointTo(Entity var1);

   public void postProcess() {
   }

   public void initProcessor(IBlockAccess var1, Entity var2) {
      this.a = var1;
      this.pointMap.clearMap();
      this.c = MathHelper.floor_float(var2.J + 1.0F);
      this.d = MathHelper.floor_float(var2.K + 1.0F);
      this.e = MathHelper.floor_float(var2.J + 1.0F);
   }
}
