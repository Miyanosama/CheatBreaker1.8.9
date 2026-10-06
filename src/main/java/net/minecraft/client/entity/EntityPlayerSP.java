package net.minecraft.client.entity;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.ToggleSprintModule;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSoundMinecartRiding;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;
import org.lwjgl.opengl.Display;
import com.cheatbreaker.client.command.ModuleCommand;
import com.cheatbreaker.client.emote.Emote;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import com.cheatbreaker.client.util.input.ToggleSprintMovementInput;
import com.cheatbreaker.client.util.ClientCredits;

public class EntityPlayerSP extends AbstractClientPlayer {
   public ToggleSprintMovementInput recoveredField2896 = new ToggleSprintMovementInput(Minecraft.getMinecraft().gameSettings);
   public boolean serverSprintState;
   public float recoveredField2897;
   public float recoveredField2898;
   public boolean hasValidHealth;
   public NetHandlerPlayClient sendQueue;
   public float prevRenderArmYaw;
   public int recoveredField2899;
   public String clientBrand;
   public StatFileWriter statWriter;
   public double lastReportedPosY;
   public Minecraft mc;
   public float renderArmYaw;
   public MovementInput movementInput;
   public float lastReportedPitch;
   public float prevRenderArmPitch;
   public float horseJumpPower;
   public double lastReportedPosX;
   public int sprintingTicksLeft;
   public boolean serverSneakState;
   public float renderArmPitch;
   public int recoveredField2900;
   public float lastReportedYaw;
   public int positionUpdateTicks;
   public double lastReportedPosZ;

   @Override
   public void onLivingUpdate() {
      if (CheatBreaker.getInstance().getModuleManager().recoveredField1716.isEnabled()) {
         this.method_06416();
      } else {
         if (this.sprintingTicksLeft > 0) {
            this.sprintingTicksLeft--;
            if (this.sprintingTicksLeft == 0) {
               this.setSprinting(false);
            }
         }

         if (this.recoveredField2899 > 0) {
            this.recoveredField2899--;
         }

         this.recoveredField2897 = this.recoveredField2898;
         if (this.inPortal) {
            if (this.mc.currentScreen != null && !this.mc.currentScreen.b_()) {
               this.mc.displayGuiScreen(null);
            }

            if (this.recoveredField2898 == 0.0F) {
               this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("portal.trigger"), this.V.nextFloat() * 0.4F + 0.8F));
            }

            this.recoveredField2898 += 0.0125F;
            if (this.recoveredField2898 >= 1.0F) {
               this.recoveredField2898 = 1.0F;
            }

            this.inPortal = false;
         } else if (this.isPotionActive(Potion.confusion) && this.getActivePotionEffect(Potion.confusion).getDuration() > 60) {
            this.recoveredField2898 += 0.006666667F;
            if (this.recoveredField2898 > 1.0F) {
               this.recoveredField2898 = 1.0F;
            }
         } else {
            if (this.recoveredField2898 > 0.0F) {
               this.recoveredField2898 -= 0.05F;
            }

            if (this.recoveredField2898 < 0.0F) {
               this.recoveredField2898 = 0.0F;
            }
         }

         if (this.timeUntilPortal > 0) {
            this.timeUntilPortal--;
         }

         boolean var1 = this.movementInput.jump;
         boolean var2 = this.movementInput.sneak;
         float var3 = 0.8F;
         boolean var4 = this.movementInput.recoveredField3366 >= var3;
         this.movementInput.updatePlayerMoveState();
         if (this.isUsingItem() && !this.au()) {
            this.movementInput.recoveredField3365 *= 0.2F;
            this.movementInput.recoveredField3366 *= 0.2F;
            this.recoveredField2899 = 0;
         }

