package comp2011.lec11;

import java.util.*;

/**
 * 
 * @author Yixin Cao (November 20, 2020)
 *
 * Some examples using java.util.HashMap.
 * {@code color} stores the mapping from color names to their RGB codes, and
 * {@code dns} stores the mapping from domain names to their IP addresses.
 * 
 */    
public class MapExamples {
    private static class IP {
        short[] address;
        public IP (int a, int b, int c, int d) {
            address = new short[4];
            address[0] = (short) a;
            address[1] = (short) b;
            address[2] = (short) c;
            address[3] = (short) d;
        }
        public String toString() {
            return new StringBuilder().append(address[0]).append('.').
                    append(address[1]).append('.').append(address[2]).
                    append('.').append(address[3]).toString();
        }
    }
    private static class Color{
        short r, g, b;
        public Color (int r, int g, int b) {
            this.r = (short) r;
            this.g = (short) g;
            this.b = (short) b;
        }
        public String toString() {
            return new StringBuilder().append('(').append(r).append(',').
                    append(g).append(',').append(b).append(')').toString();
        }
    }
    
    private static void subjects( ) {
        Hashtable<String, String> courses = new Hashtable<String,String>();  
          
        courses.put("COMP1001", "PROBLEM SOLVING METHODOLOGY IN INFORMATION TECHNOLOGY");   
        courses.put("COMP2011", "DATA STRUCTURES");
        courses.put("COMP2021", "OBJECT-ORIENTED PROGRAMMING");
        courses.put("COMP2121", "E-BUSINESS");
        courses.put("COMP2411", "DATABASE SYSTEMS");
        courses.put("COMP2421", "COMPUTER ORGANIZATION");
        courses.put("COMP3011", "DESIGN AND ANALYSIS OF ALGORITHMS");
        courses.put("COMP305",  "DATA STRUCTURES AND ALGORITHMS");
        courses.put("COMP312",  "COMPUTER COMMUNICATIONS NETWORKS");
        courses.put("COMP3122", "INFORMATION SYSTEMS DEVELOPMENT");
        courses.put("COMP3134", "BUSINESS INTELLIGENCE AND CUSTOMER RELATIONSHIP MANAGEMENT");
        courses.put("COMP3134", "BUSINESS INTELLIGENCE AND CUSTOMER RELATIONSHIP MANAGEMENT ");
        courses.put("COMP320",  "INTRODUCTION TO INTERNET COMPUTING");
        courses.put("COMP3211", "SOFTWARE ENGINEERING");
        courses.put("COMP3211", "SOFTWARE ENGINEERING");
        courses.put("COMP3235", "SOFTWARE PROJECT MANAGEMENT");
        courses.put("COMP3334", "COMPUTER SYSTEMS SECURITY");
        courses.put("COMP3335", "DATABASE SECURITY");
        courses.put("COMP3421", "WEB APPLICATION DESIGN AND DEVELOPMENT");
        courses.put("COMP3421", "WEB APPLICATION DESIGN AND DEVELOPMENT");
        courses.put("COMP3432", "INNOVATIVE COMPUTING PARADIGMS");
        courses.put("COMP407",  "COMPUTER GRAPHICS");
        courses.put("COMP4122", "GAME DESIGN AND DEVELOPMENT");
        courses.put("COMP4123", "BUSINESS PROCESS AND WORKFLOW MANAGEMENT");
        courses.put("COMP4133", "INFORMATION RETRIEVAL");
        courses.put("COMP4135", "KNOWLEDGE AND INFORMATION MANAGEMENT");
        courses.put("COMP4334", "PRINCIPLES AND PRACTICE OF INTERNET SECURITY");
        courses.put("COMP4422", "COMPUTER GRAPHICS");
        courses.put("COMP4431", "ARTIFICIAL INTELLIGENCE");
        courses.put("COMP4433", "DATA MINING AND DATA WAREHOUSING");
        courses.put("COMP4434", "BIG DATA ANALYTICS");
        courses.put("COMP4438", "EMBEDDED SOFTWARE");
        courses.put("COMP4442", "SERVICE AND CLOUD COMPUTING");
        courses.put("COMP4512", "INTELLECTUAL PROPERTY PROTECTION AND MANAGEMENT");
        courses.put("COMP5111", "DATABASE SYSTEMS AND MANAGEMENT");
        courses.put("COMP5121", "DATA MINING AND DATA WAREHOUSING APPLICATIONS");
        courses.put("COMP5122", "E-COMMERCE FUNDAMENTALS AND DEVELOPMENT");
        courses.put("COMP5131", "INTRODUCTION TO INFORMATION SYSTEMS");
        courses.put("COMP5132", "INFORMATION SYSTEMS ACQUISITION AND INTEGRATION");
        courses.put("COMP5133", "INFORMATION SYSTEMS AND E-COMMERCE STRATEGY");
        courses.put("COMP5134", "INFORMATION SYSTEM DEVELOPMET WITH OO METHODS");
        courses.put("COMP5135", "INFORMATION SYSTEMS AUDIT AND CONTROL");
        courses.put("COMP5138", "SERVICES SCIENCE MANAGEMENT");
        courses.put("COMP5220", "INFORMATION SYSTEMS PROJECT MANAGEMENT");
        courses.put("COMP5222", "SOFTWARE TESTING AND QUALITY ASSURANCE");
        courses.put("COMP5311", "INTERNET INFRASTRUCTURE AND PROTOCOLS");
        courses.put("COMP5322", "INTERNET COMPUTING AND APPLICATIONS");
        courses.put("COMP5325", "DISTRIBUTED COMPUTING");
        courses.put("COMP5326", "WIRELESS COMPUTING SYSTEMS AND APPLICATIONS");
        courses.put("COMP5331", "WEB ADVERTISING AND WEB PUBLISHING");
        courses.put("COMP5332", "WEB SERVICES AND PROJECT DEVELOPMENT");
        courses.put("COMP5412", "FUNDAMENTALS OF CHINESE COMPUTING");
        courses.put("COMP5422", "MULTIMEDIA COMPUTING, SYSTEMS AND APPLICATIONS");
        courses.put("COMP5422", "MULTIMEDIA COMPUTING, SYSTEMS AND APPLICATIONS");
        courses.put("COMP5517", "HUMAN COMPUTER INTERACTION");
        courses.put("COMP5538", "CUSTOMER RELATIONSHIP MANAGEMENT AND TECHNOLOGY");

        courses.put("COMP2011", "DATA STRUCTURES AND ALGORITHMS");
          
        System.out.println(courses.containsKey("COMP0000"));
        System.out.println(courses.get("COMP2011"));

        /*
        for(Map.Entry<String, String> m:courses.entrySet())
            System.out.println(m.getKey() + " " + m.getValue());
         */
    }
    
