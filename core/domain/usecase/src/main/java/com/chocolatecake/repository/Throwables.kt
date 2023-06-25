package com.chocolatecake.repository

open class ApiThrowable(message: String?): Throwable(message)
class UnauthorizedThrowable: ApiThrowable("Unauthorized")
class NoNetworkThrowable: ApiThrowable("No Network")
class TimeoutThrowable:ApiThrowable("Not Logged In")
class ParsingThrowable:ApiThrowable("Parsing Error")

