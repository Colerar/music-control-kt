# music-control-kt

Global hotkey daemon for macOS Music.app

This version works but has a lot of cons
compare to [`music-control-go`](https://github.com/Colerar/music-control-go):

- Requires JVM
  - but not so troublesome for me anyway
- High memory footprint: About ~20 MiB, compare to
  - [`music-control-go`](https://github.com/Colerar/music-control-go): ~5 MiB
  - [`music-control-rust`](https://github.com/Colerar/music-control-rust): ~2 MiB (but Rust version cannot works)
- Hotkey not being grabbed
  - In short, the “funky” sound will be played when pressing some hotkeys in certain applications

## Build

```bash
./gradlew shadowJar
```
