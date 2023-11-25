package com.tpcly.pathfinding.graph

interface Graph<TPosition> {
    fun cost(from: TPosition, to: TPosition): Double

    fun neighbours(position: TPosition): List<TPosition>

    fun reachable(position: TPosition): Boolean
}