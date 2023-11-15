package com.tpcly.pathfinding

import kotlin.math.pow
import kotlin.math.sqrt

val point2dManhattanHeuristic: (from: Point2D, to: Point2D) -> Double = { from, to ->
    val deltaX = (from.x - to.x).toDouble().pow(2)
    val deltaY = (from.y - to.y).toDouble().pow(2)

    sqrt(deltaX + deltaY)
}

val point3dManhattanHeuristic: (from: Point3D, to: Point3D) -> Double = { from, to ->
    val deltaX = (from.x - to.x).toDouble().pow(2)
    val deltaY = (from.y - to.y).toDouble().pow(2)
    val deltaZ = (from.z - to.z).toDouble().pow(2)

    sqrt(deltaX + deltaY + deltaZ)
}