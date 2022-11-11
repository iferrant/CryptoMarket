package dev.ferrant.core.arch

interface BaseView<VS: ViewState> {
    fun renderState(state: VS)
}
