"""Explicit, reviewable Java source corrections against recovered bytecode."""
import pathlib,re,json
ROOT=pathlib.Path(__file__).resolve().parents[1];S=ROOT/'src/main/java'
def edit(name,*pairs):
    p=S/name;s=p.read_text('utf-8')
    for a,b in pairs:s=s.replace(a,b)
    p.write_text(s,'utf-8')
edit('com/cheatbreaker/client/CheatBreaker.java',('new URI("ws://dev.moose1301.cf/connect")','URI.create("ws://dev.moose1301.cf/connect")'))
edit('com/cheatbreaker/client/config/ConfigManager.java',('var50.stream().map(Integer::parseInt)','((List<String>)var50).stream().map(Integer::parseInt)'),('var12.stream().map(Integer::parseInt)','((List<String>)var12).stream().map(Integer::parseInt)'))
edit('com/cheatbreaker/client/config/GlobalSettings.java',('values()[this.crosshairSettingsLabel.getValue()]','values()[(Integer)this.crosshairSettingsLabel.getValue()]'))
edit('com/cheatbreaker/client/nethandler/server/PacketUpdateNametags.java',('var5.forEach(var1::writeString)','((List<String>)var5).forEach(var1::writeString)'))
edit('net/minecraft/client/gui/GuiIngame.java',('Object var2 = this.highlightingItemStack.getDisplayName()','String var2 = this.highlightingItemStack.getDisplayName()'))
edit('net/minecraft/client/resources/data/IMetadataSerializer.java',('return this.getGson().fromJson','return (T)this.getGson().fromJson'))
edit('net/minecraft/world/World.java',('var2.apply(var5)','var2.apply((T)var5)'),('AxisAlignedBB var2, T var3)','AxisAlignedBB var2, Entity var3)'))
edit('net/minecraft/client/renderer/entity/RenderManager.java',('getEntityClassRenderObject(var1.getSuperclass())','getEntityClassRenderObject((Class)var1.getSuperclass())'),('return var3 != null ? var3 : this.playerRenderer;','return (Render<T>)(Render<?>)(var3 != null ? var3 : this.playerRenderer);'))
edit('net/minecraft/client/renderer/tileentity/TileEntityRendererDispatcher.java',('getSpecialRendererByClass(var1.getSuperclass())','getSpecialRendererByClass((Class)var1.getSuperclass())'))
edit('net/minecraft/client/renderer/texture/TextureMap.java',('Lists.newArrayList(var3)','Lists.<int[][]>newArrayList(new int[][][]{var3})'))
edit('recovered/unidentified/UnidentifiedClass4913.java',('((List)var4.getValue())','((List<String>)var4.getValue())'))
edit('com/cheatbreaker/client/module/type/ChatModule.java',('* this.recoveredField835.getValue()','* (Float)this.recoveredField835.getValue()'))
edit('org/json/JSONObject.java',('new UnidentifiedClass0968(null)','new UnidentifiedClass0968()'))
for name,field in [('IntHashMap','valueEntry'),('LongHashMap','value')]:
    p=S/('net/minecraft/util/'+name+'.java');s=p.read_text('utf-8');s=re.sub(r'(return\s+|\? null : )(var\d+\.'+field+r')(?=;)',r'\1(V)\2',s);p.write_text(s,'utf-8')
