package fr.insee.rmes.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.codehaus.stax2.io.EscapingWriterFactory;

public class CustomXmlEscapingWriterFactory implements EscapingWriterFactory {
	
	
public Writer createEscapingWriterFor(final Writer out, String enc) {
    return new Writer(){
        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
        	//WARN : the cbuf contains only part of the string = can't validate xml here
            String val = "";
            for (int i = off; i < len; i++) {
                val += cbuf[i];
            }
            String escapedStr = XmlUtils.encodeXml(escapeHtml(val)); //encode manually some xml tags
            out.write(escapedStr);
        }

        @Override
        public void flush() throws IOException {
            out.flush();
        }

        @Override
        public void close() throws IOException {
            out.close();
        }
      };
    }


	private String escapeHtml(String s) {
		 return s.replace("&", "&amp;")
				 .replace(">", "&gt;")
				 .replace("<", "&lt;")
				 .replace("\"", "&quot;");
	}
	


    public Writer createEscapingWriterFor(OutputStream out, String enc) {
        throw new IllegalArgumentException("not supported");
    }
}