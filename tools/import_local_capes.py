"""Replace bundled capes, retaining cb/cb2 and converting a source folder to PNG.

Requires Pillow (it may be installed in .target/image-tools).
"""
import argparse
import hashlib
import json
from pathlib import Path
import shutil
import sys
import zipfile

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / '.target/image-tools'))
from PIL import Image


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('source', type=Path)
    args = parser.parse_args()
    source = args.source.resolve(strict=True)
    resources = ROOT / 'src/main/resources'
    cape_root = resources / 'assets/minecraft/client/capes'
    prefix = 'assets/minecraft/client/capes/'
    protected = {prefix + 'cb.png', prefix + 'cb2.png'}
    stage = ROOT / '.target/cape-import'
    stage.mkdir(parents=True, exist_ok=True)
    imported = []
    seen = set()
    for file in sorted(source.iterdir(), key=lambda p: p.name.lower()):
        if not file.is_file():
            continue
        name = file.stem.lower() + '.png'
        if name in seen:
            raise ValueError('Duplicate cape name: ' + name)
        seen.add(name)
        with Image.open(file) as image:
            if image.width != 2 * image.height or getattr(image, 'n_frames', 1) != 1:
                raise ValueError('Expected a static 2:1 cape: ' + file.name)
            converted = image.convert('RGBA')
            converted.save(stage / name, format='PNG')
            # Verify lossless conversion, including transparency.
            with Image.open(stage / name) as saved:
                assert saved.size == image.size
                assert saved.convert('RGBA').tobytes() == converted.tobytes()
            imported.append({'source': file.name, 'source_sha256': digest(file),
                             'path': prefix + 'imported/' + name,
                             'sha256': digest(stage / name),
                             'width': image.width, 'height': image.height})
    if not imported:
        raise ValueError('No source capes')
    with zipfile.ZipFile(ROOT / '.target/input/preview.jar') as original:
        removed = sorted(n for n in original.namelist()
                         if n.startswith(prefix) and not n.endswith('/') and n not in protected)
        for path in protected:
            assert (resources / path).read_bytes() == original.read(path), path
    # Only remove files underneath the explicitly scoped resource directory.
    for file in cape_root.rglob('*'):
        if file.is_file() and file.relative_to(resources).as_posix() not in protected:
            assert file.resolve().is_relative_to(cape_root.resolve())
            file.unlink()
    destination = cape_root / 'imported'
    destination.mkdir(parents=True, exist_ok=True)
    for entry in imported:
        shutil.copy2(stage / Path(entry['path']).name, resources / entry['path'])
    catalog = resources / 'assets/minecraft/client/local-cosmetics.txt'
    wings = [line for line in catalog.read_text('utf-8').splitlines()
             if line.startswith('client/wings/')]
    capes = ['client/capes/cb.png', 'client/capes/cb2.png']
    capes += [entry['path'].removeprefix('assets/minecraft/') for entry in imported]
    catalog.write_text('\n'.join(capes + wings) + '\n', encoding='utf-8')
    manifest = {'preserved': sorted(protected), 'removed_original': removed, 'imported': imported}
    (ROOT / 'recovery/cape-resources.json').write_text(
        json.dumps(manifest, indent=2, ensure_ascii=False) + '\n', encoding='utf-8')
    print('Imported %d capes; retained 2 CB capes and %d wings.' % (len(imported), len(wings)))


def digest(file):
    return hashlib.sha256(file.read_bytes()).hexdigest()


if __name__ == '__main__':
    main()
