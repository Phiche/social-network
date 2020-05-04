package ru.phiche.socialnetwork.service

import ru.phiche.socialnetwork.storage.AccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service("ownDetails")
class UserDetailsServiceImpl(val accountRepository: AccountRepository): UserDetailsService {
    override fun loadUserByUsername(login: String): UserDetails {
        val account = accountRepository.findAccountByLogin(login)
        account ?: throw Exception("Аккаунта с таким логином не существует")

        return ClientUserDetails(account)
    }
}