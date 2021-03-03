package br.com.evenflow.planetsapi.model


data class SWModelList<T>(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<T>? = null
    ){

    fun hasMore(): Boolean {
        return next != null && next.length != 0
    }
}