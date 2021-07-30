package fr.insee.rmes.utils;

public class XmlUtils {
	
	public static String encodeXml(String s) {
		 return s.replaceAll("&lt;([a-zA-Z]+)&gt;", "<$1>") //xml open tag
				 .replaceAll("&lt;([a-zA-Z]+) style=(&quot;|')(.*?)(&quot;|')&gt;", "<$1 style=\"$3\">") //open tag with style
				 .replaceAll("&lt;/([a-zA-Z]+)&gt;", "</$1>") //xml close tag
				 .replaceAll("&lt;([a-zA-Z]+)/&gt;", "<$1/>") //br
				 .replaceAll("&lt;([a-zA-Z]+) /&gt;", "<$1 />") //br with space
				 .replaceAll("&lt;a href=(&quot;|')(.*?)(&quot;|')&gt;", "<a href=\"$2\"> ") //a href open tag
				 .replaceAll("&lt;a title=(&quot;|')(.*?)(&quot;|') href=(&quot;|')(.*?)(&quot;|')&gt;", "<a title=\"$2\" href=\"$5\"> ") //a title href open tag
		 		.replaceAll("&lt;a href=(&quot;|')(.*?)(&quot;|') title=(&quot;|')(.*?)(&quot;|')&gt;", "<a title=\"$5\" href=\"$2\"> "); //a href title open tag

	}

}
