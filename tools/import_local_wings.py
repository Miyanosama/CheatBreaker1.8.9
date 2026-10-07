"""Replace all bundled wings with static square textures and 64x64 thumbnails."""
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

PREFIXES = ('assets/minecraft/client/wings/', 'assets/minecraft/client/preview/wings/')


def digest(path):
    return hashlib.sha256(path.read_bytes()).hexdigest()


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('source', type=Path)
    source = parser.parse_args().source.resolve(strict=True)
    resources = ROOT / 'src/main/resources'
    stage = ROOT / '.target/wing-import'
    imported, excluded, seen = [], [], set()
    for file in sorted(source.glob('*.webp'), key=lambda p: p.name.lower()):
        thumbnail = source / 'thumbnail' / file.name
        with Image.open(file) as texture:
            if texture.width != texture.height or getattr(texture, 'n_frames', 1) != 1 or Path(str(file) + '.mcmeta').exists():
                excluded.append({'source': file.name, 'source_sha256': digest(file),
                                 'reason': 'Not a static square texture (animation metadata or frame strip)',
                                 'width': texture.width, 'height': texture.height})
                continue
            if not thumbnail.is_file():
                raise ValueError('Missing thumbnail: ' + file.name)
            name = file.stem.lower() + '.png'
            if name in seen:
                raise ValueError('Duplicate wing name: ' + name)
            seen.add(name)
            entry = {'source': file.name, 'files': []}
            with Image.open(thumbnail) as preview:
                if preview.size != (64, 64) or getattr(preview, 'n_frames', 1) != 1:
                    raise ValueError('Expected static 64x64 thumbnail: ' + file.name)
                for image, original, prefix in zip((texture, preview), (file, thumbnail), PREFIXES):
                    path = prefix + 'imported/' + name
                    target = stage / path
                    target.parent.mkdir(parents=True, exist_ok=True)
                    converted = image.convert('RGBA')
                    converted.save(target, format='PNG')
                    with Image.open(target) as saved:
                        assert saved.size == image.size
                        assert saved.convert('RGBA').tobytes() == converted.tobytes()
                    entry['files'].append({'path': path, 'sha256': digest(target),
                                           'source_sha256': digest(original),
                                           'width': image.width, 'height': image.height})
            imported.append(entry)
    if not imported:
        raise ValueError('No compatible source wings')
    with zipfile.ZipFile(ROOT / '.target/input/preview.jar') as archive:
        removed = sorted(n for n in archive.namelist() if n.startswith(PREFIXES) and not n.endswith('/'))
    # Validate every conversion before replacing the explicitly scoped resources.
    for prefix in PREFIXES:
        directory = (resources / prefix).resolve()
        assert directory.is_relative_to(resources.resolve())
        for file in directory.rglob('*'):
            if file.is_file():
                assert file.resolve().is_relative_to(directory)
                file.unlink()
    for entry in imported:
        for asset in entry['files']:
            target = resources / asset['path']
            target.parent.mkdir(parents=True, exist_ok=True)
            shutil.copy2(stage / asset['path'], target)
    catalog = resources / 'assets/minecraft/client/local-cosmetics.txt'
    retained = [line for line in catalog.read_text('utf-8').splitlines() if line and not line.startswith('client/wings/')]
    wings = [entry['files'][0]['path'].removeprefix('assets/minecraft/') for entry in imported]
    catalog.write_text('\n'.join(retained + wings) + '\n', encoding='utf-8')
    report = {'format': 'Static square RGBA PNG texture, static 64x64 RGBA PNG thumbnail; lossless conversion',
              'source_directory': str(source), 'removed_original': removed,
              'imported_count': len(imported), 'excluded_count': len(excluded),
              'imported': imported, 'excluded': excluded}
    (ROOT / 'recovery/wing-resources.json').write_text(json.dumps(report, indent=2, ensure_ascii=False) + '\n', encoding='utf-8')
    print('Imported %d wings with thumbnails; excluded %d incompatible textures.' % (len(imported), len(excluded)))


if __name__ == '__main__':
    main()
