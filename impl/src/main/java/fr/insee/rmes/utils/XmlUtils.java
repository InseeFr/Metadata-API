package fr.insee.rmes.utils;

public class XmlUtils {
	
	public static String encodeXml(String s) {
		 return s.replaceAll("&lt;&lt;([a-zA-Z]+)&gt;&gt;", "&lt;&lt; $1 &gt;&gt;") //<<texte>> => << texte >>
				 .replaceAll("&lt;([a-zA-Z]+)&gt;", "<$1>") //xml open tag
		 		 .replaceAll("&lt;([a-zA-Z]+) ([a-zA-Z:]+)?=(&quot;|')(((?!(&quot;|')).)*?)(&quot;|')&gt;", "<$1 $2=\"$4\">") //open tag with one attribute (text or xml:lang)
		 		 .replaceAll("&lt;([a-zA-Z]+) ([a-zA-Z:]+)?=(&quot;|')(((?!(&quot;|')).)*?)(&quot;|') ([a-zA-Z:]+)?=(&quot;|')(((?!(&quot;|')).)*?)(&quot;|')&gt;", "<$1 $2=\"$4\" $8=\"$10\">") //open tag with 2 attributes (text or xml:lang)
		 		 .replaceAll("&lt;([a-zA-Z]+) ([a-zA-Z:]+)?=(&quot;|')(((?!(&quot;|')).)*?)(&quot;|') ([a-zA-Z:]+)?=(&quot;|')(((?!(&quot;|')).)*?)(&quot;|') ([a-zA-Z:]+)?=(&quot;|')(((?!(&quot;|')).)*?)(&quot;|')&gt;", "<$1 $2=\"$4\" $8=\"$10\" $14=\"$16\">") //open tag with 3 attributes (text or xml:lang)
		 		 .replaceAll("&lt;([a-zA-Z]+) ([a-zA-Z:]+)?=(&quot;|')(((?!(&quot;|')).)*?)(&quot;|') ([a-zA-Z:]+)?=(&quot;|')(((?!(&quot;|')).)*?)(&quot;|') ([a-zA-Z:]+)?=(&quot;|')(((?!(&quot;|')).)*?)(&quot;|') ([a-zA-Z:]+)?=(&quot;|')(((?!(&quot;|')).)*?)(&quot;|')&gt;", "<$1 $2=\"$4\" $8=\"$10\" $14=\"$16\" $20=\"$22\">") //open tag with 4 attributes (text or xml:lang)
				 .replaceAll("&lt;/([a-zA-Z]+)&gt;", "</$1>") //xml close tag
				 .replaceAll("&lt;([a-zA-Z]+)/&gt;", "<$1/>") //br
				 .replaceAll("&lt;([a-zA-Z]+) /&gt;", "<$1 />") //br with space

	 			; 



	}

}
