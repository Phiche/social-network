package ru.phiche.socialnetwork

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class SocialNetworkApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(SocialNetworkApplication::class.java)
            .bannerMode(Banner.Mode.OFF)
            .run(*args)
}

/**
 * Хотелки:
 * Вход по почте или номеру телефона
 * Заолнение store при входе
 * Сделать один из них обязательным
 * Разграничить области видимости входа и разобраться с хранением id
 * Список друзей и подружиться
 * Сделать ленту
 * Разобраться с датой рождения и возрастом
 * Починить disable на кнопке добавления поста
 * Refresh постов при добавлениии нового
 * Мб главная страница hello с приглашением зарегаться или войти
 * Сделать редирект для запросов на бэк
 * закрыть регистрацией запросы на бэк
 * сделать аутентификацию сложнее
 * использовать кэш, чтобы постоянно не авторизовываться - https://blog.sqreen.com/authentication-best-practices-vue/
 * едем на гитхаб и хироку
 **/