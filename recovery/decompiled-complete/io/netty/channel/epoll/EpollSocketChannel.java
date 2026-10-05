/*
 * Decompiled with CFR 0.152.
 */
package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.EventLoop;
import io.netty.channel.epoll.AbstractEpollChannel;
import io.netty.channel.epoll.AbstractEpollChannel$AbstractEpollUnsafe;
import io.netty.channel.epoll.EpollSocketChannel$1;
import io.netty.channel.epoll.EpollSocketChannel$EpollSocketUnsafe;
import io.netty.channel.epoll.EpollSocketChannelConfig;
import io.netty.channel.epoll.IovArray;
import io.netty.channel.epoll.Native;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.ScheduledFuture;
import org.apache.log4j.ConsoleAppender$SystemErrStream;

public class EpollSocketChannel
extends AbstractEpollChannel
implements SocketChannel {
    public SocketAddress requestedRemoteAddress;
    public volatile InetSocketAddress local;
    public EpollSocketChannelConfig config = new EpollSocketChannelConfig(this);
    public ScheduledFuture<?> connectTimeoutFuture;
    public ConsoleAppender.SystemErrStream __junk5557109775122163367;
    public volatile boolean inputShutdown;
    public static String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName(ByteBuf.class) + ", " + StringUtil.simpleClassName(DefaultFileRegion.class) + ')';
    public ChannelPromise connectPromise;
    public volatile InetSocketAddress remote;
    public volatile boolean outputShutdown;

    @Override
    public EpollSocketChannelConfig config() {
        return this.config;
    }

    public boolean writeBytesMultiple(ChannelOutboundBuffer channelOutboundBuffer, ByteBuffer[] byteBufferArray, int n, long l) {
        assert (l != (0x20405610L & 0xCBE6D3A88D00004EL));
        boolean bl = false;
        long l2 = 0x26481815L & 0x824238AL;
        int n2 = 0;
        int n3 = n2 + n;
        block0: while (true) {
            int n4;
            long l3;
            if ((l3 = Native.writev(this.fd, byteBufferArray, n2, n)) == (0x11814006L & 0xFB25133F6C148120L)) {
                this.setEpollOut();
                break;
            }
            l2 += l3;
            if ((l -= l3) == (0xB34E0384098000C8L & 0x46134235L)) {
                bl = true;
                break;
            }
            do {
                ByteBuffer byteBuffer = byteBufferArray[n2];
                int n5 = byteBuffer.position();
                n4 = byteBuffer.limit() - n5;
                if ((long)n4 > l3) {
                    byteBuffer.position(n5 + (int)l3);
                    continue block0;
                }
                --n;
            } while (++n2 < n3 && (l3 -= (long)n4) > (0xA09A931L & 0x1260000L));
        }
        channelOutboundBuffer.removeBytes(l2);
        return bl;
    }

    @Override
    public boolean isOutputShutdown() {
        return this.outputShutdown || !this.isActive();
    }

    @Override
    public SocketAddress localAddress0() {
        return this.local;
    }

    public boolean writeFileRegion(ChannelOutboundBuffer channelOutboundBuffer, DefaultFileRegion defaultFileRegion) {
        long l = defaultFileRegion.count();
        if (defaultFileRegion.transfered() >= l) {
            channelOutboundBuffer.remove();
            return true;
        }
        long l2 = defaultFileRegion.position();
        boolean bl = false;
        long l3 = 0x6D7FCFDE8DF31B01L & 0x12002040L;
        for (int i = this.config().getWriteSpinCount() - 1; i >= 0; --i) {
            long l4 = defaultFileRegion.transfered();
            long l5 = Native.sendfile(this.fd, defaultFileRegion, l2, l4, l - l4);
            if (l5 == (0x6859281684122000L & 0x41088000L)) {
                this.setEpollOut();
                break;
            }
            l3 += l5;
            if (defaultFileRegion.transfered() < l) continue;
            bl = true;
            break;
        }
        if (l3 > (0xC2584BAA01100085L & 0x4E8BC818L)) {
            channelOutboundBuffer.progress(l3);
        }
        if (bl) {
            channelOutboundBuffer.remove();
        }
        return bl;
    }

    public static /* synthetic */ ScheduledFuture access$302(EpollSocketChannel epollSocketChannel, ScheduledFuture scheduledFuture) {
        epollSocketChannel.connectTimeoutFuture = scheduledFuture;
        return epollSocketChannel.connectTimeoutFuture;
    }

    @Override
    public SocketAddress remoteAddress0() {
        return this.remote;
    }

    public static /* synthetic */ InetSocketAddress access$402(EpollSocketChannel epollSocketChannel, InetSocketAddress inetSocketAddress) {
        epollSocketChannel.remote = inetSocketAddress;
        return epollSocketChannel.remote;
    }

    @Override
    public AbstractEpollChannel$AbstractEpollUnsafe newUnsafe() {
        return new EpollSocketChannel$EpollSocketUnsafe(this);
    }

    @Override
    public boolean isInputShutdown() {
        return this.inputShutdown;
    }

    public boolean writeBytesMultiple(ChannelOutboundBuffer channelOutboundBuffer, IovArray iovArray) {
        long l = iovArray.size();
        int n = iovArray.count();
        assert (l != (0x287C180L & 0x34080E12L));
        assert (n != 0);
        boolean bl = false;
        long l2 = 0x1608A045L & 0x41540820L;
        int n2 = 0;
        int n3 = n2 + n;
        block0: while (true) {
            long l3;
            long l4;
            if ((l4 = Native.writevAddresses(this.fd, iovArray.memoryAddress(n2), n)) == (0x28121008L & 0x675FFCB39029C8A0L)) {
                this.setEpollOut();
                break;
            }
            l2 += l4;
            if ((l -= l4) == (0xCB1A97DB20810A3AL & 0x34E56824C9609004L)) {
                bl = true;
                break;
            }
            do {
                if ((l3 = iovArray.processWritten(n2, l4)) == (0xFFFFFFFFFFFFFFFFL & 0xFFFFFFFFFFFFFFFFL)) continue block0;
                --n;
            } while (++n2 < n3 && (l4 -= l3) > (0x22404075L & 0xFCCF2F301919482L));
        }
        channelOutboundBuffer.removeBytes(l2);
        return bl;
    }

    public EpollSocketChannel() {
        super(Native.socketStreamFd(), 1);
    }

    public static /* synthetic */ SocketAddress access$200(EpollSocketChannel epollSocketChannel) {
        return epollSocketChannel.requestedRemoteAddress;
    }

    public static /* synthetic */ InetSocketAddress access$502(EpollSocketChannel epollSocketChannel, InetSocketAddress inetSocketAddress) {
        epollSocketChannel.local = inetSocketAddress;
        return epollSocketChannel.local;
    }

    @Override
    public void doWrite(ChannelOutboundBuffer channelOutboundBuffer) {
        int n;
        do {
            if ((n = channelOutboundBuffer.size()) != 0) continue;
            this.clearEpollOut();
            break;
        } while (!(n > 1 && channelOutboundBuffer.current() instanceof ByteBuf ? !this.doWriteMultiple(channelOutboundBuffer) : !this.doWriteSingle(channelOutboundBuffer)));
    }

    public static /* synthetic */ SocketAddress access$202(EpollSocketChannel epollSocketChannel, SocketAddress socketAddress) {
        epollSocketChannel.requestedRemoteAddress = socketAddress;
        return epollSocketChannel.requestedRemoteAddress;
    }

    public static /* synthetic */ ChannelPromise access$102(EpollSocketChannel epollSocketChannel, ChannelPromise channelPromise) {
        epollSocketChannel.connectPromise = channelPromise;
        return epollSocketChannel.connectPromise;
    }

    public static /* synthetic */ ChannelPromise access$100(EpollSocketChannel epollSocketChannel) {
        return epollSocketChannel.connectPromise;
    }

    public boolean doWriteMultiple(ChannelOutboundBuffer channelOutboundBuffer) {
        if (PlatformDependent.hasUnsafe()) {
            IovArray iovArray = IovArray.get(channelOutboundBuffer);
            int n = iovArray.count();
            if (n >= 1) {
                if (!this.writeBytesMultiple(channelOutboundBuffer, iovArray)) {
                    return false;
                }
            } else {
                channelOutboundBuffer.removeBytes(0x2C0D510CL & 0x53808090L);
            }
        } else {
            ByteBuffer[] byteBufferArray = channelOutboundBuffer.nioBuffers();
            int n = channelOutboundBuffer.nioBufferCount();
            if (n >= 1) {
                if (!this.writeBytesMultiple(channelOutboundBuffer, byteBufferArray, n, channelOutboundBuffer.nioBufferSize())) {
                    return false;
                }
            } else {
                channelOutboundBuffer.removeBytes(0x7F49BB27C0A41404L & 0x36102A00L);
            }
        }
        return true;
    }

    @Override
    public ServerSocketChannel parent() {
        return (ServerSocketChannel)super.parent();
    }

    @Override
    public Object filterOutboundMessage(Object object) {
        if (object instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf)object;
            if (!(byteBuf.hasMemoryAddress() || !PlatformDependent.hasUnsafe() && byteBuf.isDirect())) {
                byteBuf = this.newDirectBuffer(byteBuf);
                assert (byteBuf.hasMemoryAddress());
            }
            return byteBuf;
        }
        if (object instanceof DefaultFileRegion) {
            return object;
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(object) + EXPECTED_TYPES);
    }

    @Override
    public void doBind(SocketAddress socketAddress) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress)socketAddress;
        Native.bind(this.fd, inetSocketAddress.getAddress(), inetSocketAddress.getPort());
        this.local = Native.localAddress(this.fd);
    }

    public static /* synthetic */ boolean access$002(EpollSocketChannel epollSocketChannel, boolean bl) {
        epollSocketChannel.inputShutdown = bl;
        return epollSocketChannel.inputShutdown;
    }

    @Override
    public ChannelFuture shutdownOutput() {
        return this.shutdownOutput(this.newPromise());
    }

    public EpollSocketChannel(Channel channel, int n) {
        super(channel, n, 1, true);
        this.remote = Native.remoteAddress(n);
        this.local = Native.localAddress(n);
    }

    public boolean writeBytes(ChannelOutboundBuffer channelOutboundBuffer, ByteBuf byteBuf) {
        int n = byteBuf.readableBytes();
        if (n == 0) {
            channelOutboundBuffer.remove();
            return true;
        }
        boolean bl = false;
        long l = 0xC50A400L & 0x72221018L;
        if (byteBuf.hasMemoryAddress()) {
            block6: {
                int n2;
                long l2 = byteBuf.memoryAddress();
                int n3 = byteBuf.readerIndex();
                int n4 = byteBuf.writerIndex();
                while ((n2 = Native.writeAddress(this.fd, l2, n3, n4)) > 0) {
                    if ((l += (long)n2) == (long)n) {
                        bl = true;
                        break block6;
                    }
                    n3 += n2;
                }
                this.setEpollOut();
            }
            channelOutboundBuffer.removeBytes(l);
            return bl;
        }
        if (byteBuf.nioBufferCount() == 1) {
            block7: {
                int n5;
                int n6;
                int n7;
                int n8 = byteBuf.readerIndex();
                ByteBuffer byteBuffer = byteBuf.internalNioBuffer(n8, byteBuf.readableBytes());
                while ((n7 = Native.write(this.fd, byteBuffer, n6 = byteBuffer.position(), n5 = byteBuffer.limit())) > 0) {
                    byteBuffer.position(n6 + n7);
                    if ((l += (long)n7) != (long)n) continue;
                    bl = true;
                    break block7;
                }
                this.setEpollOut();
            }
            channelOutboundBuffer.removeBytes(l);
            return bl;
        }
        ByteBuffer[] byteBufferArray = byteBuf.nioBuffers();
        return this.writeBytesMultiple(channelOutboundBuffer, byteBufferArray, byteBufferArray.length, n);
    }

    public boolean doWriteSingle(ChannelOutboundBuffer channelOutboundBuffer) {
        Object object = channelOutboundBuffer.current();
        if (object instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf)object;
            if (!this.writeBytes(channelOutboundBuffer, byteBuf)) {
                return false;
            }
        } else if (object instanceof DefaultFileRegion) {
            DefaultFileRegion defaultFileRegion = (DefaultFileRegion)object;
            if (!this.writeFileRegion(channelOutboundBuffer, defaultFileRegion)) {
                return false;
            }
        } else {
            throw new Error();
        }
        return true;
    }

    @Override
    public ChannelFuture shutdownOutput(ChannelPromise channelPromise) {
        EventLoop eventLoop = this.eventLoop();
        if (eventLoop.inEventLoop()) {
            try {
                Native.shutdown(this.fd, false, true);
                this.outputShutdown = true;
                channelPromise.setSuccess();
            }
            catch (Throwable throwable) {
                channelPromise.setFailure(throwable);
            }
        } else {
            eventLoop.execute(new EpollSocketChannel$1(this, channelPromise));
        }
        return channelPromise;
    }

    public static /* synthetic */ ScheduledFuture access$300(EpollSocketChannel epollSocketChannel) {
        return epollSocketChannel.connectTimeoutFuture;
    }
}