         this.j(this.s - this.J * 0.35, this.getEntityBoundingBox().b + 0.5, this.u + this.J * 0.35);
         this.j(this.s - this.J * 0.35, this.getEntityBoundingBox().b + 0.5, this.u - this.J * 0.35);
         this.j(this.s + this.J * 0.35, this.getEntityBoundingBox().b + 0.5, this.u - this.J * 0.35);
         this.j(this.s + this.J * 0.35, this.getEntityBoundingBox().b + 0.5, this.u + this.J * 0.35);
         boolean var5 = this.getFoodStats().getFoodLevel() > 6.0F || this.bA.allowFlying;
         if (this.C
            && !var2
            && !var4
            && this.movementInput.recoveredField3366 >= var3
            && !this.isSprinting()
            && var5
            && !this.isUsingItem()
            && !this.isPotionActive(Potion.blindness)) {
            if (this.recoveredField2899 <= 0 && !this.mc.gameSettings.recoveredField2692.isKeyDown()) {
               this.recoveredField2899 = 7;
            } else {
               this.setSprinting(true);
            }
         }

         if (!this.isSprinting()
            && this.movementInput.recoveredField3366 >= var3
            && var5
            && !this.isUsingItem()
            && !this.isPotionActive(Potion.blindness)
            && this.mc.gameSettings.recoveredField2692.isKeyDown()) {
            this.setSprinting(true);
         }

         if (this.isSprinting() && (this.movementInput.recoveredField3366 < var3 || this.D || !var5)) {
            this.setSprinting(false);
         }

         if (this.bA.allowFlying) {
            if (this.mc.playerController.method_19874()) {
               if (!this.bA.isFlying) {
                  this.bA.isFlying = true;
                  this.sendPlayerAbilities();
               }
            } else if (!var1 && this.movementInput.jump) {
               if (this.flyToggleTimer == 0) {
                  this.flyToggleTimer = 7;
               } else {
                  this.bA.isFlying = !this.bA.isFlying;
                  this.sendPlayerAbilities();
                  this.flyToggleTimer = 0;
               }
            }
         }

         if (this.bA.isFlying && this.isCurrentViewEntity()) {
            if (this.movementInput.sneak) {
               this.w = this.w - this.bA.getFlySpeed() * 3.0F;
            }

            if (this.movementInput.jump) {
               this.w = this.w + this.bA.getFlySpeed() * 3.0F;
            }
         }

         if (this.isRidingHorse()) {
            if (this.recoveredField2900 < 0) {
               this.recoveredField2900++;
               if (this.recoveredField2900 == 0) {
                  this.horseJumpPower = 0.0F;
               }
            }

            if (var1 && !this.movementInput.jump) {
               this.recoveredField2900 = -10;
               this.sendHorseJump();
            } else if (!var1 && this.movementInput.jump) {
               this.recoveredField2900 = 0;
               this.horseJumpPower = 0.0F;
            } else if (var1) {
               this.recoveredField2900++;
               if (this.recoveredField2900 < 10) {
                  this.horseJumpPower = this.recoveredField2900 * 0.1F;
               } else {
                  this.horseJumpPower = 0.8F + 2.0F / (this.recoveredField2900 - 9) * 0.1F;
               }
            }
         } else {
            this.horseJumpPower = 0.0F;
         }

