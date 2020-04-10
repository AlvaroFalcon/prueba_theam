package com.monkeys.test.common

interface NetworkOperationCallback{
    fun showProgress()
    fun showError()
    fun hideProgress()
}