package dev.ferrant.core.arch

interface StateReducer<VS: ViewState> {
    fun reduce(initialState: VS): VS
}
