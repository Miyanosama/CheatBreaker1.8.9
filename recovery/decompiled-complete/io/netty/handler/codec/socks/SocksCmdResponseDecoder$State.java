package io.netty.handler.codec.socks;

import com.cheatbreaker.client.util.server.ServerRestrictionAction;
import junit.runner.StandardTestSuiteLoader;
import net.minecraft.block.BlockLog$1;
import net.minecraft.client.particle.EntitySmokeFX$1;
import org.json.CDL;

public enum SocksCmdResponseDecoder$State {
   CHECK_PROTOCOL_VERSION,
   READ_CMD_ADDRESS,
   READ_CMD_HEADER;

   public BlockLog$1 __junk6129005725710447201;
   public ServerRestrictionAction __junk7246944896041744486;
   // $VF: synthetic field
   public static SocksCmdResponseDecoder$State[] $VALUES = new SocksCmdResponseDecoder$State[]{
      SocksCmdResponseDecoder$State.CHECK_PROTOCOL_VERSION, SocksCmdResponseDecoder$State.READ_CMD_HEADER, SocksCmdResponseDecoder$State.READ_CMD_ADDRESS
   };
   public EntitySmokeFX$1 __junk7028006975932527599;
   public StandardTestSuiteLoader __junk1117542557126454635;
   public CDL __junk5109717009903280534;
}
