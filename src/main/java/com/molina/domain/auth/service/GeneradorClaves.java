package com.molina.domain.auth.service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class GeneradorClaves {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        System.out.println("-----BEGIN PRIVATE KEY-----");
        System.out.println(Base64.getMimeEncoder(64, new byte[]{'\n'}).encodeToString(kp.getPrivate().getEncoded()));
        System.out.println("-----END PRIVATE KEY-----\n");

        System.out.println("-----BEGIN PUBLIC KEY-----");
        System.out.println(Base64.getMimeEncoder(64, new byte[]{'\n'}).encodeToString(kp.getPublic().getEncoded()));
        System.out.println("-----END PUBLIC KEY-----");
    }
}