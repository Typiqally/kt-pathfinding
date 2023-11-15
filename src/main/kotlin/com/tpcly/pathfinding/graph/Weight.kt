package com.tpcly.pathfinding.graph

data class Weight(
    val cost: Double,
    val heuristic: Double,
) {
    val total = cost + heuristic
}