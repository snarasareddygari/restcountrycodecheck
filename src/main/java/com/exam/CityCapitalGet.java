package com.exam;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * com.exam.CityCapitalGet is the main class which takes name or code as a input
 * and provide response with Capital city and other details.
 */
    public class CityCapitalGet {

//        private static final Logger log = LogManager.getLogger(com.exam.CityCapitalGet.class);
        public static void main(String[] args) {
            try {
                while (true) {
                    System.out.println("Enter a code/name : ");
                    Scanner scanner = new Scanner(System.in);
                    String inputString = scanner.nextLine();
                    String urlString = "https://restcountries.eu/rest/v2/name/" + inputString;
                    if(inputString.length() <= 3 ) {
                        urlString = "https://restcountries.eu/rest/v2/alpha/"+inputString;
                    }
                    System.out.println("Rest url call : " + urlString +" \n");
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");

                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));

                    String output;
                    while ((output = br.readLine()) != null) {
                        System.out.println("Capital City details : \n");
                        System.out.println(output +"\n");
                    }
                }

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }