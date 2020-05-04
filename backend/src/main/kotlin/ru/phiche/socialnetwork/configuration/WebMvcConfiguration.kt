package ru.phiche.socialnetwork.configuration

import ru.phiche.socialnetwork.api.CustomRequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

//@Configuration
class WebMvcConfiguration(
        private val methodLoggingInterceptor: CustomRequestInterceptor
) : WebMvcConfigurationSupport() {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {

        registry.addInterceptor(methodLoggingInterceptor)
                .addPathPatterns("/api/account/**")
                .addPathPatterns("/api/account")
    }
}