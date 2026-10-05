package io.netty.handler.codec.socks;

import net.minecraft.world.gen.structure.StructureStrongholdPieces;

public enum SocksRequestType {
   INIT,
   AUTH,
   CMD,
   UNKNOWN;
   public static SocksRequestType[] $VALUES = new SocksRequestType[]{
      SocksRequestType.INIT, SocksRequestType.AUTH, SocksRequestType.CMD, SocksRequestType.UNKNOWN
   };
}
