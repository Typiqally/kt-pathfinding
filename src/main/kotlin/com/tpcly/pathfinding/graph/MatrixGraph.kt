package com.tpcly.pathfinding.graph

import com.tpcly.pathfinding.Point2D
import kotlin.math.sqrt

class MatrixGraph(
    val matrix: Array<Array<Int>>,
    private val adjacent: Array<Pair<Int, Int>>
) : Graph<Point2D> {
    override fun cost(from: Point2D, to: Point2D): Double {
        return if (from.x - to.x == 0 || from.y - to.y == 0) {
            1.0
        } else {
            sqrt2
        }
    }

    override fun neighbours(position: Point2D): List<Point2D> {
        val neighbours = mutableListOf<Point2D>()

        adjacent.forEach { (deltaX, deltaY) ->
            val x = position.x + deltaX
            val y = position.y + deltaY

            if (x < 0 || x >= matrix.size || y < 0 || y >= matrix[0].size || !reachable(position)) {
                return@forEach
            }

            neighbours.add(Point2D(x, y))
        }

        return neighbours
    }

    override fun reachable(position: Point2D): Boolean {
        return matrix[position.x][position.y] == 0
    }

    override fun toString(): String = buildString {
        matrix.forEach { row ->
            row.forEach { column ->
                when (column) {
                    0 -> append("_ ")
                    1 -> append("# ")
                    2 -> append("\u001B[35mx \u001B[0m")
                    3 -> append("\u001B[33m. \u001B[0m")
                }
            }

            append('\n')
        }
    }

    companion object {
        val sqrt2: Double = sqrt(2.0)
        val adjacentOrthogonal: Array<Pair<Int, Int>> = arrayOf(0 to -1, 0 to 1, -1 to 0, 1 to 0)
        val adjacentDiagonal: Array<Pair<Int, Int>> = arrayOf(1 to -1, 1 to 1, -1 to 1, -1 to -1)
    }
}