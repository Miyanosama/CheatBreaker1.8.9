/*
 * Decompiled with CFR 0.152.
 */
package io.netty.handler.codec.socks;

import com.cheatbreaker.client.util.server.ServerRestrictionAction;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.socks.SocksAddressType;
import io.netty.handler.codec.socks.SocksCmdResponse;
import io.netty.handler.codec.socks.SocksCmdStatus;
import io.netty.handler.codec.socks.SocksCommonUtils;
import io.netty.handler.codec.socks.SocksProtocolVersion;
import io.netty.handler.codec.socks.SocksResponse;
import io.netty.util.CharsetUtil;
import java.util.List;
import junit.runner.StandardTestSuiteLoader;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockLog;
import net.minecraft.client.particle.EntitySmokeFX;
import org.json.CDL;

public class SocksCmdResponseDecoder
extends ReplayingDecoder<SocksCmdResponseDecoder.State> {
    public SocksProtocolVersion version;
    public byte reserved;
    public String host;
    public int port;
    public int fieldLength;
    public static String name = "SOCKS_CMD_RESPONSE_DECODER";
    public SocksCmdStatus cmdStatus;
    public SocksResponse msg = SocksCommonUtils.UNKNOWN_SOCKS_RESPONSE;
    public SocksAddressType addressType;

    @Override
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws java.lang.Exception {
        block0 : switch ((State)((Object)this.state())) {
            case CHECK_PROTOCOL_VERSION: {
                this.version = SocksProtocolVersion.valueOf(byteBuf.readByte());
                if (this.version != SocksProtocolVersion.SOCKS5) break;
                this.checkpoint(State.READ_CMD_HEADER);
            }
            case READ_CMD_HEADER: {
                this.cmdStatus = SocksCmdStatus.valueOf(byteBuf.readByte());
                this.reserved = byteBuf.readByte();
                this.addressType = SocksAddressType.valueOf(byteBuf.readByte());
                this.checkpoint(State.READ_CMD_ADDRESS);
            }
            case READ_CMD_ADDRESS: {
                switch (this.addressType) {
                    case IPv4: {
                        this.host = SocksCommonUtils.intToIp(byteBuf.readInt());
                        this.port = byteBuf.readUnsignedShort();
                        this.msg = new SocksCmdResponse(this.cmdStatus, this.addressType, this.host, this.port);
                        break block0;
                    }
                    case DOMAIN: {
                        this.fieldLength = byteBuf.readByte();
                        this.host = byteBuf.readBytes(this.fieldLength).toString(CharsetUtil.US_ASCII);
                        this.port = byteBuf.readUnsignedShort();
                        this.msg = new SocksCmdResponse(this.cmdStatus, this.addressType, this.host, this.port);
                        break block0;
                    }
                    case IPv6: {
                        this.host = SocksCommonUtils.ipv6toStr(byteBuf.readBytes(16).array());
                        this.port = byteBuf.readUnsignedShort();
                        this.msg = new SocksCmdResponse(this.cmdStatus, this.addressType, this.host, this.port);
                        break block0;
                    }
                }
            }
        }
        channelHandlerContext.pipeline().remove(this);
        list.add(this.msg);
    }

    public static String getName() {
        return "SOCKS_CMD_RESPONSE_DECODER";
    }

    public SocksCmdResponseDecoder() {
        super(State.CHECK_PROTOCOL_VERSION);
    }

    public static enum State {
        CHECK_PROTOCOL_VERSION,
        READ_CMD_HEADER,
        READ_CMD_ADDRESS;

    }
}

