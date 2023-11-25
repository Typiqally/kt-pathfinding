package com.tpcly.pathfinding.graph

import com.tpcly.pathfinding.Path
import java.util.*

class AStarGraphPlotter : GraphPlotter {
    override fun <TPosition> find(
        graph: Graph<TPosition>,
        heuristic: (TPosition, TPosition) -> Double,
        starts: List<TPosition>,
        goals: List<TPosition>
    ): Path<TPosition>? {
        starts.forEach { start ->
            goals.forEach { goal ->
                val path = find(graph, heuristic, start, goal)
                if (path != null) {
                    return path
                }
            }
        }

        return null
    }

    override fun <TPosition> find(
        graph: Graph<TPosition>,
        heuristic: (from: TPosition, to: TPosition) -> Double,
        start: TPosition,
        goal: TPosition
    ): Path<TPosition>? {
        val startVertex = Vertex(start, Weight(0.0, 0.0))
        val goalVertex = Vertex(goal, Weight(0.0, 0.0))

        return find(graph, heuristic, startVertex, goalVertex)
    }

    private fun <TPosition> find(
        graph: Graph<TPosition>,
        heuristic: (TPosition, TPosition) -> Double,
        start: Vertex<TPosition>,
        goal: Vertex<TPosition>
    ): Path<TPosition>? {
        val active = PriorityQueue<Vertex<TPosition>>()
        val explored = mutableListOf<TPosition>()

        active.add(start)

        while (active.isNotEmpty()) {
            val current = active.remove()

            explored.add(current.position)

            if (current.position == goal.position) {
                return backtrack(current)
            }

            val neighbours = graph.neighbours(current.position)
            neighbours.forEach { neighbour ->
                if (explored.contains(neighbour)) {
                    return@forEach
                }

                val weight = Weight(
                    current.weight.cost + graph.cost(neighbour, current.position),
                    heuristic(neighbour, goal.position)
                )


                if (!active.any { neighbour == it.position && weight.cost >= it.weight.cost }) {
                    active.add(Vertex(neighbour, weight, current))
                }
            }
        }

        return null
    }

    private fun <TPosition> backtrack(from: Vertex<TPosition>): Path<TPosition> {
        val positions = mutableListOf(from.position)
        var current: Vertex<TPosition>? = from

        while (current != null) {
            positions.add(current.position)
            current = current.parent
        }

        positions.reverse()

        return Path(positions)
    }
}