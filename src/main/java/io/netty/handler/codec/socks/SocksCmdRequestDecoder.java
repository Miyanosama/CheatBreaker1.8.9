/*
 * Decompiled with CFR 0.152.
 */
package io.netty.handler.codec.socks;

import com.cheatbreaker.client.ui.mainmenu.LegacyMainMenu;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import io.netty.handler.codec.socks.SocksAddressType;
import io.netty.handler.codec.socks.SocksCmdRequest;
import io.netty.handler.codec.socks.SocksCmdType;
import io.netty.handler.codec.socks.SocksCommonUtils;
import io.netty.handler.codec.socks.SocksProtocolVersion;
import io.netty.handler.codec.socks.SocksRequest;
import io.netty.handler.ssl.util.FingerprintTrustManagerFactory;
import io.netty.util.CharsetUtil;
import java.util.List;
import net.minecraft.block.BlockButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.network.NetworkSystem;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenSavanna;
import org.apache.log4j.pattern.BridgePatternParser;
import org.java_websocket.drafts.Draft_6455;

public class SocksCmdRequestDecoder
extends ReplayingDecoder<SocksCmdRequestDecoder.State> {
    public SocksProtocolVersion version;
    public SocksAddressType addressType;
    public SocksRequest msg = SocksCommonUtils.UNKNOWN_SOCKS_REQUEST;
    public static String name = "SOCKS_CMD_REQUEST_DECODER";
    public SocksCmdType cmdType;
    public byte reserved;
    public int fieldLength;
    public String host;
    public int port;

    public static String getName() {
        return "SOCKS_CMD_REQUEST_DECODER";
    }

    @Override
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws java.lang.Exception {
        block0 : switch ((State)((Object)this.state())) {
            case CHECK_PROTOCOL_VERSION: {
                this.version = SocksProtocolVersion.valueOf(byteBuf.readByte());
                if (this.version != SocksProtocolVersion.SOCKS5) break;
                this.checkpoint(State.READ_CMD_HEADER);
            }
            case READ_CMD_HEADER: {
                this.cmdType = SocksCmdType.valueOf(byteBuf.readByte());
                this.reserved = byteBuf.readByte();
                this.addressType = SocksAddressType.valueOf(byteBuf.readByte());
                this.checkpoint(State.READ_CMD_ADDRESS);
            }
            case READ_CMD_ADDRESS: {
                switch (this.addressType) {
                    case IPv4: {
                        this.host = SocksCommonUtils.intToIp(byteBuf.readInt());
                        this.port = byteBuf.readUnsignedShort();
                        this.msg = new SocksCmdRequest(this.cmdType, this.addressType, this.host, this.port);
                        break block0;
                    }
                    case DOMAIN: {
                        this.fieldLength = byteBuf.readByte();
                        this.host = byteBuf.readBytes(this.fieldLength).toString(CharsetUtil.US_ASCII);
                        this.port = byteBuf.readUnsignedShort();
                        this.msg = new SocksCmdRequest(this.cmdType, this.addressType, this.host, this.port);
                        break block0;
                    }
                    case IPv6: {
                        this.host = SocksCommonUtils.ipv6toStr(byteBuf.readBytes(16).array());
                        this.port = byteBuf.readUnsignedShort();
                        this.msg = new SocksCmdRequest(this.cmdType, this.addressType, this.host, this.port);
                        break block0;
                    }
                }
            }
        }
        channelHandlerContext.pipeline().remove(this);
        list.add(this.msg);
    }

    public SocksCmdRequestDecoder() {
        super(State.CHECK_PROTOCOL_VERSION);
    }

    public static enum State {
        CHECK_PROTOCOL_VERSION,
        READ_CMD_HEADER,
        READ_CMD_ADDRESS;

    }
}

