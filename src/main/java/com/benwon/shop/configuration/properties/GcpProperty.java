package com.benwon.shop.configuration.properties;

import lombok.Data;

@Data
public class GcpProperty {
    private String bucketUrlPrefix;
    private BucketProperty bucket;
}
