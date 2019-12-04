package Tweet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TweetFileWriter implements AutoCloseable{
	
	private static Map<String,BufferedWriter> writers = new HashMap<>();
	
	private TweetFileWriter() {}
	
	public TweetFileWriter(List<String> topics) {
		if(null != topics) {
			topics.forEach(topic->{
				try {
					addTopic(topic);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}
	
	public void addTopic(String topic) throws IOException {
		BufferedWriter topicWriter = new BufferedWriter(new FileWriter(topic,true));
		writers.put(topic, topicWriter);
	}
	
	public void writeTweet(String content) {
		if(null != content) {
			this.writers.keySet().forEach(topic ->{
				if(content.indexOf("#"+ topic)>=0) {
					try {
						writeTweet(topic, content);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	public void writeTweet(String topic, String content) throws IOException {
		BufferedWriter writer = writers.get(topic);
		if(null != writer) {
			writer.write(content+"\r\n");
		}
	}

	@Override
	public void close() throws Exception {
		writers.values().forEach(writer -> {
			if(null!=writer) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				writer = null;
			}
		});
	}
}
