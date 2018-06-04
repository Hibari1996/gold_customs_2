package com.dkd.gold_customs2.customs_client.config;

import java.util.concurrent.ConcurrentLinkedQueue;

public  class TokenStatic {
    private TokenStatic(){

    }
    static ConcurrentLinkedQueue<String> TokenStatic = new ConcurrentLinkedQueue<String>();
}
