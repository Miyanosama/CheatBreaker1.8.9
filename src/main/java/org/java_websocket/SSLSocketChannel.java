/*
 * Decompiled with CFR 0.152.
 */
package org.java_websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import org.java_websocket.WrappedByteChannel;
import org.java_websocket.interfaces.ISSLChannel;
import org.java_websocket.util.ByteBufferUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSLSocketChannel
implements ISSLChannel,
ByteChannel,
WrappedByteChannel {
    public ByteBuffer peerNetData;
    public ByteBuffer myNetData;
    public ByteBuffer peerAppData;
    public ExecutorService executor;
    public SocketChannel socketChannel;
    public ByteBuffer myAppData;
    public SSLEngine engine;
    public Logger log = LoggerFactory.getLogger(SSLSocketChannel.class);

    @Override
    public synchronized int write(ByteBuffer byteBuffer) throws java.io.IOException {
        int n = 0;
        block6: while (byteBuffer.hasRemaining()) {
            this.myNetData.clear();
            SSLEngineResult sSLEngineResult = this.engine.wrap(byteBuffer, this.myNetData);
            switch (sSLEngineResult.getStatus()) {
                case OK: {
                    this.myNetData.flip();
                    while (this.myNetData.hasRemaining()) {
                        n += this.socketChannel.write(this.myNetData);
                    }
                    continue block6;
                }
                case BUFFER_OVERFLOW: {
                    this.myNetData = this.enlargePacketBuffer(this.myNetData);
                    continue block6;
                }
                case BUFFER_UNDERFLOW: {
                    throw new SSLException("Buffer underflow occured after a wrap. I don't think we should ever get here.");
                }
                case CLOSED: {
                    this.closeConnection();
                    return 0;
                }
            }
            throw new IllegalStateException("Invalid SSL status: " + (Object)((Object)sSLEngineResult.getStatus()));
        }
        return n;
    }

    @Override
    public void writeMore() throws java.io.IOException {
    }

    @Override
    public boolean isNeedRead() {
        return this.peerNetData.hasRemaining() || this.peerAppData.hasRemaining();
    }

    @Override
    public boolean isNeedWrite() {
        return false;
    }

    public void handleEndOfStream() throws java.io.IOException {
        try {
            this.engine.closeInbound();
        }
        catch (Exception exception) {
            this.log.error("This engine was forced to close inbound, without having received the proper SSL/TLS close notification message from the peer, due to end of stream.");
        }
        this.closeConnection();
    }

    public ByteBuffer enlargePacketBuffer(ByteBuffer byteBuffer) {
        return this.enlargeBuffer(byteBuffer, this.engine.getSession().getPacketBufferSize());
    }

    @Override
    public synchronized int read(ByteBuffer byteBuffer) throws java.io.IOException {
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (this.peerAppData.hasRemaining()) {
            this.peerAppData.flip();
            return ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
        }
        this.peerNetData.compact();
        int n = this.socketChannel.read(this.peerNetData);
        if (n > 0 || this.peerNetData.hasRemaining()) {
            this.peerNetData.flip();
            if (this.peerNetData.hasRemaining()) {
                SSLEngineResult sSLEngineResult;
                this.peerAppData.compact();
                try {
                    sSLEngineResult = this.engine.unwrap(this.peerNetData, this.peerAppData);
                }
                catch (SSLException sSLException) {
                    this.log.error("SSLExcpetion during unwrap", sSLException);
                    throw sSLException;
                }
                switch (sSLEngineResult.getStatus()) {
                    case OK: {
                        this.peerAppData.flip();
                        return ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
                    }
                    case BUFFER_UNDERFLOW: {
                        this.peerAppData.flip();
                        return ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
                    }
                    case BUFFER_OVERFLOW: {
                        this.peerAppData = this.enlargeApplicationBuffer(this.peerAppData);
                        return this.read(byteBuffer);
                    }
                    case CLOSED: {
                        this.closeConnection();
                        byteBuffer.clear();
                        return -1;
                    }
                }
                throw new IllegalStateException("Invalid SSL status: " + (Object)((Object)sSLEngineResult.getStatus()));
            }
        } else if (n < 0) {
            this.handleEndOfStream();
        }
        ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
        return n;
    }

    public boolean doHandshake() throws java.io.IOException {
        int n = this.engine.getSession().getApplicationBufferSize();
        this.myAppData = ByteBuffer.allocate(n);
        this.peerAppData = ByteBuffer.allocate(n);
        this.myNetData.clear();
        this.peerNetData.clear();
        SSLEngineResult.HandshakeStatus handshakeStatus = this.engine.getHandshakeStatus();
        boolean bl = false;
        block27: while (!bl) {
            switch (handshakeStatus) {
                case FINISHED: {
                    boolean bl2 = bl = !this.peerNetData.hasRemaining();
                    if (bl) {
                        return true;
                    }
                    this.socketChannel.write(this.peerNetData);
                    continue block27;
                }
                case NEED_UNWRAP: {
                    SSLEngineResult sSLEngineResult;
                    if (this.socketChannel.read(this.peerNetData) < 0) {
                        if (this.engine.isInboundDone() && this.engine.isOutboundDone()) {
                            return false;
                        }
                        try {
                            this.engine.closeInbound();
                        }
                        catch (SSLException sSLException) {
                            // empty catch block
                        }
                        this.engine.closeOutbound();
                        handshakeStatus = this.engine.getHandshakeStatus();
                        continue block27;
                    }
                    this.peerNetData.flip();
                    try {
                        sSLEngineResult = this.engine.unwrap(this.peerNetData, this.peerAppData);
                        this.peerNetData.compact();
                        handshakeStatus = sSLEngineResult.getHandshakeStatus();
                    }
                    catch (SSLException sSLException) {
                        this.engine.closeOutbound();
                        handshakeStatus = this.engine.getHandshakeStatus();
                        continue block27;
                    }
                    switch (sSLEngineResult.getStatus()) {
                        case OK: {
                            continue block27;
                        }
                        case BUFFER_OVERFLOW: {
                            this.peerAppData = this.enlargeApplicationBuffer(this.peerAppData);
                            continue block27;
                        }
                        case BUFFER_UNDERFLOW: {
                            this.peerNetData = this.handleBufferUnderflow(this.peerNetData);
                            continue block27;
                        }
                        case CLOSED: {
                            if (this.engine.isOutboundDone()) {
                                return false;
                            }
                            this.engine.closeOutbound();
                            handshakeStatus = this.engine.getHandshakeStatus();
                            continue block27;
                        }
                    }
                    throw new IllegalStateException("Invalid SSL status: " + (Object)((Object)sSLEngineResult.getStatus()));
                }
                case NEED_WRAP: {
                    SSLEngineResult sSLEngineResult;
                    this.myNetData.clear();
                    try {
                        sSLEngineResult = this.engine.wrap(this.myAppData, this.myNetData);
                        handshakeStatus = sSLEngineResult.getHandshakeStatus();
                    }
                    catch (SSLException sSLException) {
                        this.engine.closeOutbound();
                        handshakeStatus = this.engine.getHandshakeStatus();
                        continue block27;
                    }
                    switch (sSLEngineResult.getStatus()) {
                        case OK: {
                            this.myNetData.flip();
                            while (this.myNetData.hasRemaining()) {
                                this.socketChannel.write(this.myNetData);
                            }
                            continue block27;
                        }
                        case BUFFER_OVERFLOW: {
                            this.myNetData = this.enlargePacketBuffer(this.myNetData);
                            continue block27;
                        }
                        case BUFFER_UNDERFLOW: {
                            throw new SSLException("Buffer underflow occured after a wrap. I don't think we should ever get here.");
                        }
                        case CLOSED: {
                            try {
                                this.myNetData.flip();
                                while (this.myNetData.hasRemaining()) {
                                    this.socketChannel.write(this.myNetData);
                                }
                                this.peerNetData.clear();
                            }
                            catch (Exception exception) {
                                handshakeStatus = this.engine.getHandshakeStatus();
                            }
                            continue block27;
                        }
                    }
                    throw new IllegalStateException("Invalid SSL status: " + (Object)((Object)sSLEngineResult.getStatus()));
                }
                case NEED_TASK: {
                    Runnable runnable;
                    while ((runnable = this.engine.getDelegatedTask()) != null) {
                        this.executor.execute(runnable);
                    }
                    handshakeStatus = this.engine.getHandshakeStatus();
                    continue block27;
                }
                case NOT_HANDSHAKING: {
                    continue block27;
                }
            }
            throw new IllegalStateException("Invalid SSL status: " + (Object)((Object)handshakeStatus));
        }
        return true;
    }

    @Override
    public void close() throws java.io.IOException {
        this.closeConnection();
    }

    public ByteBuffer handleBufferUnderflow(ByteBuffer byteBuffer) {
        if (this.engine.getSession().getPacketBufferSize() < byteBuffer.limit()) {
            return byteBuffer;
        }
        ByteBuffer byteBuffer2 = this.enlargePacketBuffer(byteBuffer);
        byteBuffer.flip();
        byteBuffer2.put(byteBuffer);
        return byteBuffer2;
    }

    public SSLSocketChannel(SocketChannel socketChannel, SSLEngine sSLEngine, ExecutorService executorService, SelectionKey selectionKey) throws java.io.IOException {
        if (socketChannel == null || sSLEngine == null || this.executor == executorService) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.socketChannel = socketChannel;
        this.engine = sSLEngine;
        this.executor = executorService;
        this.myNetData = ByteBuffer.allocate(this.engine.getSession().getPacketBufferSize());
        this.peerNetData = ByteBuffer.allocate(this.engine.getSession().getPacketBufferSize());
        this.engine.beginHandshake();
        if (this.doHandshake()) {
            if (selectionKey != null) {
                selectionKey.interestOps(selectionKey.interestOps() | 4);
            }
        } else {
            try {
                this.socketChannel.close();
            }
            catch (IOException iOException) {
                this.log.error("Exception during the closing of the channel", iOException);
            }
        }
    }

    public ByteBuffer enlargeApplicationBuffer(ByteBuffer byteBuffer) {
        return this.enlargeBuffer(byteBuffer, this.engine.getSession().getApplicationBufferSize());
    }

    @Override
    public boolean isOpen() {
        return this.socketChannel.isOpen();
    }

    public ByteBuffer enlargeBuffer(ByteBuffer byteBuffer, int n) {
        byteBuffer = n > byteBuffer.capacity() ? ByteBuffer.allocate(n) : ByteBuffer.allocate(byteBuffer.capacity() * 2);
        return byteBuffer;
    }

    @Override
    public SSLEngine getSSLEngine() {
        return this.engine;
    }

    public void closeConnection() throws java.io.IOException {
        this.engine.closeOutbound();
        try {
            this.doHandshake();
        }
        catch (IOException iOException) {
            // empty catch block
        }
        this.socketChannel.close();
    }

    @Override
    public int readMore(ByteBuffer byteBuffer) throws java.io.IOException {
        return this.read(byteBuffer);
    }

    @Override
    public boolean isBlocking() {
        return this.socketChannel.isBlocking();
    }
}

