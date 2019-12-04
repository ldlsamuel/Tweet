package Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tweet {
	
	private static List<String> topics = new ArrayList<>();
	
	private static void getTopics(Scanner input) {
		System.out.println("Please input topics(max:5  type quit to quit):");
		
		for (int i = 0; i < Const.TOPIC_NUBEMER; i++) {
			System.out.print("Enter topic or quit:");
			String topic = input.next();
			if("quit".equals(topic)) {
				break;
			}else {
				topics.add(topic);
			}
		}
	}
	
	private static void inputContent(Scanner input) throws Exception {
		
		try(TweetFileWriter tweetFileWriter = new TweetFileWriter(topics);){
			
	    	System.out.println("Start tweet:");
	    	String strTweet = "";
	    	while(!strTweet.equals("quit")) {
	    		strTweet = input.nextLine();
	    		tweetFileWriter.writeTweet(strTweet);
	    	}
	    	
	    	System.out.print("Finished!");
		}
	}
	

	public static void main(String[] args) throws Exception {

		try(Scanner input = new Scanner(System.in)){
			
			getTopics(input);
	    	
			inputContent(input);	
		}
	}

}
