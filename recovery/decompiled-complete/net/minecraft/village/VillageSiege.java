package net.minecraft.village;

import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving$SpawnPlacementType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.server.network.NetHandlerHandshakeTCP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import org.java_websocket.exceptions.InvalidFrameException;

public class VillageSiege {
   public ItemGlassBottle field_0006;
   public boolean field_75535_b;
   public Village theVillage;
   public NetHandlerHandshakeTCP field_0010;
   public int field_0001;
   public int field_0002;
   public int field_0012;
   public int field_0009;
   public NoDiscordClientException field_0003;
   public RenderBiped field_0013;
   public int field_75536_c = -1;
   public int field_0007;
   public InvalidFrameException field_0008;
   public World worldObj;

   public VillageSiege(World var1) {
      this.worldObj = var1;
   }

   public void tick() {
      if (this.worldObj.isDaytime()) {
         this.field_75536_c = 0;
      } else if (this.field_75536_c != 2) {
         if (this.field_75536_c == 0) {
            float var1 = this.worldObj.getCelestialAngle(0.0F);
            if (var1 < 0.5 || var1 > 0.501) {
               return;
            }

            this.field_75536_c = this.worldObj.s.nextInt(10) == 0 ? 1 : 2;
            this.field_75535_b = false;
            if (this.field_75536_c == 2) {
               return;
            }
         }

         if (this.field_75536_c != -1) {
            if (!this.field_75535_b) {
               if (!this.method_06690()) {
                  return;
               }

               this.field_75535_b = true;
            }

            if (this.field_0007 > 0) {
               this.field_0007--;
            } else {
               this.field_0007 = 2;
               if (this.field_0012 > 0) {
                  this.method_06687();
                  this.field_0012--;
               } else {
                  this.field_75536_c = 2;
               }
            }
         }
      }
   }

   public boolean method_06690() {
      for (EntityPlayer var3 : this.worldObj.j) {
         if (!var3.isSpectator()) {
            this.theVillage = this.worldObj.getVillageCollection().getNearestVillage(new BlockPos(var3), 1);
            if (this.theVillage != null
               && this.theVillage.getNumVillageDoors() >= 10
               && this.theVillage.getTicksSinceLastDoorAdding() >= 20
               && this.theVillage.getNumVillagers() >= 20) {
               BlockPos var4 = this.theVillage.getCenter();
               float var5 = this.theVillage.getVillageRadius();
               boolean var6 = false;

               for (int var7 = 0; var7 < 10; var7++) {
                  float var8 = this.worldObj.s.nextFloat() * (float) Math.PI * 2.0F;
                  this.field_0009 = var4.getX() + (int)(MathHelper.cos(var8) * var5 * 0.9);
                  this.field_0002 = var4.getY();
                  this.field_0001 = var4.getZ() + (int)(MathHelper.sin(var8) * var5 * 0.9);
                  var6 = false;

                  for (Village var10 : this.worldObj.getVillageCollection().getVillageList()) {
                     if (var10 != this.theVillage && var10.func_179866_a(new BlockPos(this.field_0009, this.field_0002, this.field_0001))) {
                        var6 = true;
                        break;
                     }
                  }

                  if (!var6) {
                     break;
                  }
               }

               if (var6) {
                  return false;
               }

               Vec3 var11 = this.func_179867_a(new BlockPos(this.field_0009, this.field_0002, this.field_0001));
               if (var11 != null) {
                  this.field_0007 = 0;
                  this.field_0012 = 20;
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean method_06687() {
      Vec3 var1 = this.func_179867_a(new BlockPos(this.field_0009, this.field_0002, this.field_0001));
      if (var1 == null) {
         return false;
      } else {
         EntityZombie var2;
         try {
            var2 = new EntityZombie(this.worldObj);
            var2.onInitialSpawn(this.worldObj.E(new BlockPos(var2)), (IEntityLivingData)null);
            var2.setVillager(false);
         } catch (Exception var4) {
            var4.printStackTrace();
            return false;
         }

         var2.a_(var1.xCoord, var1.yCoord, var1.zCoord, this.worldObj.s.nextFloat() * 360.0F, 0.0F);
         this.worldObj.spawnEntityInWorld(var2);
         BlockPos var3 = this.theVillage.getCenter();
         var2.setHomePosAndDistance(var3, this.theVillage.getVillageRadius());
         return true;
      }
   }

   public Vec3 func_179867_a(BlockPos var1) {
      for (int var2 = 0; var2 < 10; var2++) {
         BlockPos var3 = var1.add(this.worldObj.s.nextInt(16) - 8, this.worldObj.s.nextInt(6) - 3, this.worldObj.s.nextInt(16) - 8);
         if (this.theVillage.func_179866_a(var3)
            && SpawnerAnimals.canCreatureTypeSpawnAtLocation(EntityLiving$SpawnPlacementType.ON_GROUND, this.worldObj, var3)) {
            return new Vec3(var3.getX(), var3.getY(), var3.getZ());
         }
      }

      return null;
   }
}
