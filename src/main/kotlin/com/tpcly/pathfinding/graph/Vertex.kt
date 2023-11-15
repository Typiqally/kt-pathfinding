package com.tpcly.pathfinding.graph

data class Vertex<TPosition>(
    val position: TPosition,
    val weight: Weight,
    val parent: Vertex<TPosition>? = null
) : Comparable<Vertex<TPosition>> {
    override fun compareTo(other: Vertex<TPosition>): Int = (weight.total - other.weight.total).toInt()
}