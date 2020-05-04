package ru.phiche.socialnetwork.service

import ru.phiche.socialnetwork.api.VzapertiController
import ru.phiche.socialnetwork.api.dto.BaseUserInfo
import ru.phiche.socialnetwork.dto.Person
import ru.phiche.socialnetwork.storage.AccountRepository
import ru.phiche.socialnetwork.storage.PersonRepository
import ru.phiche.socialnetwork.storage.mapper.Account
import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClientAccountService(val personRepository: PersonRepository,
                           val accountRepository: AccountRepository) {

    @Transactional
    fun registerAccount(account: Account): Int {
        logger.info("account = $account")
        val personId = personRepository.createNew()
        return accountRepository.createAccount(
                Account(
                        personId = personId,
                        login = account.login,
                        password = account.password,
                        registrationTelNo = account.registrationTelNo,
                        registrationMail = account.registrationMail,
                        registeredAt = account.registeredAt
                )
        )
    }

    fun updatePerson(person: Person) {
        personRepository.updatePerson(person)
    }

    fun getBasicUserInfoByUsername(username: String): BaseUserInfo {
        val account = accountRepository.findAccountByLogin(username)
                ?: throw Exception("Failed to find account by username = $username")

        return BaseUserInfo(accountId = account.id!!, personId = account.personId!!)
    }

    companion object : KLogging()
}