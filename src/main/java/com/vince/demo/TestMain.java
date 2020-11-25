package com.vince.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class TestMain {

	public static void main(String[] args) {
		// Recipient's email ID needs to be mentioned.
		String to = "vincenzo.lucchese77@gmail.com";
		// Sender's email ID needs to be mentioned
		String from = "v.lucchese2112@gmail.com";
		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("v.lucchese2112@gmail.com", "ttcpfjusjvbffjrs" + 
						"");
			}

		});
		// session.setDebug(true);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");
			Multipart multipart = new MimeMultipart();
			MimeBodyPart attachmentPart = new MimeBodyPart();
			MimeBodyPart textPart = new MimeBodyPart();

			try {

				File f = new File("./history_page/localhost/1/20201124223619/1.html");

				attachmentPart.attachFile(f);
				textPart.setText("This is text");
				multipart.addBodyPart(textPart);
				multipart.addBodyPart(attachmentPart);

			} catch (IOException e) {

				e.printStackTrace();

			}

			message.setContent(multipart);

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	public static void main1(String args[]) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(dateFormat.format(new Date()));

		String refere = "<body";
		String test = "ciao <body class=\"index\"> bla bla";
		String replace = "REPLACE";
		int start = test.indexOf(refere, 0);
		int end = test.indexOf(">", start + refere.length());

		String finale = test.substring(0, end + 1) + replace + test.substring(end + 1, test.length());
		System.out.println(finale);

		System.out.println(start);
		System.out.println(end);

	}

	private void test1() {
		String url = "http://riqualificazione.formez.it/content/concorso-ripam-agenzia-italiana-cooperazione-sviluppo-e-ministero-dellambiente-e-tutela";
		// http://riqualificazione.formez.it/content/concorso-ripam-agenzia-italiana-cooperazione-sviluppo-e-ministero-dellambiente-e-tutela
		System.out.println(url.indexOf("/"));

		String inputLine;
		try {
			URL data = new URL(url);
			HttpURLConnection con = (HttpURLConnection) data.openConnection();

			con.setRequestProperty("User-Agent", "Mozilla/5.0 Firefox/26.0");
			con.connect();

			System.out.println(con.getResponseCode());
			/* Read webpage coontent */
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			FileWriter myWriter = new FileWriter(".store_page/ciao.html");
// myWriter.write("Files in Java might be tricky, but it is fun enough!");

// BufferedWriter writer = Files.newBufferedWriter(dst, StandardCharsets.UTF_8);
			/* Read line by line */
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				myWriter.write(inputLine);
				myWriter.write("\n");
			}

			myWriter.close();
			/* close BufferedReader */
			in.close();
			/* close HttpURLConnection */
			con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