    /*
     * Information on the RGB color model: https://en.wikipedia.org/wiki/RGB_color_model  
     */
     private static void color( ) {
        Map<String, Color> map = new HashMap<String, Color>();
        map.put("Aquamarine", new Color(0x7F, 0xFF, 0xD0));
        map.put("Black", new Color(0, 0, 0));
        map.put("white", new Color(255, 255, 255));
        map.put("Cardinal", new Color(0xC4, 0x1E, 0x3A));
        map.put("Cyan", new Color(0x00, 0xFF, 0xFF));
        map.put("Dark blue", new Color(0x00, 0x00, 0x8B));
        map.put("Forest green", new Color(0x01, 0x44, 0x21));
        map.put("Magenta", new Color(0xFF, 0x00, 0xFF));
        map.put("Maroon", new Color(0x80, 0x00, 0x00));
        // https://www.polyu.edu.hk/web/en/about_polyu/university_identity/
        map.put("PolyU Red", new Color(160, 35, 55));
        map.put("PolyU Grey", new Color(88, 89, 91));
        map.put("Royal blue", new Color(0x00, 0x23, 0x66));
        map.put("Silver", new Color(0xC0, 0xC0, 0xC0));
        map.put("Turquoise", new Color(0x30, 0xD5, 0xC8));
        map.put("Violet", new Color(0x8F, 0x00, 0xFF));
        
        System.out.println("The color code of PolyU Red is " + map.get("PolyU Red"));
    }
    
    /*
     * Information on Internet Protocol version 4: https://en.wikipedia.org/wiki/IPv4
     * and the Domain Name System (DNS): https://en.wikipedia.org/wiki/Domain_Name_System
     */
     private static void dns( ) {
        Map<String, IP> map = new HashMap<String, IP>();
        map.put("www.polyu.edu.hk", new IP(158, 132, 48, 76));
        map.put("www.comp.polyu.edu.hk", new IP(158, 132, 20, 226));
        map.put("www.google.com", new IP(172, 217, 24, 68));
        map.put("www.youtube.com", new IP(216, 58, 200, 78));
        map.put("www.facebook.com", new IP(31, 13, 77, 35));
        map.put("www.amazon.com", new IP(13, 224, 154, 9));
        map.put("www.google.com.hk", new IP(216, 58, 199, 3));
        map.put("www.wikipedia.org", new IP(103, 102, 166, 224));
        map.put("www.zoom.us", new IP(3, 235, 71, 135));
        map.put("www.hktvmall.com", new IP(14, 198, 244, 42));
        map.put("www.cti.hk", new IP(14, 198, 244, 42));
        map.put("www.whatsapp.com", new IP(31, 13, 77, 60));

        System.out.println("The IP address of wikipedia is " + map.get("www.wikipedia.org"));
    }
    
    public static void main(String[] args) {
        subjects();
        dns();
        color();
    }

}
