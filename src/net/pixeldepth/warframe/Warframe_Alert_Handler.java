package net.pixeldepth.warframe;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Warframe_Alert_Handler extends DefaultHandler {

	private List<Alert> alerts = new ArrayList<Alert>();
	private Alert current_alert;
	private StringBuilder content;
	private Pattern regex;
	private SimpleDateFormat date_format = new SimpleDateFormat("E, dd MMM yyyy H:mm:ss Z");

	public Warframe_Alert_Handler(Pattern regex){
		this.regex = regex;
	}

	@Override
	public void startElement(String uri, String l_name, String elem_name, Attributes attributes) throws SAXException {
		this.content = new StringBuilder();

		if(elem_name.equalsIgnoreCase("item")){
			this.current_alert = new Alert();
		}
	}

	@Override
	public void endElement(String uri, String l_name, String elem_name) throws SAXException {
		if(elem_name.equalsIgnoreCase("item")){
			if(this.current_alert != null){
				this.alerts.add(this.current_alert);
			}
		} else if(this.current_alert != null){
			if(elem_name.equalsIgnoreCase("title")){
				this.current_alert.title = content.toString();
			} else if(elem_name.equalsIgnoreCase("guid")){
				this.current_alert.guid = content.toString();
			} else if(elem_name.equalsIgnoreCase("author")){
				this.current_alert.author = content.toString();
			} else if(elem_name.equalsIgnoreCase("description")){
				this.current_alert.description = content.toString();
			} else if(elem_name.equalsIgnoreCase("wf:faction")){
				this.current_alert.faction = content.toString();
			} else if(elem_name.equalsIgnoreCase("wf:expiry")){
				this.current_alert.expiry = content.toString();
			} else if(elem_name.equalsIgnoreCase("pubdate")){
				this.current_alert.date = content.toString();

				try {
					Date parsed = this.date_format.parse(this.current_alert.date);
					this.current_alert.time = parsed.getTime();
				} catch(ParseException e){
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		content.append(ch, start, length);
	}

	@Override
	public void endDocument() throws SAXException {
		Iterator<Alert> i = this.alerts.iterator();

		while(i.hasNext()){
			Alert item = i.next();

			if(item.time > Settings_Application.last_alert_time()){
				Matcher matcher = this.regex.matcher(item.title);

				if(matcher.find()){
					Warframe_Notifications.show_notification(item);
				}
			}
		}
	}

}
