package com.example.alertsystem;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/*
public static void delPatientinfo(int roomCode) throws IOException {
    URL url = null;
    try {
        url = new URL(webserverUrl+"/deletePatientInfo.php?roomCode="+roomCode);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
    BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream())
    );
    in.close();
    }
    public static void addAlert(int roomCode, String alert, String timeof ) throws IOException {
    URL url = null;
    try {
        url = new URL(webserverUrl+"/insertAlert.php?roomCode="+roomCode+"&alert=\""+alert+"\"&timeOf=\""+timeof+"\"");
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
    BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream())
    );
    in.close();
}
 String s = store.substring(store.indexOf("roomCode:") + 9);
        s = s.substring(0, s.indexOf("/"));
        s = s.replace(" ", "");
        int temp = Integer.parseInt(s);
        list.add(temp);
        store = store.substring(store.indexOf("<br>")+4);
*/
public class webserver {
    final static String webserverUrl = "http://justinapt1.asuscomm.com";

    @RequiresApi(api = Build.VERSION_CODES.N)

    public static int getPercent(int roomCode) throws IOException {
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
        String s = "";

        try {
            s = store.substring(store.indexOf("percentage:") + 11);
            s = s.substring(0, s.indexOf("/"));
            s = s.replace(" ", "");
        } catch (Exception e) {
            return 0;
        }

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

    public static void setPercent(int roomCode, int percent) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/insUpdPerc.php?roomCode="+roomCode+"&percentage="+percent);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        in.close();
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
        String s = "";

        try {
            s = store.substring(store.indexOf("timeOfMov:") + 11);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return "Field Not Set";
        }

        in.close();
        return s;
    }

    public static int[] getMovRoomCodes() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selAllLMov.php");
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
        int[] roomCodes = new int[size];

        for(int i = 0; i < size; i++) {
            roomCodes[i] = list.get(i);
        }

