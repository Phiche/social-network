package ru.phiche.socialnetwork.api

import ru.phiche.socialnetwork.api.dto.BaseUserInfo
import ru.phiche.socialnetwork.api.dto.Relationship
import ru.phiche.socialnetwork.dto.Person
import ru.phiche.socialnetwork.dto.Post
import ru.phiche.socialnetwork.service.ClientAccountService
import ru.phiche.socialnetwork.storage.AccountRepository
import ru.phiche.socialnetwork.storage.PersonRepository
import ru.phiche.socialnetwork.storage.PostRepository
import ru.phiche.socialnetwork.storage.RelationshipRepository
import ru.phiche.socialnetwork.storage.mapper.Account
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping("/api")
class VzapertiController(
        val personRepository: PersonRepository,
        val clientAccountService: ClientAccountService,
        val postRepository: PostRepository,
        val relationshipRepository: RelationshipRepository
) {

    @RequestMapping(path = ["/account"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun registerAccount(@RequestBody account: Account): Int {
        return clientAccountService.registerAccount(account)
    }

    @RequestMapping(path = ["/person"], method = [RequestMethod.POST])
    @ResponseStatus(HttpStatus.CREATED)
    fun updatePerson(@RequestBody person: Person) {
        logger.info("Trying to update person with personId = ${person.id}")
        clientAccountService.updatePerson(person)
    }

    @GetMapping(path = ["/person"])
    @ResponseStatus(HttpStatus.CREATED)
    fun getPersonByAccountId(@RequestParam(name = "accountId", required = true)
                             accountId: Int): Person {
        logger.info("Trying to find person with accountId = $accountId")
        return personRepository.getPersonByAccountId(accountId)
    }

    @GetMapping("/people")
    fun getPeople(): List<Person> {
        return personRepository.getPeople()
    }

    @GetMapping("/person/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun getPersonById(@PathVariable("id")
                      id: Int): Person {
        logger.info("Trying to find person by id = $id")
        return personRepository.getPersonById(id)
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    fun postNews(@RequestBody post: Post): Int {
        return  postRepository.createPost(post)
    }

    @GetMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    fun getPostsByPersonId(@RequestParam(name = "personId", required = true)
                      personId: Int): List<Post> {
        logger.info("Trying to find posts by personId = $personId")
        return postRepository.findPostsByPersonId(personId)
    }

    @RequestMapping(path = ["/hello"])
    fun sayHello(): String? {
        logger.info("GET called on /hello resource")
        return "Hello from Spring Boot Backend!"
    }

    @RequestMapping(path = ["/secured"], method = [RequestMethod.GET])
    @ResponseBody
    fun getSecured(principal: Principal): BaseUserInfo {
        logger.info("GET successfully called on /secured resource")
        val result = clientAccountService.getBasicUserInfoByUsername(principal.name)

        logger.info(result.toString())

        return result
    }

    @PostMapping("/relationship")
    @ResponseStatus(HttpStatus.CREATED)
    fun createFriendshipRequest(@RequestBody relationship: Relationship) {
        relationshipRepository.createFriendshipRequest(relationship)
    }

    // Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
    // Required because of 'mode: history' usage in frontend routing, see README for further details
    @RequestMapping(path = ["{_:^(?!index\\.html|api).*$}"], method = [RequestMethod.GET])
    fun redirectApi(): String {
        logger.info("URL entered directly into the Browser, so we need to redirect...")
        return "forward:/"
    }

    companion object : KLogging()
}