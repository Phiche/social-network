package ru.phiche.socialnetwork.storage

import ru.phiche.socialnetwork.storage.mapper.Account

interface AccountRepository {
    fun findAccountByLogin(login: String): Account?
    fun createAccount(account: Account): Int
}