        in.close();
        return roomCodes;
    }

    public static String[] getAllLMov() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selAllLMov.php");
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

        List<String> list = new ArrayList<String>();

        while(store.contains("<br>")) {
            String s = store.substring(store.indexOf("timeOfMov:") + 11);
            s = s.substring(0, s.indexOf("/"));
            list.add(s);
            store = store.substring(store.indexOf("<br>")+4);
        }
        int size = list.size();
        String[] LMov = new String[size];

        for(int i = 0; i < size; i++) {
            LMov[i] = list.get(i);
        }

        in.close();
        return LMov;
    }

    public static void setLMov(int roomCode, String timeOf) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/insUpdLMov.php?roomCode="+roomCode+"&timeOfMov="+timeOf);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        in.close();
    }

    public static void setLMovNoDate(int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/insUpdLMovNoDate.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        in.close();
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
        String s = "";

        try {
            s = store.substring(store.indexOf("lastPing:") + 10);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return "Field Not Set";
        }
        in.close();
        return s;
    }

    public static int[] getWifiRoomCodes() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selAllWifiS.php");
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
        int[] roomCodes = new int[size];

        for(int i = 0; i < size; i++) {
            roomCodes[i] = list.get(i);
        }

        in.close();
        return roomCodes;
    }

    public static String[] getAllWifiS() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selAllWifiS.php");
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

        List<String> list = new ArrayList<String>();

        while(store.contains("<br>")) {
            String s = store.substring(store.indexOf("lastPing:") + 10);
            s = s.substring(0, s.indexOf("/"));
            list.add(s);
            store = store.substring(store.indexOf("<br>")+4);
        }
        int size = list.size();
        String[] lastPing = new String[size];

        for(int i = 0; i < size; i++) {
            lastPing[i] = list.get(i);
        }

        in.close();
        return lastPing;
    }

    public static void setWifiS(int roomCode, String timeOf) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/insertWifiS.php?roomCode="+roomCode+"&lastPing="+timeOf);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        in.close();
    }

    public static void setWifiSNoDate(int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/insertWifiSNoDate.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        in.close();
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
        String s = "";

        try {
            s = store.substring(store.indexOf("patientName:") + 13);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return "Field Not Set";
        }
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
        String s = "";

        try {
            s = store.substring(store.indexOf("address:") + 9);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return "Field Not Set";
        }
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
        String s = "";

        try {
            s = store.substring(store.indexOf("phone:") + 7);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return "Field Not Set";
        }
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
        String s = "";

        try {
            s = store.substring(store.indexOf("emergencyName:") + 15);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return "Field Not Set";
        }
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
        String s = "";

        try {
            s = store.substring(store.indexOf("emergencyPhone:") + 16);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return "Field Not Set";
        }
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
        try {
            while (store.contains("<br>")) {
                String s = store.substring(store.indexOf("aID:") + 5);
                s = s.substring(0, s.indexOf("/"));
                int temp = Integer.parseInt(s);
                list.add(temp);
                store = store.substring(store.indexOf("<br>") + 4);
            }
        } catch (Exception e) {
            int[] empty = new int[1];
            return empty;
        }
        int size = list.size();
        int[] aIDS = new int[size];

        for(int i = 0; i < size; i++){
            aIDS[i] = list.get(i);
        }

        in.close();
        return aIDS;
    }

    public static int[] getAllAlertIDS () throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selAllAlert.php");
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
        try {
            while (store.contains("<br>")) {
                String s = store.substring(store.indexOf("aID:") + 5);
                s = s.substring(0, s.indexOf("/"));
                int temp = Integer.parseInt(s);
                list.add(temp);
                store = store.substring(store.indexOf("<br>") + 4);
            }
        } catch (Exception e) {
            int[] empty = new int[1];
            return empty;
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
        String s = "";

        try {
            s = store.substring(store.indexOf("roomCode:") + 10);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return 0;
        }
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
        String s = "";

        try {
            s = store.substring(store.indexOf("alert:") + 7);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return "Field Not Set";
        }

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
        String s = "";

        try {
            s = store.substring(store.indexOf("timeOf:") + 8);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return "Field Not Set";
        }

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
        String s = "";

        try {
            s = store.substring(store.indexOf("dismissed:") + 11);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return false;
        }
        boolean dismissed = false;
        if(s.contains("1")) {
            dismissed = true;
        } else {
            dismissed = false;
        }

        in.close();
        return dismissed;
    }

    public static void updateAlertDismissed(boolean dismissed, int aID) throws IOException {
        URL url = null;
        int disInt = 0;
        if (dismissed) {
            disInt = 1;
        } else {
            disInt = 0;
        }
        try {
            url = new URL(webserverUrl+"/updateAlertDismissed.php?aID="+aID+"&dismissed="+disInt);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        in.close();
    }


    public static void delPatientinfo(int roomCode) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/deletePatientInfo.php?roomCode="+roomCode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        in.close();
    }
    // Hard Coded Alert Test function
    public static void addAlert() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/insertAlert.php?roomCode=001&alert=\"Test\"&timeOf=\"2022-10-22 03:03:03\"");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        in.close();
    }

    public static int[] getcodesallpatientInfo() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selAllPatientInfo.php");
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

        try {
            while (store.contains("<br>")) {
                String s = store.substring(store.indexOf("roomCode:") + 9);
                s = s.substring(0, s.indexOf("/"));
                s = s.replace(" ", "");
                int temp = Integer.parseInt(s);
                list.add(temp);
                store = store.substring(store.indexOf("<br>") + 4);
            }
        } catch (Exception e) {
            int[] empty = new int[1];
            return empty;
        }
        int size = list.size();
        int[] percentages = new int[size];

        for(int i = 0; i < size; i++) {
            percentages[i] = list.get(i);
        }

        in.close();
        return percentages;
    }

    public static void setPatientinfo(String patientName, String address,String emergencyName, int roomCode,String emergencyPhone, String phone) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/insertPatientInfo.php?roomCode="+roomCode+"&patientName=\""+patientName+"\"&address=\""+address+"\"&emergencyName=\""+emergencyName+"\"&emergencyPhone=\""+emergencyPhone+"\"&phone=\""+phone+"\"");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        in.close();
    }

    public static String[] getCodeandname() throws IOException {
        int[] arr = getcodesallpatientInfo();
        String[] lines = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String Temp ="";
            Temp = Integer.toString(arr[i]);
            lines[i] = "Room Code: " + Temp + "       Name: " + getPatientName(arr[i]);
        }

        return lines;
    }

    public static int getenumofEnt() throws IOException {
        int[] arr = getcodesallpatientInfo();
        int ent = arr.length;
        return ent;
    }

    public static int getenumofaID() throws IOException {
        int[] arr = getAllAlertIDS();
        int ent = arr.length;
        return ent;
    }

    public static String[] getaIDandName() throws IOException {
        int[] arr = getAllAlertIDS();
        String[] lines = new String[arr.length];

        for (int i = 0; i < arr.length; i++) {
            lines[i] = "Room Code: " + getAlertRoomCode(arr[i]) + "       Type: " + getAlertAlert(arr[i]);
        }
        return lines;
    }


    public static int[] alertchecker() throws IOException {
        int[] arr = getAllAlertIDS();
        int[] num = new int[arr.length];

        boolean dis;
        int j = 0;

        for (int i = 0; i < arr.length; i++) {
            dis = getAlertDismissed(arr[i]);

            if (dis == false) {
                num[j] = arr[i];
                j++;
            }

        }


        return num;
    }


    public static int[] getAllRoomCodesAllTables() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectAllRoomCodes.php");
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
        int[] roomCodes = new int[size];

        for(int i = 0; i < size; i++) {
            roomCodes[i] = list.get(i);
        }

        in.close();
        return roomCodes;
    }

    public static int getPercentParameter() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectParameters.php");
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
        String s = "";
        try {
            s = store.substring(store.indexOf("percentPara:") + 13);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return 0;
        }

        int percentPara = Integer.parseInt(s);

        return percentPara;
    }

    public static int getWifiParameter() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectParameters.php");
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
        String s = "";
        try {
            s = store.substring(store.indexOf("pingPara:") + 10);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return 0;
        }

        int pingPara = Integer.parseInt(s);

        return pingPara;
    }

    public static int getMovementParameter() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectParameters.php");
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
        String s = "";
        try {
            s = store.substring(store.indexOf("movePara:") + 10);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return 0;
        }

        int movePara = Integer.parseInt(s);

        return movePara;
    }

    public static int getNotificationParameter() throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/selectParameters.php");
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
        String s = "";
        try {
            s = store.substring(store.indexOf("notificationPara:") + 18);
            s = s.substring(0, s.indexOf("/"));
        } catch (Exception e) {
            return 0;
        }

        int notifPara = Integer.parseInt(s);

        return notifPara;
    }

    public static void setPercentParameter(int percent) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/insertPercPara.php?percPara="+percent);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
    }

    public static void setWifiParameter(int ping) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/insertPingPara.php?pingPara="+ping);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
    }

    public static void setMoveParameter(int move) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/insertMovePara.php?movePara="+move);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
    }

    public static void setNotificationParameter(int notification) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/insertNotificationPara.php?notificationPara="+notification);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
    }

    public static String[] iDnameaid(boolean a) throws IOException {
        int[] arr = getAllAlertIDS();
        int cnt = 0;

        if (a == false) { //non dissmissed
            int len = iDelength(false);
            String[] lines = new String[len];
            for (int i = 0; i < arr.length; i++) {
                if (getAlertDismissed(arr[i]) == false) {
                    lines[cnt] = "Room Code: " + getAlertRoomCode(arr[i]) + "       Type: " + getAlertAlert(arr[i]);
                    cnt++;
                }
            }
            return lines;
        }

        else { //dissmissed
            int len = iDelength(true);
            String[] lines = new String[len];
            for (int i = 0; i < arr.length; i++) {
                if (getAlertDismissed(arr[i]) == true) {
                    lines[cnt] = "Room Code: " + getAlertRoomCode(arr[i]) + "       Type: " + getAlertAlert(arr[i]);
                    cnt++;
                }
            }
            return lines;
        }
    }


    public static int iDelength(boolean a) throws IOException {
        int[] arr = getAllAlertIDS();
        int num = 0;

        if (a == false) { //non dissmissed
            for (int i = 0; i < arr.length; i++) {
                if (getAlertDismissed(arr[i]) == false) {
                    num++;
                }
            }
            return num;
        }

        else { //dissmissed
            for (int i = 0; i < arr.length; i++) {
                if (getAlertDismissed(arr[i]) == true) {
                    num++;
                }
            }
            return num;
        }
    }

    public static int[] iDepos(boolean a) throws IOException {
        int[] arr = getAllAlertIDS();
        int cnt = 0;

        if (a == false) { //non dissmissed
            int len = iDelength(false);
            int num[] = new int[len];
            for (int i = 0; i < arr.length; i++) {
                if (getAlertDismissed(arr[i]) == false) {
                    num[cnt] = arr[i];
                    cnt++;
                }

            }
            return num;
        }

        else { //dissmissed
            int len = iDelength(true);
            int num[] = new int[len];
            for (int i = 0; i < arr.length; i++) {
                if (getAlertDismissed(arr[i]) == true) {
                    num[cnt] = arr[i];
                    cnt++;
                }
            }
            return num;
        }
    }

    public static void upPatientinfo(String patientName, String address,String emergencyName, int roomCode,String emergencyPhone, String phone) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/updatePatientInfo.php?roomCode="+roomCode+"&patientName=\""+patientName+"\"&address=\""+address+"\"&emergencyName=\""+emergencyName+"\"&emergencyPhone=\""+emergencyPhone+"\"&phone=\""+phone+"\"");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        in.close();
    }
    public static void delAlert(int aID) throws IOException {
        URL url = null;

        try {
            url = new URL(webserverUrl+"/deleteAlert.php?aID="+aID);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream())
        );
        in.close();
    }
    //Webserver
    public static String[] getCodeandEname() throws IOException {
        int[] arr = getcodesallpatientInfo();
        int len = 0;
        int cnt = 0;

        for (int i = 0; i < arr.length; i++) {
            if (getPatientName(arr[i]) != "Field Not Set") {
                len++;
            }
        }
        int[] arr2 = new int[len];

        for (int i = 0; i < arr.length; i++) {
            if (getPatientName(arr[i]) != "Field Not Set") {
                arr2[cnt] = arr[i];
                cnt++;
            }
        }

        String[] lines = new String[len];
        for (int i = 0; i < len; i++) {
            String Temp ="";
            Temp = Integer.toString(arr2[i]);
            lines[i] = "Room Code: " + Temp + "       Name: " + getPatientName(arr2[i]);
        }

        return lines;
    }


    public static int[] getCodeEarr() throws IOException {
        int[] arr = getcodesallpatientInfo();
        int len = 0;
        int cnt = 0;

        for (int i = 0; i < arr.length; i++) {
            if (getPatientName(arr[i]) != "Field Not Set") {
                len++;
            }
        }
        int[] arr2 = new int[len];

        for (int i = 0; i < arr.length; i++) {
            if (getPatientName(arr[i]) != "Field Not Set") {
                arr2[cnt] = arr[i];
                cnt++;
            }
        }
        return arr2;

    }

    public static int getElenz() throws IOException {
        int[] arr = getcodesallpatientInfo();
        int len = 0;

        for (int i = 0; i < arr.length; i++) {
            if (getPatientName(arr[i]) != "Field Not Set") {
                len++;
            }
        }
        return len;
    }




}
