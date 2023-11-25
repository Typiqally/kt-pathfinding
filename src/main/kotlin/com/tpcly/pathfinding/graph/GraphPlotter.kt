package com.tpcly.pathfinding.graph

import com.tpcly.pathfinding.Path

interface GraphPlotter {
    fun <TPosition> find(
        graph: Graph<TPosition>,
        heuristic: (TPosition, TPosition) -> Double,
        start: TPosition,
        goal: TPosition
    ): Path<TPosition>?
}