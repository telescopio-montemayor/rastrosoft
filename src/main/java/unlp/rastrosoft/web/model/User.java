/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unlp.rastrosoft.web.model;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author ip300
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private String enabled;
    private String name;
    private String lastname;
    private String mail;
    private String hash;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public User(){
        
    }
    public User(int userId, String username, String password, String enabled, String name, String lastname, String mail) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
    public String createHash(){
        SecureRandom random = new SecureRandom();
        String hashcode = new BigInteger(130, random).toString(32).substring(0, 8);
        this.setHash(hashcode);
        return hashcode;
    }
    public void sendConfirmationMail(String hashcode){
        SendMailTLS email = new SendMailTLS();
        String to,subject,content;
        to = this.getMail();
        String activationLink = "http://localhost:8080/rastrosoft/activate?hash=" + hashcode + "&mail=" + to;
        subject = "Solicitud de registro en rastrosoft";
        //content = "Bienvenido," + "\n\n Por favor haga <a href=\"http://www.google.com\">click aqui</a> para verificar su cuenta y poder acceder.";
        content = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                  "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                  "<head>\n" +
                  "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                  "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                  "  <title>Bienvenido a rastrosoft</title>\n" +
                  "\n" +
                  "  <style type=\"text/css\">\n" +
                  "  @import url(http://fonts.googleapis.com/css?family=Droid+Sans);\n" +
                  "\n" +
                  "  /* Take care of image borders and formatting */\n" +
                  "\n" +
                  "  img {\n" +
                  "    max-width: 600px;\n" +
                  "    outline: none;\n" +
                  "    text-decoration: none;\n" +
                  "    -ms-interpolation-mode: bicubic;\n" +
                  "  }\n" +
                  "\n" +
                  "  a {\n" +
                  "    text-decoration: none;\n" +
                  "    border: 0;\n" +
                  "    outline: none;\n" +
                  "    color: #bbbbbb;\n" +
                  "  }\n" +
                  "\n" +
                  "  a img {\n" +
                  "    border: none;\n" +
                  "  }\n" +
                  "\n" +
                  "  /* General styling */\n" +
                  "\n" +
                  "  td, h1, h2, h3  {\n" +
                  "    font-family: Helvetica, Arial, sans-serif;\n" +
                  "    font-weight: 400;\n" +
                  "  }\n" +
                  "\n" +
                  "  td {\n" +
                  "    text-align: center;\n" +
                  "  }\n" +
                  "\n" +
                  "  body {\n" +
                  "    -webkit-font-smoothing:antialiased;\n" +
                  "    -webkit-text-size-adjust:none;\n" +
                  "    width: 100%;\n" +
                  "    height: 100%;\n" +
                  "    color: #37302d;\n" +
                  "    background: #ffffff;\n" +
                  "    font-size: 16px;\n" +
                  "  }\n" +
                  "\n" +
                  "   table {\n" +
                  "    border-collapse: collapse !important;\n" +
                  "  }\n" +
                  "\n" +
                  "  .headline {\n" +
                  "    color: #ffffff;\n" +
                  "    font-size: 36px;\n" +
                  "  }\n" +
                  "\n" +
                  " .force-full-width {\n" +
                  "  width: 100% !important;\n" +
                  " }\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "  </style>\n" +
                  "\n" +
                  "  <style type=\"text/css\" media=\"screen\">\n" +
                  "      @media screen {\n" +
                  "         /*Thanks Outlook 2013! http://goo.gl/XLxpyl*/\n" +
                  "        td, h1, h2, h3 {\n" +
                  "          font-family: 'Droid Sans', 'Helvetica Neue', 'Arial', 'sans-serif' !important;\n" +
                  "        }\n" +
                  "      }\n" +
                  "  </style>\n" +
                  "\n" +
                  "  <style type=\"text/css\" media=\"only screen and (max-width: 480px)\">\n" +
                  "    /* Mobile styles */\n" +
                  "    @media only screen and (max-width: 480px) {\n" +
                  "\n" +
                  "      table[class=\"w320\"] {\n" +
                  "        width: 320px !important;\n" +
                  "      }\n" +
                  "\n" +
                  "\n" +
                  "    }\n" +
                  "  </style>\n" +
                  "</head>\n" +
                  "<body class=\"body\" style=\"padding:0; margin:0; display:block; background:#ffffff; -webkit-text-size-adjust:none\" bgcolor=\"#ffffff\">\n" +
                  "<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\" >\n" +
                  "  <tr>\n" +
                  "    <td align=\"center\" valign=\"top\" bgcolor=\"#ffffff\"  width=\"100%\">\n" +
                  "      <center>\n" +
                  "        <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" class=\"w320\">\n" +
                  "          <tr>\n" +
                  "            <td align=\"center\" valign=\"top\">\n" +
                  "\n" +
                  "                <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"margin:0 auto;\">\n" +
                  "                  <tr>\n" +
                  "                    <td style=\"font-size: 30px; text-align:center;\">\n" +
                  "                      <br>\n" +
                  "                        Rastrosoft\n" +
                  "                      <br>\n" +
                  "                      <br>\n" +
                  "                    </td>\n" +
                  "                  </tr>\n" +
                  "                </table>\n" +
                  "\n" +
                  "                <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#4dbfbf\">\n" +
                  "                  <tr>\n" +
                  "                    <td>\n" +
                  "                    <br>\n" +
                  "                      <img src=\"https://www.filepicker.io/api/file/Pv8CShvQHeBXdhYu9aQE\" width=\"216\" height=\"189\" alt=\"robot picture\">\n" +
                  "                    </td>\n" +
                  "                  </tr>\n" +
                  "                  <tr>\n" +
                  "                    <td class=\"headline\">\n" +
                  "                      Bienvenido!\n" +
                  "                    </td>\n" +
                  "                  </tr>\n" +
                  "                  <tr>\n" +
                  "                    <td>\n" +
                  "\n" +
                  "                      <center>\n" +
                  "                        <table style=\"margin: 0 auto;\" cellpadding=\"0\" cellspacing=\"0\" width=\"60%\">\n" +
                  "                          <tr>\n" +
                  "                            <td style=\"color:#187272;\">\n" +
                  "                            <br>\n" +
                  "                             Al asombroso mundo de la astronomía moderna! Estamos seguros que te sentirás como en casa con Rastrosoft.\n" +
                  "                            <br>\n" +
                  "                            <br>\n" +
                  "                            </td>\n" +
                  "                          </tr>\n" +
                  "                        </table>\n" +
                  "                      </center>\n" +
                  "\n" +
                  "                    </td>\n" +
                  "                  </tr>\n" +
                  "                  <tr>\n" +
                  "                    <td>\n" +
                  "                      <div><!--[if mso]>\n" +
                  "                        <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"http://www.google.com\" style=\"height:50px;v-text-anchor:middle;width:200px;\" arcsize=\"8%\" stroke=\"f\" fillcolor=\"#178f8f\">\n" +
                  "                          <w:anchorlock/>\n" +
                  "                          <center>\n" +
                  "                        <![endif]-->\n" +
                  "                            <a href=\""+activationLink+"\"\n" +
                  "                      style=\"background-color:#178f8f;border-radius:4px;color:#ffffff;display:inline-block;font-family:Helvetica, Arial, sans-serif;font-size:16px;font-weight:bold;line-height:50px;text-align:center;text-decoration:none;width:200px;-webkit-text-size-adjust:none;\">Activar cuenta!</a>\n" +
                  "                        <!--[if mso]>\n" +
                  "                          </center>\n" +
                  "                        </v:roundrect>\n" +
                  "                      <![endif]--></div>\n" +
                  "                      <br>\n" +
                  "                      <br>\n" +
                  "                    </td>\n" +
                  "                  </tr>\n" +
                  "                </table>\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "\n" +
                  "            </td>\n" +
                  "          </tr>\n" +
                  "        </table>\n" +
                  "    </center>\n" +
                  "    </td>\n" +
                  "  </tr>\n" +
                  "</table>\n" +
                  "</body>\n" +
                  "</html>";
      email.sendMail(to,subject,content);
    }
   
    
}
