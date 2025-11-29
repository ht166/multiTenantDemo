package com.demo;

import java.util.Collections;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
@MapperScan(basePackages = "com.demo.repository")
public class MultiTenantDemoApplication extends SpringBootServletInitializer  {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MultiTenantDemoApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(MultiTenantDemoApplication.class, args);
		
	}
	
	@Bean
	ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
		return new TomcatServletWebServerFactory() {
		
		@Override
		protected void postProcessContext(Context context) {
			super.postProcessContext(context);
			JspPropertyGroup jspPropertyGroup = new JspPropertyGroup();
			jspPropertyGroup.addUrlPattern("*.jsp");
			jspPropertyGroup.setElIgnored("false");
			jspPropertyGroup.setPageEncoding("MS932");
			jspPropertyGroup.setScriptingInvalid("false");
			jspPropertyGroup.addIncludePrelude("/WEB-INF/view/common/common.jspf");
			JspPropertyGroupDescriptorImpl jspPropertyGroupDescriptor = new JspPropertyGroupDescriptorImpl(jspPropertyGroup);
			context.setJspConfigDescriptor(new JspConfigDescriptorImpl(Collections.singletonList(jspPropertyGroupDescriptor), Collections.emptyList()));
		}
		};
	}

}
