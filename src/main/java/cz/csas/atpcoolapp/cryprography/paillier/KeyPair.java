package cz.csas.atpcoolapp.cryprography.paillier;
import org.json.JSONObject;

import java.math.BigInteger;

/**
 * A class that holds a pair of associated public and private keys.
 */
public class KeyPair {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private final BigInteger upperBound;

    public KeyPair(PrivateKey privateKey, PublicKey publicKey, BigInteger upperBound) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.upperBound = upperBound;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Decrypts the given ciphertext.
     */
    public final BigInteger decrypt(BigInteger c) {

        BigInteger n = publicKey.getN();
        BigInteger nSquare = publicKey.getnSquared();
        BigInteger lambda = privateKey.getLambda();

        BigInteger u = privateKey.getPreCalculatedDenominator();

        BigInteger p = c.modPow(lambda, nSquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);

        if (upperBound != null && p.compareTo(upperBound) > 0) {
            p = p.subtract(n);
        }

        return p;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();

        if(privateKey != null && publicKey != null) {
            jsonObject.put(PrivateKey.KEY_PRIVATEKEY, privateKey.toJSONObject().get(PrivateKey.KEY_PRIVATEKEY));
            jsonObject.put(PublicKey.KEY_PUBLICKEY, publicKey.toJSONObject().get(PublicKey.KEY_PUBLICKEY));
        }

        return jsonObject;
    }

    @Override
    public String toString() {

        return toJSONObject().toString(4);
    }
}
