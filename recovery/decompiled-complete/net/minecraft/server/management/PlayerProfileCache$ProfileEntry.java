package net.minecraft.server.management;

import com.mojang.authlib.GameProfile;
import java.util.Date;
import net.minecraft.client.model.ModelRabbit;
import net.minecraft.client.renderer.BlockModelShapes$1;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.entity.Entity$1;
import net.minecraft.util.EnumTypeAdapterFactory$1;
import net.minecraft.world.gen.structure.MapGenVillage;
import recovered.unidentified.UnidentifiedClass3516;

public class PlayerProfileCache$ProfileEntry {
   public Entity$1 field_0004;
   public MapGenVillage field_0007;
   public RenderSkeleton field_0003;
   public UnidentifiedClass3516 field_0006;
   public GameProfile gameProfile;
   public EnumTypeAdapterFactory$1 field_0001;
   public BlockModelShapes$1 field_0008;
   public Date expirationDate;
   public ModelRabbit field_0009;

   public PlayerProfileCache$ProfileEntry(PlayerProfileCache var1, GameProfile var2, Date var3) {
      this.field_152671_a = var1;
      super();
      this.gameProfile = var2;
      this.expirationDate = var3;
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public Date getExpirationDate() {
      return this.expirationDate;
   }
}