edit('net/minecraft/client/renderer/entity/RendererLivingEntity.java',('return this.h.add(var1);','return ((List)this.h).add(var1);'))
edit('net/minecraft/world/WorldServer.java',('? WeightedRandom.getRandomItem(this.s, var3)', '? (BiomeGenBase.SpawnListEntry)WeightedRandom.getRandomItem(this.s, var3)'))
edit('net/minecraft/network/NetworkSystem.java',('Class<EpollServerSocketChannel> var4','Class<? extends Channel> var4'))
edit('net/minecraft/world/chunk/Chunk.java',('var4.apply(var9)','var4.apply((T)var9)'),('var3.add(var9);','var3.add((T)var9);'))
edit('net/minecraft/world/SpawnerAnimals.java',('= WeightedRandom.getRandomItem(var0.s, var7)','= (BiomeGenBase.SpawnListEntry)WeightedRandom.getRandomItem(var0.s, var7)'),('java.util.Set var49 =','java.util.Collection<ChunkCoordIntPair> var49 ='))
edit('net/minecraft/world/gen/layer/GenLayer.java',('GenLayerHills var26 =','GenLayer var26 ='))
edit('com/cheatbreaker/client/ui/mainmenu/AccountLoginButton.java',('for (Session var2 : CheatBreaker.getInstance().method_19770())','for (com.cheatbreaker.client.util.SessionServer var2 : CheatBreaker.getInstance().method_19770())'))
edit('net/optifine/util/LinkedList.java',('public class LinkedList<T> {','public class LinkedList<T> implements Iterable<LinkedList.Node<T>> {'))
edit('io/netty/util/internal/ThreadLocalRandom.java',('   @Override\n',''))
edit('com/cheatbreaker/client/ui/overlay/VoiceChatGui.java',('ArrayList var8 = Lists.newArrayList(this.voiceChannel.getUsers())','ArrayList<VoiceUser> var8 = Lists.newArrayList(this.voiceChannel.getUsers())'))
for name in ['UnidentifiedClass0557','UnidentifiedClass1449','UnidentifiedClass1222','UnidentifiedClass4330','UnidentifiedClass4676']:
    p=S/('recovered/unidentified/'+name+'.java');s=p.read_text('utf-8')
    s=s.replace('this, this, var2, null','this, this, var2').replace('this, var2, null','this, var2').replace('method_08278(Iterable.class, var1), null','method_08278(Iterable.class, var1)').replace('new Cartesian_GetList<>(null)','new Cartesian_GetList<>()').replace('this.recoveredField3932, this.recoveredField3933, null','this.recoveredField3932, this.recoveredField3933').replace('new UnidentifiedClass1590(null)','new UnidentifiedClass1590()').replace('(Iterator<T[]>)(', '(Iterator<T[]>)(Iterator<?>)(')
    p.write_text(s,'utf-8')
