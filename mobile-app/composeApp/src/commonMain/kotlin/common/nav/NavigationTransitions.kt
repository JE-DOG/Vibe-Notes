package common.nav

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.navigation3.scene.Scene
import androidx.navigationevent.NavigationEvent

private const val DEFAULT_TRANSITION_DURATION_MILLISECOND = 500
private val IosTransitionEasing = CubicBezierEasing(0.2833f, 0.99f, 0.31833f, 0.99f)

fun <T : Any> appTransitionSpec():
        AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform = {
    ContentTransform(
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(
                DEFAULT_TRANSITION_DURATION_MILLISECOND,
                easing = IosTransitionEasing,
            ),
        ),
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
            targetOffset = { it / 4 },
            animationSpec = tween(
                DEFAULT_TRANSITION_DURATION_MILLISECOND,
                easing = IosTransitionEasing,
            ),
        ),
    )
}

fun <T : Any> appPopTransitionSpec():
        AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform = {
    ContentTransform(
        slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Right,
            initialOffset = { it / 4 },
            animationSpec = tween(
                DEFAULT_TRANSITION_DURATION_MILLISECOND,
                easing = IosTransitionEasing,
            ),
        ),
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Right,
            animationSpec = tween(
                DEFAULT_TRANSITION_DURATION_MILLISECOND,
                easing = IosTransitionEasing,
            ),
        ),
    )
}

fun <T : Any> appPredictivePopTransitionSpec():
        AnimatedContentTransitionScope<Scene<T>>.(@NavigationEvent.SwipeEdge Int) -> ContentTransform =
    { edge ->
        val towards = if (edge == NavigationEvent.EDGE_LEFT) {
            AnimatedContentTransitionScope.SlideDirection.Right
        } else {
            AnimatedContentTransitionScope.SlideDirection.Left
        }

        ContentTransform(
            slideIntoContainer(
                towards = towards,
                initialOffset = { it / 4 },
                animationSpec = tween(
                    durationMillis = DEFAULT_TRANSITION_DURATION_MILLISECOND,
                    easing = IosTransitionEasing,
                ),
            ),
            slideOutOfContainer(
                towards = towards,
                animationSpec = tween(
                    durationMillis = DEFAULT_TRANSITION_DURATION_MILLISECOND,
                    easing = IosTransitionEasing,
                ),
            ),
        )
    }
