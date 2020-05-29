package chapter4.src2;

public interface Cipher {
	
	byte[] encrypt(byte[] source, byte[] key);
	byte[] decrypt(byte[] source, byte[] key);
	int strength();

}
