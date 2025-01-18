package com.official.memento.auth.infrastructure.apple;

public class AppleKey {
    private String kid;
    private String alg; // Algorithm
    private String n;   // Modulus
    private String e;   // Exponent
    private String kty; // Key Type (e.g., RSA)

    public String getKid() { return kid; }
    public String getAlg() { return alg; }
    public String getN() { return n; }
    public String getE() { return e; }
    public String getKty() { return kty; }
}