edit('com/cheatbreaker/client/util/dash/DashPlayer.java',('RuntimeException var1 =','Throwable var1 ='))
edit('io/netty/handler/codec/DelimiterBasedFrameDecoder.java',('var3, var4.slice(var4.readerIndex(), var4.readableBytes())','var3, new ByteBuf[]{var4.slice(var4.readerIndex(), var4.readableBytes())}'))
edit('io/netty/handler/codec/MessageToMessageCodec.java',('this.encode(var1, var2, var3)','this.encode(var1, (OUTBOUND_IN)var2, var3)'),('this.decode(var1, var2, var3)','this.decode(var1, (INBOUND_IN)var2, var3)'))
edit('io/netty/handler/codec/marshalling/CompatibleMarshallingDecoder.java',('ChannelBufferByteInput var5 =','ByteInput var5 ='))
edit('io/netty/handler/codec/serialization/CompatibleObjectEncoder.java',('= var4.setIfAbsent(var5)','= (ObjectOutputStream)var4.setIfAbsent(var5)'))
edit('io/netty/util/concurrent/GlobalEventExecutor.java',('public static final boolean $assertionsDisabled','public final boolean $assertionsDisabled'))
edit('junit/framework/Assert.java',('assertEquals(var0, var1, Boolean.valueOf(var2))','assertEquals(var0, (Object)var1, (Object)Boolean.valueOf(var2))'))
edit('junit/runner/TestCaseClassLoader.java',('B var2 = null','byte[] var2 = null'))
edit('net/minecraft/block/BlockChest.java',('TileEntityChest var4 =','ILockableContainer var4 ='))
edit('net/minecraft/block/state/pattern/BlockStateHelper.java',('(IProperty<Comparable>)','(IProperty)'))
edit('net/minecraft/client/renderer/block/statemap/StateMap.java',('(IProperty<Object>)','(IProperty)'))
edit('net/minecraft/client/renderer/tileentity/TileEntityBannerRenderer.java',('new TileEntityBannerRenderer.TimedBannerTexture(null)','new TileEntityBannerRenderer.TimedBannerTexture()'))
edit('net/minecraft/command/CommandBase.java',('ChatComponentText var6 =','IChatComponent var6 ='))
edit('net/minecraft/command/CommandShowSeed.java',('net.minecraft.world.WorldServer var3 =','net.minecraft.world.World var3 ='))
edit('net/minecraft/command/PlayerSelector.java',('Lists.newArrayList(var7)','Lists.newArrayList((Iterable)var7)'),('final String var2','String var2'),('final String var3','String var3'))
edit('net/minecraft/enchantment/EnchantmentHelper.java',('= WeightedRandom.getRandomItem','= (EnchantmentData)WeightedRandom.getRandomItem'))
edit('net/minecraft/item/ItemSlab.java',('(IProperty<Comparable>)','(IProperty)'),('withProperty(this.singleSlab.getVariantProperty(), var6)','withProperty((IProperty)this.singleSlab.getVariantProperty(), var6)'))
edit('net/minecraft/util/MapPopulator.java',('var2.put(var5, var3.next())','var2.put((K)var5, (V)var3.next())'))
edit('net/optifine/CustomColors.java',('CustomColormap var5 =','CustomColors.IColorizer var5 ='),('var4.keySet().toArray(new String[var4.size()])','(String[])var4.keySet().toArray(new String[var4.size()])'))
edit('net/optifine/util/NativeMemory.java',('final Object var3','Object var3'),('final Method var2','Method var2'))
edit('org/apache/log4j/lf5/LogLevel.java',('? _registeredLogLevelMap.put','? (LogLevel)_registeredLogLevelMap.put'))
edit('recovered/unidentified/UnidentifiedClass1187.java',('((List)this.setting.getValue())','((List<Integer>)this.setting.getValue())'))
# Field renamed from obfuscated "b_" in EntityLiving; IMob has a different field of that name.
files=['entity/EntityLiving','entity/monster/EntityMob','entity/monster/EntityGuardian','entity/monster/EntityBlaze','entity/monster/EntityEndermite','entity/monster/EntityGhast','entity/monster/EntitySlime','entity/monster/EntityZombie','entity/boss/EntityWither']
for f in files:
    p=S/('net/minecraft/'+f+'.java');s=p.read_text('utf-8');s=re.sub(r'\bb_\b(?!\s*\()', 'experienceValue',s);p.write_text(s,'utf-8')
# The superclass already implements the interface's world accessor.
for p in S.rglob('*.java'):
    s=p.read_text('utf-8');old=s;s=s.replace('.z()','.getWorld()')
    if p.name=='TileEntity.java':s=s.replace('World z()', 'World getWorld()')
    if s!=old:p.write_text(s,'utf-8')
# Captured fields precede Object's constructor in bytecode. Parameterized superclass
# calls must be first in standalone source; captured fields are not read by these supers.
for f in ['UnidentifiedClass3249','UnidentifiedClass4511']:
    p=S/('recovered/unidentified/'+f+'.java');s=p.read_text('utf-8');s=re.sub(r'(public '+f+r'\([^{}]*\)\s*\{)\s*(this\.[^;]+;)\s*(super\([^;]+;)',r'\1\n      \3\n      \2',s);p.write_text(s,'utf-8')
# These accessors were absorbed into their parent decompilation; use the same field.
for f in ['UnidentifiedClass3777','UnidentifiedClass4880']:
    p=S/('recovered/unidentified/'+f+'.java');s=p.read_text('utf-8');s=re.sub(r'NetHandlerPlayClient.access\$000\((this\.\w+)\)',r'\1.netManager',s);p.write_text(s,'utf-8')
print('Applied bytecode-guided Java compatibility repairs')
