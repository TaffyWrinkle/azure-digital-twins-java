/**
 * Copyright (c) Microsoft Corporation. Licensed under the MIT License.
 */
package com.microsoft.twins.reflector;

import java.util.UUID;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.microsoft.twins.reflector.proxy.TenantResolver;

@Configuration
@EnableAutoConfiguration
@EnableBinding({TestFeedbackSink.class, TestIngressSource.class, TestDeviceMessageSink.class})
@EnableConfigurationProperties(TestConfigurationProperties.class)
public class TestConfiguration {

  @Bean
  ListenToIngressSampler listenToIngressSampler() {
    return new ListenToIngressSampler();
  }

  @Bean
  TenantResolver tenantResolver() {
    return new TestTenantResolver();
  }


  public class TestTenantResolver implements TenantResolver {
    private UUID tenant;
    private UUID gateway;

    public void setTenant(final UUID tenant) {
      this.tenant = tenant;
    }

    @Override
    public UUID getTenant() {
      return tenant;
    }

    @Override
    public UUID getGateway() {
      return gateway;
    }

    public void setGateway(final UUID gateway) {
      this.gateway = gateway;
    }

  }



}
