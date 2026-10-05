package io.netty.handler.codec.socks;

import com.cheatbreaker.client.ui.mainmenu.LegacyMainMenu;
import io.netty.handler.ssl.util.FingerprintTrustManagerFactory;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.util.EnumFacing$1;
import net.minecraft.world.biome.BiomeGenSavanna$Mutated;
import org.java_websocket.drafts.Draft_6455;

public enum SocksCmdRequestDecoder$State {
   CHECK_PROTOCOL_VERSION,
   READ_CMD_HEADER,
   READ_CMD_ADDRESS;

   public BiomeGenSavanna$Mutated __junk3245002008324692329;
   public FingerprintTrustManagerFactory __junk7335801878700871070;
   public NBTSizeTracker __junk3978217221720353638;
   public EnumFacing$1 __junk3915446151971265555;
   public Draft_6455 __junk2759777451630131679;
   public GuiButton __junk8498100550253291378;
   // $VF: synthetic field
   public static SocksCmdRequestDecoder$State[] $VALUES = new SocksCmdRequestDecoder$State[]{
      CHECK_PROTOCOL_VERSION, READ_CMD_HEADER, SocksCmdRequestDecoder$State.READ_CMD_ADDRESS
   };
   public LegacyMainMenu __junk8886176388315551162;
   public EntityAIMoveIndoors __junk8395760349879485756;
}
