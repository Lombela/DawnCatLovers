package com.dawn.catlovers.feature.breeds

import com.dawn.catlovers.core.domain.CoroutineDispatchers

internal fun MainDispatcherRule.testDispatchers(): CoroutineDispatchers =
    CoroutineDispatchers(
        io = dispatcher,
        default = dispatcher,
    )
