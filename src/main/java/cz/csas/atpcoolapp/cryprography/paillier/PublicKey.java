package cz.csas.atpcoolapp.cryprography.paillier;

import org.json.JSONObject;

import java.math.BigInteger;
import java.util.Random;

/**
 * A class that represents the public part of the Paillier key pair.
 * <p>
 * As in all asymmetric cryptographic systems it is responsible for the
 * encryption.
 * <p>
 */
public class PublicKey {
    public static final String KEY_PUBLICKEY = "publicKey";
    public static final String KEY_BITS = "bits";
    public static final String KEY_N = "n";
    public static final String KEY_NSQUARED = "nSquared";
    public static final String KEY_G = "g";

    private final int bits;
    private final BigInteger n;
    private final BigInteger nSquared;
    private final BigInteger g;

    public PublicKey(BigInteger n, BigInteger nSquared, BigInteger g, int bits) {
        this.n = n;
        this.nSquared = nSquared;
        this.bits = bits;
        this.g = g;
    }

    public int getBits() {
        return bits;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getnSquared() {
        return nSquared;
    }

    public BigInteger getG() {
        return g;
    }

    /**
     * Encrypts the given plaintext.
     *
     * @param m The plaintext that should be encrypted.
     * @return The corresponding ciphertext.
     */
    public final BigInteger encrypt(BigInteger m) throws InvalidPaillierValueException {

        if(m.signum() < 0) {
            throw new InvalidPaillierValueException("Negative value is not allowed for Paillier encryption.");
        }

        BigInteger r;
        do {
            r = new BigInteger(bits, new Random());
        } while (r.compareTo(n) >= 0);

        BigInteger result = g.modPow(m, nSquared);
        BigInteger x = r.modPow(n, nSquared);

        result = result.multiply(x);
        result = result.mod(nSquared);

        return result;
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        JSONObject jsonPublicKey = new JSONObject();

        jsonPublicKey.put(KEY_BITS, bits);
        jsonPublicKey.put(KEY_N, n.toString());
        jsonPublicKey.put(KEY_NSQUARED, nSquared.toString());
        jsonPublicKey.put(KEY_G, g.toString());
        json.put(KEY_PUBLICKEY, jsonPublicKey);

        return json;
    }

    public static class InvalidPaillierValueException extends Exception {
        InvalidPaillierValueException(String message) {
            super(message);
        }
    }

    @Override
    public String toString() {

        return toJSONObject().toString(4);
    }
}