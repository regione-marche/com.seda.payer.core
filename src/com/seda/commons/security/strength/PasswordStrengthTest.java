/**
 * 
 */
package com.seda.commons.security.strength;

/**
 * @author f.ricci
 *
 */
public class PasswordStrengthTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] passwords = new String[]{"hello123##KHG","normale","s@v3m3","pass23@aa",
				"supercalifragile","MamMaMia","password","TeStA123!"};
		PasswordStrength passwordStrength =PasswordStrength.instance();
		for (int i = 0; i < passwords.length; i++) {
			int verdict = passwordStrength.check(passwords[i]);
			System.out.println("Password '" + passwords[i] + "' = " + verdict + " (" +PasswordStrength.verdictToString(verdict) + ")");
		}
	}

}
