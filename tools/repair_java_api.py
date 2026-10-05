"""Repair erased generic API use and Java-only qualifier syntax."""
import pathlib,re
ROOT=pathlib.Path(__file__).resolve().parents[1];root=ROOT/'src/main/java'
for p in (root/'com/cheatbreaker').rglob('*.java'):
    s=p.read_text('utf-8')
    s=re.sub(r'((?:if|while)\s*\(\s*!?\s*)(this\.(?:\w+\.)*\w+\.getValue\(\))',r'\1(Boolean)\2',s)
    s=re.sub(r'(?<!\))\b(this\.(?:\w+\.)*\w+\.getValue\(\))(?=\s*(?:\?|&&|\|\|))',r'(Boolean)\1',s)
    s=re.sub(r'((?:&&|\|\|)\s*)(this\.(?:\w+\.)*\w+\.getValue\(\))',r'\1(Boolean)\2',s)
    p.write_text(s,'utf-8')
p=root/'net/minecraft/client/main/Main.java';s=p.read_text('utf-8');types={3:'Integer',4:'File',5:'File',6:'File',8:'Integer',15:'Integer',16:'Integer'}
s=re.sub(r'ArgumentAcceptingOptionSpec (var(\d+)) =',lambda m:'ArgumentAcceptingOptionSpec<'+types.get(int(m[2]),'String')+'> '+m[1]+' =',s);p.write_text(s,'utf-8')
for name in ['SocksCmdRequestDecoder','SocksCmdResponseDecoder']:
    p=root/('io/netty/handler/codec/socks/'+name+'.java');s=p.read_text('utf-8').replace('ReplayingDecoder<State>','ReplayingDecoder<'+name+'.State>');p.write_text(s,'utf-8')
p=root/'com/cheatbreaker/client/event/EventBus.java';s=p.read_text('utf-8').replace('.add(var2);','.add((Consumer<EventBus$Event>)(Consumer<?>)var2);').replace('CopyOnWriteArrayList var3 =','CopyOnWriteArrayList<Consumer<EventBus$Event>> var3 =');p.write_text(s,'utf-8')
# Infer variables whose initializer already has an unambiguous Java type.
changes=0
for p in root.rglob('*.java'):
    s=p.read_text('utf-8');old=s
    s=re.sub(r'\bObject (\w+) = ("(?:\\.|[^"\\])*"\s*;)',r'String \1 = \2',s)
    s=re.sub(r'\bObject (\w+) = new (\w+(?:\.\w+)*)(?=\()',r'\2 \1 = new \2',s)
    s=re.sub(r'\bClass<(?:EpollSocketChannel|OioSocketChannel|NioSocketChannel)> (\w+)',r'Class<? extends Channel> \1',s)
    s=s.replace('for (final int ', 'for (int ')
    if s!=old:changes+=1;p.write_text(s,'utf-8')
print('Restored generic API uses; local type inference files',changes)
