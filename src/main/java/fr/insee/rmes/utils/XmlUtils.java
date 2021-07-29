package fr.insee.rmes.utils;

public class XmlUtils {
	
	public static String encodeXml(String s) {
		 return s.replaceAll("&lt;([a-zA-Z]+)&gt;", "<$1>") //xml open tag
				 .replaceAll("&lt;/([a-zA-Z]+)&gt;", "</$1>") //xml close tag
				 .replaceAll("&lt;([a-zA-Z]+)/&gt;", "<$1/>") //br
				 .replaceAll("&lt;([a-zA-Z]+) /&gt;", "<$1 />"); //br with space
	}

}
