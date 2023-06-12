package com.chocolatecake.repository

open class ApiThrowable: Throwable()
class UnauthorizedThrowable: ApiThrowable()
class NoNetworkThrowable: ApiThrowable()

