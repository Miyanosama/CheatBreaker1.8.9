package net.minecraft.block;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker07;
import java.util.List;
import javax.vecmath.Point4d;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.main.lIlIIIIIllIllIIlIlllllllI;
import net.minecraft.client.player.inventory.LocalBlockIntercommunication;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.optifine.CustomColors;
import net.optifine.shaders.gui.GuiShaders;

public class BlockAnvil extends BlockFalling {
   public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing$Plane.HORIZONTAL);
   public Point4d field_0005;
   public CustomColors field_0001;
   public lIlIIIIIllIllIIlIlllllllI field_0002;
   public NioEventLoopGroup field_0008;
   public LocalBlockIntercommunication field_0006;
   public WebSocketServerHandshaker07 field_0009;
   public S0EPacketSpawnObject field_0007;
   public GuiShaders field_0000;
   public static PropertyInteger DAMAGE = PropertyInteger.create("damage", 0, 2);

   public BlockAnvil() {
      super(Material.anvil);
      this.setDefaultState(this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(DAMAGE, 0));
      this.setLightOpacity(0);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(FACING).getHorizontalIndex();
      return var2 | var1.getValue(DAMAGE) << 2;
   }

   @Override
   public void onStartFalling(EntityFallingBlock var1) {
      var1.setHurtEntities(true);
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(var1, 1, 0));
      var3.add(new ItemStack(var1, 1, 1));
      var3.add(new ItemStack(var1, 1, 2));
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      EnumFacing var9 = var8.getHorizontalFacing().rotateY();
      return super.onBlockPlaced(var1, var2, var3, var4, var5, var6, var7, var8).withProperty(FACING, var9).withProperty(DAMAGE, var7 >> 2);
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(DAMAGE);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(var1 & 3)).withProperty(DAMAGE, (var1 & 15) >> 2);
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (!var1.D) {
         var4.displayGui(new BlockAnvil$Anvil(var1, var2));
      }

      return true;
   }

   @Override
   public IBlockState getStateForEntityRender(IBlockState var1) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      return true;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      EnumFacing var3 = var1.getBlockState(var2).getValue(FACING);
      if (var3.getAxis() == EnumFacing$Axis.X) {
         this.a(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
      } else {
         this.a(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
      }
   }

   @Override
   public void onEndFalling(World var1, BlockPos var2) {
      var1.b(1022, var2, 0);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING, DAMAGE);
   }
}
