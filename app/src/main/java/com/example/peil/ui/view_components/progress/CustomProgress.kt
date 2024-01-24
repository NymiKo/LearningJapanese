package com.example.peil.ui.view_components.progress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CustomProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = ProgressIndicatorDefaults.circularColor,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    trackColor: Color = ProgressIndicatorDefaults.circularTrackColor,
    strokeCap: StrokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
    sizeCircularProgress: Dp = 100.dp
) {
    val coercedProgress = progress.coerceIn(0f, 1f)
    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Butt)
    }
    Canvas(
        modifier
            .progressSemantics(coercedProgress)
            .size(sizeCircularProgress)
    ) {
        // Start at 12 o'clock
        val startAngle = 140f
        val sweep = progress * 260f
        drawArc(
            color = trackColor,
            startAngle = startAngle,
            sweepAngle = 260f,
            style = stroke,
            useCenter = false,
            size = Size(size.width, size.height)
        )
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweep,
            style = stroke,
            useCenter = false,
            size = Size(size.width, size.height)
        )
    }
}