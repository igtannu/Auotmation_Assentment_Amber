package com.utils;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.mail.BodyPart;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.MimeMultipart;

public class EmailReader {

	 public static String readOTP(String email, String appPassword) {
		 System.out.println("read otp");
	        try {
	        	String subjectPattern = ".*\\b(\\d{6})\\b.*";
	            // Set up mail session
	            Properties properties = new Properties();
	            properties.put("mail.store.protocol", "imaps");
	            Session session = Session.getDefaultInstance(properties, null);
	            Store store = session.getStore("imaps");

	            // Connect to the Gmail server using the app password
	            store.connect("imap.gmail.com", email, appPassword);
	            Folder inbox = store.getFolder("inbox");
	            inbox.open(Folder.READ_WRITE);
	            inbox.expunge();

	            int messageCount = inbox.getMessageCount();
	            inbox.expunge();

	            
	            int start = Math.max(1, messageCount );  // Fetch the latest message
	            int end = messageCount;

	            // Fetch the latest email
	            Message[] messages = inbox.getMessages(start, end);
	            
	            // Get the messages
	            inbox.expunge();

	            for (Message message : messages) {
	                String messageSubject = message.getSubject();
	                if (messageSubject != null && messageSubject.matches(subjectPattern)) {
	                    // Extract the OTP from the subject
	                    String otpPattern = "\\b\\d{6}\\b";
	                    Pattern otpRegex = Pattern.compile(otpPattern);
	                    Matcher matcher = otpRegex.matcher(messageSubject);

	                    if (matcher.find()) {
	                        // Return the first match (OTP)
	                        return matcher.group();
	                    }
	                }
	            }

	            // Close the connection
	            inbox.close(false);
	            store.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        int count = mimeMultipart.getCount();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                text.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                // Extract text from HTML if needed
                // You may use Jsoup or another HTML parsing library for this
                // For simplicity, let's assume we don't need to extract text from HTML
            }
        }
        return text.toString();
    }
}
