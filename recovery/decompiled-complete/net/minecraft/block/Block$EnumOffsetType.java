package net.minecraft.block;

import io.netty.handler.codec.compression.SnappyFramedDecoder;
import io.netty.handler.codec.http.websocketx.WebSocket08FrameEncoder;
import net.minecraft.scoreboard.ScoreHealthCriteria;
import recovered.unidentified.UnidentifiedClass0517;

public enum Block$EnumOffsetType {
   NONE,
   XZ,
   XYZ;

   public SnappyFramedDecoder field_0002;
   // $VF: synthetic field
   public static Block$EnumOffsetType[] $VALUES = new Block$EnumOffsetType[]{NONE, XZ, Block$EnumOffsetType.XYZ};
   public ScoreHealthCriteria field_0001;
   public WebSocket08FrameEncoder field_0007;
   public UnidentifiedClass0517 field_0004;
}
