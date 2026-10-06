package recovery;

import com.cheatbreaker.recovery.ExceptionRethrow;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import junit.framework.TestCase;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.json.JSONObject;
import org.json.JSONString;

public class RestorationBehaviorTest extends TestCase {
    public void testGzipDecoderStateTransitions() throws Exception {
        byte[] payload = new byte[20000];
        for (int i = 0; i < payload.length; i++) payload[i] = (byte) (i * 37);
        java.io.ByteArrayOutputStream bytes = new java.io.ByteArrayOutputStream();
        java.util.zip.GZIPOutputStream gzip = new java.util.zip.GZIPOutputStream(bytes);
        gzip.write(payload);
        gzip.close();
        byte[] encoded = bytes.toByteArray();
        io.netty.channel.embedded.EmbeddedChannel channel = new io.netty.channel.embedded.EmbeddedChannel(
                new io.netty.handler.codec.compression.JdkZlibDecoder(io.netty.handler.codec.compression.ZlibWrapper.GZIP));
        java.io.ByteArrayOutputStream decoded = new java.io.ByteArrayOutputStream();
        try {
            // Header arrives one byte at a time; body and footer arrive in chunks.
            int position = 0;
            while (position < encoded.length) {
                int count = Math.min(position < 12 ? 1 : 17, encoded.length - position);
                channel.writeInbound(Unpooled.wrappedBuffer(encoded, position, count));
                position += count;
            }
            channel.finish();
            io.netty.buffer.ByteBuf part;
            while ((part = (io.netty.buffer.ByteBuf) channel.readInbound()) != null) {
                try {
                    byte[] data = new byte[part.readableBytes()];
                    part.readBytes(data);
                    decoded.write(data);
                } finally { part.release(); }
            }
            assertTrue(java.util.Arrays.equals(payload, decoded.toByteArray()));
        } finally {
            channel.finish();
            Object pending;
            while ((pending = channel.readInbound()) != null) io.netty.util.ReferenceCountUtil.release(pending);
            while ((pending = channel.readOutbound()) != null) io.netty.util.ReferenceCountUtil.release(pending);
        }
        assertEquals("Upgrade", io.netty.handler.codec.http.HttpHeaders.Names.UPGRADE);
        assertEquals("Upgrade", io.netty.handler.codec.http.HttpHeaders.Values.UPGRADE);
        assertEquals("Public", io.netty.handler.codec.rtsp.RtspHeaders.Names.PUBLIC);
        assertEquals("public", io.netty.handler.codec.rtsp.RtspHeaders.Values.PUBLIC);
    }

    public void testRiffSampleWriteBeyondByteRange() throws Exception {
        java.nio.file.Path directory = java.nio.file.Paths.get(".target/test-data");
        java.nio.file.Files.createDirectories(directory);
        java.nio.file.Path file = java.nio.file.Files.createTempFile(
                directory, "recovery-riff-", ".wav");
        javazoom.jl.converter.RiffFile riff = new javazoom.jl.converter.RiffFile();
        try {
            assertEquals(0, riff.Open(file.toString(), 1));
            long start = riff.file.getFilePointer();
            short[] samples = new short[150];
            for (int i = 0; i < samples.length; i++) samples[i] = (short) (0x1200 + i);
            assertEquals(0, riff.Write(samples, 300));
            assertEquals(start + 300, riff.file.getFilePointer());
            riff.file.seek(start + 298);
            assertEquals(0x95, riff.file.readUnsignedByte());
            assertEquals(0x12, riff.file.readUnsignedByte());
        } finally {
            riff.Close();
            java.nio.file.Files.deleteIfExists(file);
        }
    }

    public void testServerRuleWireProtocol() throws Exception {
        com.cheatbreaker.client.nethandler.obj.ServerRule[] rules =
                com.cheatbreaker.client.nethandler.obj.ServerRule.values();
        String[] names = {"voiceEnabled", "minimapStatus", "serverHandlesWaypoints",
                "competitiveGame", "legacyEnchanting", "legacyCombat"};
        assertEquals(names.length, rules.length);
        assertEquals(Integer.valueOf(10), com.cheatbreaker.client.nethandler.Packet.recoveredField434.get(
                com.cheatbreaker.client.nethandler.server.PacketServerRule.class));
        for (int i = 0; i < rules.length; i++) {
            assertEquals(names[i], rules[i].getRuleName());
            com.cheatbreaker.client.nethandler.server.PacketServerRule sent =
                    new com.cheatbreaker.client.nethandler.server.PacketServerRule(rules[i], true);
            sent.recoveredField345 = 123456;
            sent.recoveredField342 = 1.25F;
            sent.recoveredField344 = "NEUTRAL";
            io.netty.buffer.ByteBuf bytes = Unpooled.buffer();
            try {
                com.cheatbreaker.client.nethandler.ByteBufWrapper wire =
                        new com.cheatbreaker.client.nethandler.ByteBufWrapper(bytes);
                sent.write(wire);
                com.cheatbreaker.client.nethandler.server.PacketServerRule received =
                        new com.cheatbreaker.client.nethandler.server.PacketServerRule();
                received.read(wire);
                assertSame(rules[i], received.method_13129());
                assertTrue(received.method_13131());
                assertEquals(123456, received.method_13128());
                assertEquals(1.25F, received.method_13127(), 0.0F);
                assertEquals("NEUTRAL", received.method_13130());
                assertEquals(0, bytes.readableBytes());
            } finally { bytes.release(); }
        }
    }

