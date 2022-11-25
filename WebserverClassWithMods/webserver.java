package com.example.teamhproject;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class webserver {
final static String webserverUrl = "http://justinapt1.asuscomm.com";

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getPercent(int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectPercent.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }

        String s = store.substring(store.indexOf("percentage:") + 11);
        s = s.substring(0, s.indexOf("/"));
        s = s.replace(" ", "");

        int percentage = Integer.parseInt(s);
        in.close();
        return percentage;
    }

    public int[] getPercentages() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selAllPerc.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }

        List<Integer> list = new ArrayList<Integer>();

        while(store.contains("<br>")) {
            String s = store.substring(store.indexOf("percentage:") + 11);
            s = s.substring(0, s.indexOf("/"));
            s = s.replace(" ", "");
            int temp = Integer.parseInt(s);
            list.add(temp);
            store = store.substring(store.indexOf("<br>")+4);
        }
        int size = list.size();
        int[] percentages = new int[size];

        for(int i = 0; i < size; i++) {
            percentages[i] = list.get(i);
        }

        in.close();
        return percentages;
    }

    public int[] getPercRoomCodes() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selAllPerc.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }

        List<Integer> list = new ArrayList<Integer>();

        while(store.contains("<br>")) {
            String s = store.substring(store.indexOf("roomCode:") + 9);
            s = s.substring(0, s.indexOf("/"));
            s = s.replace(" ", "");
            int temp = Integer.parseInt(s);
            list.add(temp);
            store = store.substring(store.indexOf("<br>")+4);
        }
        int size = list.size();
        int[] percentages = new int[size];

        for(int i = 0; i < size; i++) {
            percentages[i] = list.get(i);
        }

        in.close();
        return percentages;
    }

    public static String getTimeOfMov(int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectLMov.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }

        String s = store.substring(store.indexOf("timeOfMov:") + 11);
        s = s.substring(0, s.indexOf("/"));

        in.close();
        return s;
    }

    public static String getTimeOfWifi(int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectWifiS.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }

        String s = store.substring(store.indexOf("lastPing:") + 10);
        s = s.substring(0, s.indexOf("/"));

        in.close();
        return s;
    }

    public static String getPatientName (int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectPatientInfo.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }

        String s = store.substring(store.indexOf("patientName:") + 13);
        s = s.substring(0, s.indexOf("/"));

        in.close();
        return s;
    }

    public static String getPatientAddress (int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectPatientInfo.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }

        String s = store.substring(store.indexOf("address:") + 9);
        s = s.substring(0, s.indexOf("/"));

        in.close();
        return s;
    }

    public static String getPatientPhone (int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectPatientInfo.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }

        String s = store.substring(store.indexOf("phone:") + 7);
        s = s.substring(0, s.indexOf("/"));

        in.close();
        return s;
    }

    public static String getPatientEName (int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectPatientInfo.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }

        String s = store.substring(store.indexOf("emergencyName:") + 15);
        s = s.substring(0, s.indexOf("/"));

        in.close();
        return s;
    }

    public static String getPatientEPhone (int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectPatientInfo.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }

        String s = store.substring(store.indexOf("emergencyPhone:") + 16);
        s = s.substring(0, s.indexOf("/"));

        in.close();
        return s;
    }

    public int[] getAlertIDS (int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectAlert.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }

        List<Integer> list = new ArrayList<Integer>();

        while(store.contains("<br>")) {
            String s = store.substring(store.indexOf("aID:") + 5);
            s = s.substring(0, s.indexOf("/"));
            int temp = Integer.parseInt(s);
            list.add(temp);
            store = store.substring(store.indexOf("<br>")+4);
        }
        int size = list.size();
        int[] aIDS = new int[size];

        for(int i = 0; i < size; i++){
            aIDS[i] = list.get(i);
        }

        in.close();
        return aIDS;
    }

    public static int getAlertRoomCode(int aID) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectAlertByID.php?aID="+aID);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }
        String s = store.substring(store.indexOf("roomCode:") + 10);
        s = s.substring(0, s.indexOf("/"));

        int ID = Integer.parseInt(s);

        in.close();
        return ID;
    }

    public static String getAlertAlert(int aID) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectAlertByID.php?aID="+aID);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }
        String s = store.substring(store.indexOf("alert:") + 7);
        s = s.substring(0, s.indexOf("/"));


        in.close();
        return s;
    }

    public static String getAlertTimeOf(int aID) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectAlertByID.php?aID="+aID);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }
        String s = store.substring(store.indexOf("timeOf:") + 8);
        s = s.substring(0, s.indexOf("/"));


        in.close();
        return s;
    }

    public static boolean getAlertDismissed (int aID) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectAlertByID.php?aID="+aID);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String inputLine;
        String store = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            store = inputLine;
        }
        String s = store.substring(store.indexOf("dismissed:") + 11);
        s = s.substring(0, s.indexOf("/"));

        boolean dismissed = false;
        if(s == "1") {
            dismissed = true;
        } else {
            dismissed = false;
        }

        in.close();
        return dismissed;
    }
}
