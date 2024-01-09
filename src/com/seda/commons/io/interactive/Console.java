/**
 * 
 */
package com.seda.commons.io.interactive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.util.Arrays;

/**
 * 
 * @author f.ricci
 *
 */
public class Console {
	private InputStream input;
	private BufferedReader bufferdReader;

	
	public static void main(String[] args) {
		Console console = new Console();
		String nome = console.readLine("Fornire il nome: ");
		String password = console.readPassword("Fornire la password: ");
		System.out.println(String.format("Nome=%s, Password=%s",nome,password));
	}
    
	public Console() {
		this.input=System.in;
		this.bufferdReader= new BufferedReader(new InputStreamReader(System.in));
	}

	
	public void writeLine(String prompt)  {
		System.out.println(prompt);
	}	
	
	public String readLine(String prompt)  {
		try {
			System.out.print(prompt);
			return bufferdReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public String readPassword(String prompt) {
		Masking maskingthread = new Masking(prompt);
		Thread thread = new Thread(maskingthread);
		thread.start();
		try {
			char[] lineBuffer;
			char[] buf;

			buf = lineBuffer = new char[128];

			int room = buf.length;
			int offset = 0;
			int c;

			loop:   while (true) {
				switch (c = input.read()) {
				case -1:
				case '\n':
					break loop;
				case '\r':
					int c2 = input.read();
					if ((c2 != '\n') && (c2 != -1)) {
						if (!(input instanceof PushbackInputStream)) {
							input = new PushbackInputStream(input);
						}
						((PushbackInputStream)input).unread(c2);
					} else {
						break loop;
					}
				default:
					if (--room < 0) {
						buf = new char[offset + 128];
						room = buf.length - offset - 1;
						System.arraycopy(lineBuffer, 0, buf, 0, offset);
						Arrays.fill(lineBuffer, ' ');
						lineBuffer = buf;
					}
					buf[offset++] = (char) c;
					break;
				}
			}
			maskingthread.stopMasking();			
			if (offset == 0) {
				return null;
			}
			char[] ret = new char[offset];
			System.arraycopy(buf, 0, ret, 0, offset);
			Arrays.fill(buf, ' ');
			return String.valueOf(ret);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			maskingthread.stopMasking();
		}
		return null;
	}	
	
}