    public void testTearDownFailurePropagation() throws Throwable {
        final RuntimeException cleanupFailure = new RuntimeException("cleanup");
        TestCase cleanup = new TestCase() {
            public void runTest() {}
            public void tearDown() { throw cleanupFailure; }
        };
        try { cleanup.runBare(); fail("Cleanup failure was discarded"); }
        catch (Throwable failure) { assertSame(cleanupFailure, failure); }
        final RuntimeException bodyFailure = new RuntimeException("body");
        TestCase both = new TestCase() {
            public void runTest() { throw bodyFailure; }
            public void tearDown() { throw cleanupFailure; }
        };
        try { both.runBare(); fail("Test failure was discarded"); }
        catch (Throwable failure) { assertSame(bodyFailure, failure); }
    }

    public void testIntegerCountersBeyondByteRange() {
        assertEquals(10, net.optifine.util.TextureUtils.getPowerOfTwo(1024));
        assertEquals(1024, net.optifine.util.TextureUtils.twoToPower(10));
        assertEquals(1024, net.optifine.util.TextureUtils.ceilPowerOfTwo(513));
        java.awt.image.BufferedImage cape = net.optifine.player.CapeUtils.parseCape(
                new java.awt.image.BufferedImage(129, 65, java.awt.image.BufferedImage.TYPE_INT_ARGB));
        assertEquals(256, cape.getWidth());
        assertEquals(128, cape.getHeight());
        io.netty.channel.RecvByteBufAllocator.Handle handle =
                new io.netty.channel.AdaptiveRecvByteBufAllocator().newHandle();
        assertEquals(1024, handle.guess());
        handle.record(1024);
        assertEquals(16384, handle.guess());
        byte[] data = new byte[300];
        for (int i = 0; i < data.length; i++) data[i] = (byte) i;
        io.netty.buffer.ByteBuf buffer = Unpooled.wrappedBuffer(data);
        try {
            String hex = io.netty.buffer.ByteBufUtil.hexDump(buffer);
            assertEquals(600, hex.length());
            assertEquals("00010203", hex.substring(0, 8));
            assertEquals("2a2b", hex.substring(596));
        } finally {
            buffer.release();
        }
    }

    public void testFacingOrdinalsAndLookup() {
        EnumFacing[] expected = {EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH,
                EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.EAST};
        EnumFacing[] actual = EnumFacing.values();
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertSame(expected[i], actual[i]);
            assertEquals(i, actual[i].ordinal());
            assertSame(expected[i], EnumFacing.getFront(i));
        }
        assertSame(EnumFacing.EAST, EnumFacing.NORTH.rotateY());
    }

    public void testVarIntBoundaryRoundTrips() {
        int[] values = {0, 1, 127, 128, 16383, 16384, Integer.MAX_VALUE,
                -1, Integer.MIN_VALUE};
        PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
        try {
            for (int value : values) {
                buffer.clear();
                buffer.writeVarIntToBuffer(value);
                assertTrue(buffer.readableBytes() <= 5);
                assertEquals(value, buffer.readVarIntFromBuffer());
                assertEquals(0, buffer.readableBytes());
            }
            buffer.clear();
            buffer.writeString("CheatBreaker \u4e2d\u6587");
            assertEquals("CheatBreaker \u4e2d\u6587", buffer.readStringFromBuffer(64));
        } finally {
            buffer.release();
        }
    }

    public void testCollisionRayAndMiss() {
        AxisAlignedBB box = new AxisAlignedBB(0, 0, 0, 1, 1, 1);
        MovingObjectPosition hit = box.calculateIntercept(new Vec3(-1, .5, .5),
                new Vec3(2, .5, .5));
        assertNotNull(hit);
        assertSame(EnumFacing.WEST, hit.sideHit);
        assertEquals(0.0, hit.hitVec.xCoord, 0.0);
        assertEquals(.5, hit.hitVec.yCoord, 0.0);
        assertNull(box.calculateIntercept(new Vec3(-1, 2, .5), new Vec3(2, 2, .5)));
        assertTrue(box.intersectsWith(new AxisAlignedBB(.5, .5, .5, 2, 2, 2)));
        assertFalse(box.intersectsWith(new AxisAlignedBB(1, 0, 0, 2, 1, 1)));
    }

    public void testExceptionRethrowPreservesInstance() {
        IOException original = new IOException("original");
        try {
            ExceptionRethrow.<RuntimeException>rethrow(original);
            fail("Expected the original exception");
        } catch (Throwable thrown) {
            assertSame(original, thrown);
        }
    }

    public void testJsonEntrySerializationAndCustomString() {
        JSONObject object = new JSONObject();
        object.put("name", "CheatBreaker");
        object.put("nested", new JSONObject().put("enabled", true));
        String encoded = object.toString();
        JSONObject decoded = new JSONObject(encoded);
        assertEquals("CheatBreaker", decoded.getString("name"));
        assertTrue(decoded.getJSONObject("nested").getBoolean("enabled"));
        assertEquals(2, decoded.keySet().size());
        object.put("custom", new JSONString() {
            public String toJSONString() {
                return "{\"restored\":true}";
            }
        });
        assertTrue(new JSONObject(object.toString()).getJSONObject("custom").getBoolean("restored"));
    }

    public void testResourceLocationDefaults() {
        ResourceLocation location = new ResourceLocation("textures/gui/icons.png");
        assertEquals("minecraft", location.getResourceDomain());
        assertEquals("textures/gui/icons.png", location.getResourcePath());
    }
}
