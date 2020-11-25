package com.vince.demo.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class CustomAuthenticator extends Authenticator {
	
	CustomAuthenticator auth = new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("", "ttcpfjusjvbffjrs");
		}
	}

}
