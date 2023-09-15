package com.ivo.rickandmortyapp.utils

interface BaseMapper<I, O> {
    fun map(input: I): O
}