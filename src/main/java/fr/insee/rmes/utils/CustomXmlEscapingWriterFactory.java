package fr.insee.rmes.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.apache.commons.text.StringEscapeUtils;
import org.codehaus.stax2.io.EscapingWriterFactory;

public class CustomXmlEscapingWriterFactory implements EscapingWriterFactory {
public Writer createEscapingWriterFor(final Writer out, String enc) {
    return new Writer(){
        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            String val = "";
            for (int i = off; i < len; i++) {
                val += cbuf[i];
            }
            String escapedStr =  StringEscapeUtils.unescapeXml(escapeHtml(val)); //convert special characters excluding xml tags
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
				 .replace("\"", "&quot;")
				 .replace("'", "&apos;");
	}

    public Writer createEscapingWriterFor(OutputStream out, String enc) {
        throw new IllegalArgumentException("not supported");
    }
}