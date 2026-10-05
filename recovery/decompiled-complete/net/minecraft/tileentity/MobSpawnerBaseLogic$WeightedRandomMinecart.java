package net.minecraft.tileentity;

import javazoom.jl.decoder.LayerIIIDecoder$gr_info_s;
import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.creativetab.CreativeTabs$3;
import net.minecraft.entity.item.EntityMinecart$EnumMinecartType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.util.WeightedRandom$Item;
import net.optifine.shaders.config.ShaderOptionProfile;

public class MobSpawnerBaseLogic$WeightedRandomMinecart extends WeightedRandom$Item {
   public CreativeTabs$3 field_0003;
   public LayerIIIDecoder$gr_info_s field_0006;
   public ShaderOptionProfile field_0002;
   public TileEntityChestRenderer field_0005;
   public String entityType;
   public NBTTagCompound nbtData;
   public ServerScoreboard field_0004;

   public NBTTagCompound toNBT() {
      NBTTagCompound var1 = new NBTTagCompound();
      var1.setTag("Properties", this.nbtData);
      var1.setString("Type", this.entityType);
      var1.setInteger("Weight", this.a);
      return var1;
   }

   public MobSpawnerBaseLogic$WeightedRandomMinecart(MobSpawnerBaseLogic var1, NBTTagCompound var2, String var3) {
      this(var1, var2, var3, 1);
   }

   public MobSpawnerBaseLogic$WeightedRandomMinecart(MobSpawnerBaseLogic var1, NBTTagCompound var2, String var3, int var4) {
      this.field_98221_d = var1;
      super(var4);
      if (var3.equals("Minecart")) {
         if (var2 != null) {
            var3 = EntityMinecart$EnumMinecartType.byNetworkID(var2.getInteger("Type")).getName();
         } else {
            var3 = "MinecartRideable";
         }
      }

      this.nbtData = var2;
      this.entityType = var3;
   }

   public MobSpawnerBaseLogic$WeightedRandomMinecart(MobSpawnerBaseLogic var1, NBTTagCompound var2) {
      this(var1, var2.getCompoundTag("Properties"), var2.getString("Type"), var2.getInteger("Weight"));
   }
}
