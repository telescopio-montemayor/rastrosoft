/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.controller;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

/**
 *
 * @author ip300
 */
public class ScapeString {
    
    public static String scape (String unsafe_string){
        
        PolicyFactory policy = new HtmlPolicyBuilder()
        .allowElements("a")
        .allowUrlProtocols("https")
        .allowAttributes("href").onElements("a")
        .requireRelNofollowOnLinks()
        .toFactory();
        
        String safe_string = policy.sanitize(unsafe_string);
        
        return safe_string;
    }
    
}
