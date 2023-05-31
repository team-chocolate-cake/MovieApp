package com.chocolatecake.movieapp.domain.mappers

interface Mapper<INPUT, OUTPUT> {
    fun map(input: INPUT): OUTPUT
}