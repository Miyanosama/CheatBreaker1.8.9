# CheatBreaker 1.8.9

[![简体中文](https://img.shields.io/badge/%E7%AE%80%E4%BD%93%E4%B8%AD%E6%96%87-57606a?style=for-the-badge)](README.md) [![繁體中文](https://img.shields.io/badge/%E7%B9%81%E9%AB%94%E4%B8%AD%E6%96%87-57606a?style=for-the-badge)](README.zh-TW.md) [![日本語](https://img.shields.io/badge/%E6%97%A5%E6%9C%AC%E8%AA%9E-57606a?style=for-the-badge)](README.ja.md) [![English](https://img.shields.io/badge/English-0969da?style=for-the-badge)](README.en.md)

## About

CheatBreaker 1.8.9 is a client project for Minecraft Java Edition 1.8.9. It provides a CheatBreaker-style interface, configurable HUD elements and modules, and local cosmetics. The project aims to improve everyday gameplay through ongoing source maintenance, bug fixes, and interface improvements.

## Features

- Custom main menu, server list, and module settings, with adjustable client UI size.
- HUD and utility modules, including potion status, Hitboxes, and Hurtcam with adjustable camera shake when taking damage.
- Local capes and wings, with cosmetic search, selection, and saved preferences.
- Raw Mouse Input, FullBright, and improvements to input handling and UI responsiveness.
- Drag-to-reorder server cards and a reconnect feature.

## Usage

After launching the client, press **Right Shift** to open the modules and settings interface. Select cosmetics on the **COSMETICS** page in the main menu. Local cosmetics are rendered by the current client and are not automatically synchronized with other players.

## Building

You need **Java 8 JDK**, **Python 3**, and the build dependencies declared in `pom.xml`. Place the Minecraft launcher dependencies in `.target/launcher-libs/`; the file list is provided in `recovery/launcher-dependencies.json`. Run these commands from the project root on Windows:

```bat
mvnw.cmd clean package
python tools/package_client.py
```

The build runs compilation, tests, and audits. The resulting JAR is `.target/maven-source/CheatBreaker1.8.9.jar`, and the packaged client is written to `.target/client/CheatBreaker1.8.9/`.

## Contributing

Bug reports through Issues and improvements through Pull Requests are welcome. Include reproduction steps, expected behavior, and actual behavior. Remove account details, tokens, and personal paths before submitting logs. Source code is in `src/main/java/`, and resources are in `src/main/resources/`.

## Independence and License

This project is not affiliated with, sponsored by, endorsed by, or officially authorized by [Mojang AB (Mojang Studios)](https://www.minecraft.net/en-us/usage-guidelines), [OptiFine (author: sp614x)](https://optifine.net/copyright), [Microsoft Corporation](https://www.microsoft.com/), or [CheatBreaker®](https://github.com/CheatBreaker).

Original code contributed to this project is licensed under the [MIT License](LICENSE). Third-party code, assets, dependencies, and trademarks remain the property of their respective rights holders and remain subject to their existing licenses or terms of use.
