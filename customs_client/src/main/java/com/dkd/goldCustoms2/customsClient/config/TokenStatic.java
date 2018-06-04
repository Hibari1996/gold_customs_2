package com.dkd.goldCustoms2.customsClient.config;

import java.util.concurrent.ConcurrentLinkedQueue;

public  class TokenStatic {
    private TokenStatic(){

    }
    static ConcurrentLinkedQueue<String> TokenStatic = new ConcurrentLinkedQueue<String>();
}
