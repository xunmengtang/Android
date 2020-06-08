package com.reaksmeyarun.pda.customView.BubbleTabBar

class AngleCoordinate {
        var x1 : Float = 0f
        var y1 : Float = 0f
        var x2 : Float = 0f
        var y2 : Float = 0f

        fun getAngleCoordinate(
            gradientAngle: GradientAngle?,
            width: Float,
            height: Float
        ): AngleCoordinate {
            val angleCoordinate = AngleCoordinate()
            when (gradientAngle) {
                GradientAngle.LEFT_TO_RIGHT -> angleCoordinate.x2 = width
                GradientAngle.RIGHT_TO_LEFT -> angleCoordinate.x1 = width
                GradientAngle.TOP_TO_BOTTOM -> angleCoordinate.y2 = height
                GradientAngle.BOTTOM_TO_TOP -> angleCoordinate.y1 = height
                GradientAngle.LEFT_TOP_TO_RIGHT_BOTTOM -> {
                    angleCoordinate.x2 = width
                    angleCoordinate.y2 = height
                }
                GradientAngle.RIGHT_BOTTOM_TO_LEFT_TOP -> {
                    angleCoordinate.x1 = width
                    angleCoordinate.y1 = height
                }
                GradientAngle.LEFT_BOTTOM_TO_RIGHT_TOP -> {
                    angleCoordinate.x2 = width
                    angleCoordinate.y1 = height
                }
                GradientAngle.RIGHT_TOP_TO_LEFT_BOTTOM -> {
                    angleCoordinate.x1 = width
                    angleCoordinate.y2 = height
                }
            }
            return angleCoordinate
        }
}