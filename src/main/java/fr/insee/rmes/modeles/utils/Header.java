public class Header {
    private String header;

    public Header(String header) {
        if (header == null){
            this.header = null;
        }
        else{
            this.header = header;
        }
    }

    public String getString() {
        if (header != null && !header.isEmpty()) {
            return header;
        }
        else{
            return null;                //without this it might cause some trouble to test with new Header(null)
        }
    }
}
