package net.pixeldepth.warframe;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class Task extends TimerTask {

	private Pattern pattern;
	private String feed;

	public Task(Pattern pattern, String feed){
		this.pattern = pattern;
		this.feed = feed;
	}

	@Override
	public void run(){
		try {
			URL url = new URL(this.feed);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);

			try {

				SAXParser p = factory.newSAXParser();

				Warframe_Alert_Handler handler = new Warframe_Alert_Handler();
				p.parse(new InputSource(url.openStream()), handler);

			} catch(ParserConfigurationException e){
				e.printStackTrace();
			} catch(SAXException e){
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			}
		} catch(MalformedURLException e){
			e.printStackTrace();
		}
	}

}
