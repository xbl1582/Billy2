package com.light.hexo.common.config;

import com.light.hexo.business.admin.config.BlogProperty;
import com.light.hexo.business.admin.constant.ConfigEnum;
import com.light.hexo.business.admin.service.ConfigService;
import com.light.hexo.business.admin.web.interceptor.InstallInterceptor;
import com.light.hexo.business.admin.web.interceptor.UserInterceptor;
import com.light.hexo.common.component.interceptor.VisitInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SpringMvcConfig
 * @Description: Spring mvc 配置
 */
@Configuration
@DependsOn("flywayConfig")
public class SpringMvcConfig implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

    @Autowired
    private VisitInterceptor visitInterceptor;

    @Autowired
    private InstallInterceptor installInterceptor;

    @Autowired
    private ConfigService configService;

    @Autowired
    private BlogProperty blogProperty;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String filePath = this.configService.getConfigValue(ConfigEnum.LOCAL_FILE_PATH.getName());
        String localFilePath = StringUtils.isNotBlank(filePath) ?
                filePath  + File.separator
                : this.blogProperty.getAttachmentDir();

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" +  localFilePath);

        registry.addResourceHandler("/cover/**")
                .addResourceLocations("file:" +  localFilePath + "cover/");

        registry.addResourceHandler("/theme/**")
                .addResourceLocations(
                        "file:" + this.blogProperty.getThemeDir(),
                        "classpath:/templates/theme/"
                );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(installInterceptor)
                .addPathPatterns("/")
                .addPathPatterns("/admin")
                .addPathPatterns("/admin/login.html")
                .addPathPatterns("/admin/login.json")
                .excludePathPatterns("/error");

        registry.addInterceptor(visitInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/assets/**")
                .excludePathPatterns("/theme/**")
                .excludePathPatterns("/error");

        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns(
                        "/admin/assets/**",
                        "/admin/captcha.jpg",
                        "/admin/login.html",
                        "/admin/login.json",
                        "/admin/logout.json",
                        "/admin/install.html",
                        "/admin/install.json",
                        "/admin/postAuth.html",
                        "/error");
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(){
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        List<View> list = new ArrayList<>();
        list.add(mappingJackson2JsonView());
        viewResolver.setDefaultViews(list);
        return viewResolver;
    }

    @Bean
    public MappingJackson2JsonView mappingJackson2JsonView(){
        return new MappingJackson2JsonView();
    }
}
