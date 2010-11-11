/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

/**
 *
 * @author aq615c
 */
public class WikiHtmlFormatter {
    public static String format(String content)
    {
        return
            "<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01//EN'>" +
            "<html>"+
            "<head>"+
            "<title></title>"+
            "<style type='text/css'>"+
            css +
            "</style>"+
            "</head>"+
            "<body>" +
            content +
            "</body>" +
            "</html>";
    }

    public static String css =
            "";
}
