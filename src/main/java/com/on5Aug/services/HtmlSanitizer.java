package com.on5Aug.services;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Service;

@Service
public class HtmlSanitizer {
    public static String sanitize(String html) {
    	List<String> tags = Arrays.asList(
                "a", "b", "blockquote", "br", "caption", "cite", "code", "col",
                "colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6",
                "i", "img", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong",
                "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u",
                "ul", "hr"
        );

        Safelist safeList = Safelist.basicWithImages();
        for (String tag : tags) {
        	safeList.addAttributes(tag, "style");
        	safeList.addAttributes(tag, "class");
        }

        safeList
            .addAttributes("a", "href", "title")
            .addAttributes("blockquote", "cite")
            .addAttributes("col", "span", "width")
            .addAttributes("colgroup", "span", "width")
            .addAttributes("img", "align", "alt", "height", "src", "title", "width")
            .addAttributes("ol", "start", "type")
            .addAttributes("q", "cite")
            .addAttributes("table", "summary", "width")
            .addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width")
            .addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width")
            .addAttributes("ul", "type")
            .addProtocols("a", "href", "ftp", "http", "https", "mailto")
            .addProtocols("blockquote", "cite", "http", "https")
            .addProtocols("cite", "cite", "http", "https")
            .addProtocols("img", "src", "http", "https")
            .addProtocols("q", "cite", "http", "https");

        return Jsoup.clean(html, safeList);

    }
}

