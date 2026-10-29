package AbstractDataTypes.Stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;
import java.util.Set;

public class HtmlValidator {
    public static void main(String[] args) {
        //INIT List
        String[] test ={
            "<html></html",
            "<html><h1></h1><body></body></html",
            "<html><head><title>Just Testing</title></head><body><h1>Heading 1</h1></body></html",
            "<html><head></html></head>"
        };

        try{
            // Code for excepion
            boolean r = isValid(test[0]);
            System.out.println(r);
            
            r = isValid(test[1]);
            System.out.println(r);
            
            r = isValid(test[2]);
            System.out.println(r);
            
            r = isValid(test[3]);
            System.out.println(r);


        } catch(Exception e) {
            System.err.print(e.getMessage());
        }

    }

    // Checks explicit tag nesting; this is not a full HTML5 parser.
    public static boolean isValid(String html){
        if (html == null) { return false; }
        Deque<String> stack = new ArrayDeque<>();
        Set<String> voidTags = Set.of("area", "base", "br", "col", "embed", "hr",
                "img", "input", "link", "meta", "param", "source", "track", "wbr");

        int start = html.indexOf("<");

        while(start != -1){
            if (html.startsWith("<!--", start)) {
                int commentEnd = html.indexOf("-->", start + 4);
                if (commentEnd == -1) { return false; }
                start = html.indexOf('<', commentEnd + 3);
                continue;
            }
            int end = findTagEnd(html, start + 1);

            if (end == -1){
                return false;
            }

            String tag = html.substring(start + 1, end).trim();
            if (tag.toLowerCase(Locale.ROOT).matches("!doctype\\s+html")) {
                start = html.indexOf('<', end + 1);
                continue;
            }
            boolean closing = tag.startsWith("/");
            boolean selfClosing = tag.endsWith("/");
            if (closing) { tag = tag.substring(1); }
            if (selfClosing) { tag = tag.substring(0, tag.length() - 1).trim(); }
            int nameEnd = 0;
            while (nameEnd < tag.length() && !Character.isWhitespace(tag.charAt(nameEnd))) { nameEnd++; }
            String name = tag.substring(0, nameEnd).toLowerCase(Locale.ROOT);
            if (!name.matches("[a-z][a-z0-9:-]*")) { return false; }

            if (closing){
                if (selfClosing || !tag.substring(nameEnd).trim().isEmpty()
                        || voidTags.contains(name) || stack.isEmpty() || !name.equals(stack.pop())){
                    return false;
                }
        
            } else if (!selfClosing && !voidTags.contains(name)) {
                stack.push(name);
            }

            start = html.indexOf("<", end + 1);
        } //Closes while loop

        return stack.isEmpty();
    }

    private static int findTagEnd(String html, int start) {
        char quote = 0;
        for (int i = start; i < html.length(); i++) {
            char current = html.charAt(i);
            if (quote != 0) {
                if (current == quote) { quote = 0; }
            } else if (current == '\'' || current == '"') {
                quote = current;
            } else if (current == '>') {
                return i;
            }
        }
        return -1;
    }
}