         super.onLivingUpdate();
         if (this.C && this.bA.isFlying && !this.mc.playerController.method_19874()) {
            this.bA.isFlying = false;
            this.sendPlayerAbilities();
         }
      }
   }

   public void closeScreenAndDropStack() {
      this.bi.setItemStack(null);
      super.closeScreen();
      if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField594.getValue()) {
         Minecraft.getMinecraft().entityRenderer.stopUseShader();
      }

      this.mc.displayGuiScreen(null);
   }

   @Override
   public void displayGui(IInteractionObject var1) {
      String var2 = var1.getGuiID();
      if ("minecraft:crafting_table".equals(var2)) {
         this.mc.displayGuiScreen(new GuiCrafting(this.bi, this.o));
      } else if ("minecraft:enchanting_table".equals(var2)) {
         this.mc.displayGuiScreen(new GuiEnchantment(this.bi, this.o, var1));
      } else if ("minecraft:anvil".equals(var2)) {
         this.mc.displayGuiScreen(new GuiRepair(this.bi, this.o));
      }
   }

   @Override
   public void onUpdate() {
      if (this.o.e(new BlockPos(this.s, 0.0, this.u))) {
         super.onUpdate();
         if (this.au()) {
            this.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(this.y, this.z, this.C));
            this.sendQueue.addToSendQueue(new C0CPacketInput(this.aZ, this.ba, this.movementInput.jump, this.movementInput.sneak));
         } else {
            this.onUpdateWalkingPlayer();
         }
      }
   }

   public String getClientBrand() {
      return this.clientBrand;
   }

   @Override
   public EntityItem dropOneItem(boolean var1) {
      C07PacketPlayerDigging.Action var2 = var1 ? C07PacketPlayerDigging.Action.DROP_ALL_ITEMS : C07PacketPlayerDigging.Action.DROP_ITEM;
      this.sendQueue.addToSendQueue(new C07PacketPlayerDigging(var2, BlockPos.ORIGIN, EnumFacing.DOWN));
      return null;
   }

   @Override
   public void openEditSign(TileEntitySign var1) {
      this.mc.displayGuiScreen(new GuiEditSign(var1));
   }

   public void setPlayerSPHealth(float var1) {
      if (this.hasValidHealth) {
         float var2 = this.getHealth() - var1;
         if (var2 <= 0.0F) {
            this.setHealth(var1);
            if (var2 < 0.0F) {
               this.Z = this.aD / 2;
            }
         } else {
            this.aX = var2;
            this.setHealth(this.getHealth());
            this.Z = this.aD;
            this.damageEntity(DamageSource.generic, var2);
            this.au = this.av = 10;
         }
      } else {
         this.setHealth(var1);
         this.hasValidHealth = true;
      }
   }

   @Override
   public void displayGUIChest(IInventory var1) {
      String var2 = var1 instanceof IInteractionObject ? ((IInteractionObject)var1).getGuiID() : "minecraft:container";
      if ("minecraft:chest".equals(var2)) {
         this.mc.displayGuiScreen(new GuiChest(this.bi, var1));
      } else if ("minecraft:hopper".equals(var2)) {
         this.mc.displayGuiScreen(new GuiHopper(this.bi, var1));
      } else if ("minecraft:furnace".equals(var2)) {
         this.mc.displayGuiScreen(new GuiFurnace(this.bi, var1));
      } else if ("minecraft:brewing_stand".equals(var2)) {
         this.mc.displayGuiScreen(new GuiBrewingStand(this.bi, var1));
      } else if ("minecraft:beacon".equals(var2)) {
         this.mc.displayGuiScreen(new GuiBeacon(this.bi, var1));
      } else if (!"minecraft:dispenser".equals(var2) && !"minecraft:dropper".equals(var2)) {
         this.mc.displayGuiScreen(new GuiChest(this.bi, var1));
      } else {
         this.mc.displayGuiScreen(new GuiDispenser(this.bi, var1));
      }
   }

   @Override
   public void sendPlayerAbilities() {
      this.sendQueue.addToSendQueue(new C13PacketPlayerAbilities(this.bA));
   }

   @Override
   public boolean canCommandSenderUseCommand(int var1, String var2) {
      return var1 <= 0;
   }

   @Override
   public void closeScreen() {
      this.sendQueue.addToSendQueue(new C0DPacketCloseWindow(this.bk.d));
      this.closeScreenAndDropStack();
   }

   @Override
   public boolean isSneaking() {
      boolean var1 = this.movementInput != null ? this.movementInput.sneak : false;
      return var1 && !this.bw;
   }

   @Override
   public void displayGUIHorse(EntityHorse var1, IInventory var2) {
      this.mc.displayGuiScreen(new GuiScreenHorseInventory(this.bi, var2, var1));
   }

   public boolean isCurrentViewEntity() {
      return this.mc.getRenderViewEntity() == this;
   }

   @Override
   public void onEnchantmentCritical(Entity var1) {
      this.mc.effectRenderer.emitParticleAtEntity(var1, EnumParticleTypes.CRIT_MAGIC);
   }

   @Override
   public void displayGUIBook(ItemStack var1) {
      Item var2 = var1.getItem();
      if (var2 == Items.writable_book) {
         this.mc.displayGuiScreen(new GuiScreenBook(this, var1, true));
      }
   }

   public void setXPStats(float var1, int var2, int var3) {
      this.bD = var1;
      this.bC = var2;
      this.bB = var3;
   }

   @Override
   public void updateEntityActionState() {
      super.updateEntityActionState();
      if (this.isCurrentViewEntity()) {
         this.aZ = this.movementInput.recoveredField3365;
         this.ba = this.movementInput.recoveredField3366;
         this.aY = this.movementInput.jump;
         this.prevRenderArmYaw = this.renderArmYaw;
         this.prevRenderArmPitch = this.renderArmPitch;
         this.renderArmPitch = (float)(this.renderArmPitch + (this.z - this.renderArmPitch) * 0.5);
         this.renderArmYaw = (float)(this.renderArmYaw + (this.y - this.renderArmYaw) * 0.5);
      }
   }

   public void method_06434(String var1) {
      ChatComponentText var2 = new ChatComponentText(
         EnumChatFormatting.RED + "[C" + EnumChatFormatting.WHITE + "B" + EnumChatFormatting.RED + "] " + EnumChatFormatting.RESET
      );
      ChatComponentText var3 = new ChatComponentText(EnumChatFormatting.GRAY + "Invalid Emote ID: " + var1);
      var2.appendSibling(var3);
      Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var2);
   }

   @Override
   public void damageEntity(DamageSource var1, float var2) {
      if (!this.isEntityInvulnerable(var1)) {
         this.setHealth(this.getHealth() - var2);
      }
   }

   @Override
   public BlockPos getPosition() {
      return new BlockPos(this.s + 0.5, this.t + 0.5, this.u + 0.5);
   }

   @Override
   public void heal(float var1) {
   }

   @Override
   public void setSprinting(boolean var1) {
      super.setSprinting(var1);
      this.sprintingTicksLeft = var1 ? 600 : 0;
   }

   public void onUpdateWalkingPlayer() {
      boolean var1 = this.isSprinting();
      if (var1 != this.serverSprintState) {
         if (var1) {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.START_SPRINTING));
         } else {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.STOP_SPRINTING));
         }

         this.serverSprintState = var1;
      }

      boolean var2 = this.isSneaking();
      if (var2 != this.serverSneakState) {
         if (var2) {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.START_SNEAKING));
         } else {
            this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.STOP_SNEAKING));
         }

         this.serverSneakState = var2;
      }

      if (this.isCurrentViewEntity()) {
         double var3 = this.s - this.lastReportedPosX;
         double var5 = this.getEntityBoundingBox().b - this.lastReportedPosY;
         double var7 = this.u - this.lastReportedPosZ;
         double var9 = this.y - this.lastReportedYaw;
         double var11 = this.z - this.lastReportedPitch;
         boolean var13 = var3 * var3 + var5 * var5 + var7 * var7 > 9.0E-4 || this.positionUpdateTicks >= 20;
         boolean var14 = var9 != 0.0 || var11 != 0.0;
         if (this.m == null) {
            if (var13 && var14) {
               this.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(this.s, this.getEntityBoundingBox().b, this.u, this.y, this.z, this.C));
            } else if (var13) {
               this.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(this.s, this.getEntityBoundingBox().b, this.u, this.C));
            } else if (var14) {
               this.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(this.y, this.z, this.C));
            } else {
               this.sendQueue.addToSendQueue(new C03PacketPlayer(this.C));
            }
         } else {
            this.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(this.v, -999.0, this.x, this.y, this.z, this.C));
            var13 = false;
         }

         this.positionUpdateTicks++;
         if (var13) {
            this.lastReportedPosX = this.s;
            this.lastReportedPosY = this.getEntityBoundingBox().b;
            this.lastReportedPosZ = this.u;
            this.positionUpdateTicks = 0;
         }

         if (var14) {
            this.lastReportedYaw = this.y;
            this.lastReportedPitch = this.z;
         }
      }
   }

   @Override
   public void joinEntityItemWithWorld(EntityItem var1) {
   }

   public void sendChatMessage(String var1) {
      if (var1.equals("/cb debug")) {
         CheatBreaker.getInstance().getGlobalSettings().recoveredField514 = !CheatBreaker.getInstance().getGlobalSettings().recoveredField514;
         Display.setTitle("CheatBreaker 1.8.9");
         ChatComponentText var2 = new ChatComponentText(
            EnumChatFormatting.GRAY + "Debug: " + EnumChatFormatting.RESET + CheatBreaker.getInstance().getGlobalSettings().recoveredField514
         );
         var2.method_07469(true);
         Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var2);
      } else if (var1.equals("/cb credits") && !ClientCredits.method_12803(UUID.fromString(this.mc.getSession().getPlayerID()))) {
         ClientCredits.method_12802();
      } else if (var1.equals("/cb aero")) {
         CheatBreaker.recoveredField1597 = !CheatBreaker.recoveredField1597;

         for (int var9 = 0; var9 < 50; var9++) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Aero Client is BACK!"));
         }
      } else if (var1.startsWith("/cb emote")) {
         String[] var10 = var1.split(" ");
         if (var10.length != 3) {
            ChatComponentText var13 = new ChatComponentText(
               EnumChatFormatting.RED + "[C" + EnumChatFormatting.WHITE + "B" + EnumChatFormatting.RED + "] " + EnumChatFormatting.RESET
            );
            ChatComponentText var16 = new ChatComponentText(EnumChatFormatting.RED + "Usage: /cb emote <id>");
            var13.appendSibling(var16);
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var13);
            return;
         }

         if (var10[2].equals("list")) {
            ChatComponentText var12 = new ChatComponentText(
               EnumChatFormatting.RED + "[C" + EnumChatFormatting.WHITE + "B" + EnumChatFormatting.RED + "] " + EnumChatFormatting.RESET
            );
            StringBuilder var15 = new StringBuilder();

            for (int var18 : CheatBreaker.getInstance().method_19783().method_01369()) {
               Emote var7 = CheatBreaker.getInstance().method_19783().method_01372(var18);
               if (!var15.toString().equals("")) {
                  var15.append(", ");
               }

               var15.append(var7.method_02056()).append(" (ID: ").append(var18).append(")");
            }

            var12.appendSibling(new ChatComponentText(EnumChatFormatting.GRAY + "Emotes: " + var15));
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var12);
            return;
         }

         int var3;
         try {
            var3 = Integer.parseInt(var10[2]);
         } catch (NumberFormatException var8) {
            this.method_06434(var10[2]);
            return;
         }

         Emote var4 = CheatBreaker.getInstance().method_19783().method_01372(var3);
         if (var4 == null) {
            this.method_06434(var3 + "");
            return;
         }

         ChatComponentText var5 = new ChatComponentText(
            EnumChatFormatting.RED + "[C" + EnumChatFormatting.WHITE + "B" + EnumChatFormatting.RED + "] " + EnumChatFormatting.RESET
         );
         ChatComponentText var6 = new ChatComponentText(EnumChatFormatting.GRAY + "Doing emote: " + var4.method_02056());
         var5.appendSibling(var6);
         Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(var5);
         CheatBreaker.getInstance().method_19783().method_01380(Minecraft.getMinecraft().thePlayer, var4);
      } else {
         if (CheatBreaker.getInstance().getGlobalSettings().recoveredField491.method_08908()) {
            for (ModuleCommand var14 : CheatBreaker.getInstance().method_19756().recoveredField291) {
               if (var1.equals(var14.method_00002())) {
                  var14.method_00001();
                  return;
               }
            }
         }

         this.sendQueue.addToSendQueue(new C01PacketChatMessage(var1));
         CheatBreaker.getInstance().getModuleManager().chatModule.recoveredField826.add(System.currentTimeMillis());
      }
   }

   public StatFileWriter getStatFileWriter() {
      return this.statWriter;
   }

   public EntityPlayerSP(Minecraft var1, World var2, NetHandlerPlayClient var3, StatFileWriter var4) {
      super(var2, var3.getGameProfile());
      this.sendQueue = var3;
      this.statWriter = var4;
      this.mc = var1;
      this.am = 0;
   }

   @Override
   public void addChatMessage(IChatComponent var1) {
      this.mc.ingameGUI.getChatGUI().printChatMessage(var1);
   }

   public boolean isRidingHorse() {
      return this.m != null && this.m instanceof EntityHorse && ((EntityHorse)this.m).isHorseSaddled();
   }

   public float getHorseJumpPower() {
      return this.horseJumpPower;
   }

   @Override
   public void addStat(StatBase var1, int var2) {
      if (var1 != null && var1.isIndependent) {
         super.addStat(var1, var2);
      }
   }

   @Override
   public void displayVillagerTradeGui(IMerchant var1) {
      this.mc.displayGuiScreen(new GuiMerchant(this.bi, var1, this.o));
   }

   @Override
   public boolean isServerWorld() {
      return true;
   }

   @Override
   public void openEditCommandBlock(CommandBlockLogic var1) {
      this.mc.displayGuiScreen(new GuiCommandBlock(var1));
   }

   @Override
   public boolean j(double var1, double var3, double var5) {
      if (this.T) {
         return false;
      } else {
         BlockPos var7 = new BlockPos(var1, var3, var5);
         double var8 = var1 - var7.getX();
         double var10 = var5 - var7.getZ();
         if (!this.isOpenBlockSpace(var7)) {
            byte var12 = -1;
            double var13 = 9999.0;
            if (this.isOpenBlockSpace(var7.west()) && var8 < var13) {
               var13 = var8;
               var12 = 0;
            }

            if (this.isOpenBlockSpace(var7.east()) && 1.0 - var8 < var13) {
               var13 = 1.0 - var8;
               var12 = 1;
            }

            if (this.isOpenBlockSpace(var7.north()) && var10 < var13) {
               var13 = var10;
               var12 = 4;
            }

            if (this.isOpenBlockSpace(var7.south()) && 1.0 - var10 < var13) {
               var13 = 1.0 - var10;
               var12 = 5;
            }

            float var15 = 0.1F;
            if (var12 == 0) {
               this.v = -var15;
            }

            if (var12 == 1) {
               this.v = var15;
            }

            if (var12 == 4) {
               this.x = -var15;
            }

            if (var12 == 5) {
               this.x = var15;
            }
         }

         return false;
      }
   }

   @Override
   public void respawnPlayer() {
      this.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
   }

   public boolean isOpenBlockSpace(BlockPos var1) {
      return !this.o.getBlockState(var1).getBlock().isNormalCube() && !this.o.getBlockState(var1.up()).getBlock().isNormalCube();
   }

   @Override
   public void onCriticalHit(Entity var1) {
      this.mc.effectRenderer.emitParticleAtEntity(var1, EnumParticleTypes.CRIT);
   }

   @Override
   public void playSound(String var1, float var2, float var3) {
      this.o.playSound(this.s, this.t, this.u, var1, var2, var3, false);
   }

   public void sendHorseJump() {
      this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.RIDING_JUMP, (int)(this.getHorseJumpPower() * 100.0F)));
   }

   @Override
   public void swingItem() {
      super.swingItem();
      this.sendQueue.addToSendQueue(new C0APacketAnimation());
   }

   @Override
   public void addChatComponentMessage(IChatComponent var1) {
      this.mc.ingameGUI.getChatGUI().printChatMessage(var1);
   }

   public void sendHorseInventory() {
      this.sendQueue.addToSendQueue(new C0BPacketEntityAction(this, C0BPacketEntityAction.Action.OPEN_INVENTORY));
   }

   public void method_06416() {
      if (this.sprintingTicksLeft > 0) {
         this.sprintingTicksLeft--;
         if (this.sprintingTicksLeft == 0) {
            this.setSprinting(false);
         }
      }

      if (this.recoveredField2899 > 0) {
         this.recoveredField2899--;
      }

      this.recoveredField2897 = this.recoveredField2898;
      if (this.inPortal) {
         if (this.mc.currentScreen != null && !this.mc.currentScreen.b_()) {
            this.mc.displayGuiScreen((GuiScreen)null);
         }

         if (this.recoveredField2898 == 0.0F) {
            this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("portal.trigger"), this.V.nextFloat() * 0.4F + 0.8F));
         }

         this.recoveredField2898 += 0.0125F;
         if (this.recoveredField2898 >= 1.0F) {
            this.recoveredField2898 = 1.0F;
         }

         this.inPortal = false;
      } else if (this.isPotionActive(Potion.confusion) && this.getActivePotionEffect(Potion.confusion).getDuration() > 60) {
         this.recoveredField2898 += 0.006666667F;
         if (this.recoveredField2898 > 1.0F) {
            this.recoveredField2898 = 1.0F;
         }
      } else {
         if (this.recoveredField2898 > 0.0F) {
            this.recoveredField2898 -= 0.05F;
         }

         if (this.recoveredField2898 < 0.0F) {
            this.recoveredField2898 = 0.0F;
         }
      }

      if (this.timeUntilPortal > 0) {
         this.timeUntilPortal--;
      }

      boolean var1 = this.movementInput.jump;
      boolean var2 = this.movementInput.sneak;
      float var3 = 0.8F;
      boolean var4 = this.movementInput.recoveredField3366 >= var3;
      ToggleSprintMovementInput.method_09085(this.mc, (MovementInputFromOptions)this.movementInput, this);
      if (this.isUsingItem() && !this.au()) {
         this.movementInput.recoveredField3365 *= 0.2F;
         this.movementInput.recoveredField3366 *= 0.2F;
         this.recoveredField2899 = 0;
      }

      this.j(this.s - this.J * 0.35, this.getEntityBoundingBox().b + 0.5, this.u + this.J * 0.35);
      this.j(this.s - this.J * 0.35, this.getEntityBoundingBox().b + 0.5, this.u - this.J * 0.35);
      this.j(this.s + this.J * 0.35, this.getEntityBoundingBox().b + 0.5, this.u - this.J * 0.35);
      this.j(this.s + this.J * 0.35, this.getEntityBoundingBox().b + 0.5, this.u + this.J * 0.35);
      boolean var5 = this.getFoodStats().getFoodLevel() > 6.0F || this.bA.allowFlying;
      boolean var6 = !CheatBreaker.getInstance().getModuleManager().recoveredField1716.isEnabled()
         || !(Boolean)ToggleSprintModule.recoveredField3620.getValue();
      boolean var7 = (Boolean)ToggleSprintModule.recoveredField3619.getValue();
      if (ToggleSprintModule.recoveredField3627) {
         this.setSprinting(false);
         this.recoveredField2896.method_09086(false, false);
         ToggleSprintModule.recoveredField3627 = false;
      }

      if (var6) {
         if ((Boolean)ToggleSprintModule.recoveredField3619.getValue()
            && this.C
            && !var2
            && !var4
            && this.movementInput.recoveredField3366 >= var3
            && !this.isSprinting()
            && var5
            && !this.isUsingItem()
            && !this.isPotionActive(Potion.blindness)) {
            if (this.recoveredField2899 <= 0 && !this.mc.gameSettings.recoveredField2692.isKeyDown()) {
               this.recoveredField2899 = 7;
            } else {
               this.setSprinting(true);
               this.recoveredField2896.method_09086(true, false);
            }

            if (!this.isSprinting()
               && this.movementInput.recoveredField3366 >= var3
               && var5
               && !this.isUsingItem()
               && !this.isPotionActive(Potion.blindness)
               && this.mc.gameSettings.recoveredField2692.isKeyDown()) {
               this.setSprinting(true);
               this.recoveredField2896.method_09086(true, false);
            }
         }
      } else {
         boolean var8 = ToggleSprintMovementInput.recoveredField3284;
         if (var5
            && !this.isUsingItem()
            && !this.isPotionActive(Potion.blindness)
            && !ToggleSprintMovementInput.recoveredField3281
            && (!var7 || !this.isSprinting())) {
            this.setSprinting(var8);
         }

         if (var7
            && !var8
            && this.C
            && !var4
            && this.movementInput.recoveredField3366 >= var3
            && !this.isSprinting()
            && var5
            && !this.isUsingItem()
            && !this.isPotionActive(Potion.blindness)) {
            if (this.recoveredField2899 == 0) {
               this.recoveredField2899 = 7;
            } else {
               this.setSprinting(true);
               this.recoveredField2896.method_09086(true, true);
               this.recoveredField2899 = 0;
            }
         }
      }

      if (this.isSprinting() && (this.movementInput.recoveredField3366 < var3 || this.D || !var5)) {
         this.setSprinting(false);
         if (ToggleSprintMovementInput.recoveredField3281 || var6 || ToggleSprintMovementInput.recoveredField3280 || this.au()) {
            this.recoveredField2896.method_09086(false, false);
         }
      }

      if ((Boolean)ToggleSprintModule.recoveredField3630.getValue()
         && this.bA.isFlying
         && this.mc.gameSettings.recoveredField2692.isKeyDown()
         && this.bA.isCreativeMode) {
         this.bA.setFlySpeed(0.05F * (Float)ToggleSprintModule.recoveredField3623.getValue());
         if (this.movementInput.sneak) {
            this.w = this.w - 0.15 * ((Float)ToggleSprintModule.recoveredField3623.getValue()).floatValue();
         }

         if (this.movementInput.jump) {
            this.w = this.w + 0.15 * ((Float)ToggleSprintModule.recoveredField3623.getValue()).floatValue();
         }
      } else if (this.bA.getFlySpeed() != 0.05F) {
         this.bA.setFlySpeed(0.05F);
      }

      if (this.bA.allowFlying) {
         if (this.mc.playerController.method_19874()) {
            if (!this.bA.isFlying) {
               this.bA.isFlying = true;
               this.sendPlayerAbilities();
            }
         } else if (!var1 && this.movementInput.jump) {
            if (this.flyToggleTimer == 0) {
               this.flyToggleTimer = 7;
            } else {
               this.bA.isFlying = !this.bA.isFlying;
               this.sendPlayerAbilities();
               this.flyToggleTimer = 0;
            }
         }
      }

      if (this.bA.isFlying && this.isCurrentViewEntity()) {
         if (this.movementInput.sneak) {
            this.w = this.w - this.bA.getFlySpeed() * 3.0F;
         }

         if (this.movementInput.jump) {
            this.w = this.w + this.bA.getFlySpeed() * 3.0F;
         }
      }

      if (this.isRidingHorse()) {
         if (this.recoveredField2900 < 0) {
            this.recoveredField2900++;
            if (this.recoveredField2900 == 0) {
               this.horseJumpPower = 0.0F;
            }
         }

         if (var1 && !this.movementInput.jump) {
            this.recoveredField2900 = -10;
            this.sendHorseJump();
         } else if (!var1 && this.movementInput.jump) {
            this.recoveredField2900 = 0;
            this.horseJumpPower = 0.0F;
         } else if (var1) {
            this.recoveredField2900++;
            if (this.recoveredField2900 < 10) {
               this.horseJumpPower = this.recoveredField2900 * 0.1F;
            } else {
               this.horseJumpPower = 0.8F + 2.0F / (this.recoveredField2900 - 9) * 0.1F;
            }
         }
      } else {
         this.horseJumpPower = 0.0F;
      }

      super.onLivingUpdate();
      if (this.C && this.bA.isFlying && !this.mc.playerController.method_19874()) {
         this.bA.isFlying = false;
         this.sendPlayerAbilities();
      }
   }

   @Override
   public boolean attackEntityFrom(DamageSource var1, float var2) {
      return false;
   }

   @Override
   public void mountEntity(Entity var1) {
      super.mountEntity(var1);
      if (var1 instanceof EntityMinecart) {
         this.mc.getSoundHandler().playSound(new MovingSoundMinecartRiding(this, (EntityMinecart)var1));
      }
   }

   public void setClientBrand(String var1) {
      this.clientBrand = var1;
   }

   @Override
   public boolean isUser() {
      return true;
   }
}
