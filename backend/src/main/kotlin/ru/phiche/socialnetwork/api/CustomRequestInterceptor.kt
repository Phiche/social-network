package ru.phiche.socialnetwork.api

import mu.KLogging
import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.time.Instant
import java.util.stream.Collectors
import javax.servlet.DispatcherType
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class CustomRequestInterceptor: HandlerInterceptorAdapter() {

    companion object: KLogging()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.dispatcherType == DispatcherType.REQUEST)
            logger.info("PSS.REQUEST.ACCEPTED;" +
                    " method=${request.requestURI};" +
                    " returnExtraData=${request.getHeader("Return-Extra-Data")}" +
                    " query=${request.queryString};")
        return true
    }

}