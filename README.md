# Dawn Cat Lovers

Android code assessment app for browsing cat breeds from [TheCatAPI](https://developers.thecatapi.com/).

## Problem And Solution

The app fetches cat breeds, shows one image per breed, supports Browse -> Details -> Filters, and remains useful offline after a successful sync. Room is the authoritative data source: screens observe local database flows, while network refreshes only update the local store.

## Architecture

- `:app`: application entry point, Hilt setup, Room/OkHttp/repository providers.
- `:core:model`: UI-independent domain models and filter matching.
- `:core:domain`: repository contracts and use cases that define feature-facing behavior.
- `:core:database`: Room entity, DAO, database, and database mappers.
- `:core:network`: TheCatAPI client using OkHttp and structured JSON parsing.
- `:core:data`: offline-first repository implementation that observes, refreshes, and preserves local favorite state.
- `:core:designsystem`: Compose theme, color tokens, typography, and shared styling.
- `:feature:breeds`: Browse, Details, Filters navigation, ViewModels, and Compose UI.

The module split is feature-first with shared core modules. Feature modules depend on domain contracts/use cases instead of data implementations, so new features can be added without coupling feature modules directly to each other or to storage/network details.

## Technical Choices

- Kotlin + Jetpack Compose + Material 3 for modern Android UI.
- Room for offline-first storage and observable local state.
- Hilt for dependency injection at the app and feature boundaries.
- Retrofit + Moshi for typed TheCatAPI calls and JSON parsing.
- OkHttp for HTTP caching beneath Retrofit.
- Coil for image loading and disk caching.
- Gradle version catalog and wrapper for repeatable builds.

## API Key

TheCatAPI key is read from `THE_CAT_API_KEY` in the environment, falling back to `THE_CAT_API_KEY` in `local.properties`. `local.properties` is ignored and must not be committed.

Client-side mobile keys are still extractable from built APKs. For production-grade secrecy, route TheCatAPI calls through a backend service and keep the key server-side.

## Tradeoffs And Improvements

- Filters are applied in repository memory because the breed dataset is small. If the dataset grew, filtering should move into indexed Room queries or FTS.
- The API key is optional because the assessment endpoint works without one. A production app should proxy requests through a backend when the key must remain secret.
- Favorites are local-only. A real account feature would sync them through a backend.
- Some visible UI affordances are presentational and not fully implemented yet, including share, compare, voice search, and add-filter actions. These should either be wired to real behavior or hidden before treating the app as production-ready.
