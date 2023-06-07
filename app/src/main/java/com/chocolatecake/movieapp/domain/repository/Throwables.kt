package com.chocolatecake.movieapp.domain.repository

open class ApiThrowable: Throwable()
class UnauthorizedThrowable: ApiThrowable()
class NoNetworkThrowable: ApiThrowable()

