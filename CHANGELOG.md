# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

## [0.6.0]

### Added

- All missing ios, watchos, tvos and macos simulator targets added
- Added `androidNativeX64` and `androidNativeX86` targets
- Added proper android release and debug variants instead of piggybacking on jvm artefact
- New and improved `typedReducer` and `createTypedStore` builders for those needing a simple
  action-typed store.
  Recommended to use with sealed interface hierarchies.

### Changed

- Major gradle infra rework
- Enabled `explicitPublicApi()`
- **BREAKING**: `redux-kotlin-thunk` APIs moved to a new package: `org.reduxkotlin.thunk`

### Removed

- Remove deprecated `wasm32` target

---

## [0.5.4] - 2020-08-16

- kotlin 1.4.0 final
- change test.yml to just run a build (currently no tests)
- update to redux-kotlin 0.5.5

---

[Unreleased]: https://github.com/reduxkotlin/redux-kotlin-thunk/compare/0.6.0...HEAD

[0.6.0]: https://github.com/reduxkotlin/redux-kotlin-thunk/compare/v0.5.5...0.6.0

[0.5.5]: https://github.com/reduxkotlin/redux-kotlin-thunk/compare/v0.5.4...v0.5.5

[0.5.4]: https://github.com/reduxkotlin/redux-kotlin-thunk/releases/tag/v0.5.4
