package com.benwon.shop.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.benwon.shop.entity.InviteCode;
import com.benwon.shop.entity.Product;
import com.benwon.shop.entity.SaleGroup;
import com.benwon.shop.entity.SaleOrder;
import com.benwon.shop.entity.SaleOrderDetail;

@Configuration
@EnableJpaAuditing
public class RepositoryConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Product.class);
        config.exposeIdsFor(SaleGroup.class);
        config.exposeIdsFor(SaleOrder.class);
        config.exposeIdsFor(SaleOrderDetail.class);
        config.exposeIdsFor(InviteCode.class);


    }
}
