package com.chocolatecake.movieapp.ui.viewmodel.mappers

interface Mapper<INPUT, OUTPUT> {
    fun map(input: INPUT): OUTPUT
    fun map(input: List<INPUT>): List<OUTPUT>{
        return input.map(::map)
    }
}