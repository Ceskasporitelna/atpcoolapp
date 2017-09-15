package cz.csas.atpcoolapp.cryprography.paillier;


import org.json.JSONObject;

import java.math.BigInteger;

/**
 * A class that represents the private part of the Paillier key pair.
 */
public class PrivateKey {
    public static final String KEY_LAMBDA = "lambda";
    public static final String KEY_PRECALCULATEDDENOMINATOR = "preCalculatedDenominator";
    public static final String KEY_PRIVATEKEY = "privateKey";
    private final BigInteger lambda;
    private final BigInteger preCalculatedDenominator;

    public PrivateKey(BigInteger lambda, BigInteger preCalculatedDenominator) {
        this.lambda = lambda;

        this.preCalculatedDenominator = preCalculatedDenominator;
    }

    public BigInteger getLambda() {
        return lambda;
    }

    public BigInteger getPreCalculatedDenominator() {
        return preCalculatedDenominator;
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        JSONObject jsonPrivateKey = new JSONObject();

        jsonPrivateKey.put(KEY_LAMBDA,lambda.toString());
        jsonPrivateKey.put(KEY_PRECALCULATEDDENOMINATOR, preCalculatedDenominator.toString());
        json.put(KEY_PRIVATEKEY, jsonPrivateKey);

        return json;
    }

    @Override
    public String toString() {

        return toJSONObject().toString(4);
    }
}
