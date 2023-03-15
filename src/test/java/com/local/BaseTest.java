package com.local;

import com.local.client.BaseClient;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    @BeforeAll
    public static void setup() {
        BaseClient client = new BaseClient();
    }
}
