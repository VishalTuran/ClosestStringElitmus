import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ClosestString {
    public static ArrayList<String> CsvtoArrayList()
    {
        ArrayList<String> tobeReturned=new ArrayList<String>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        Scanner scanner=new Scanner(System.in);
        String filePath=scanner.next();

        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] leett = line.split(cvsSplitBy); //Removing commas from file

                tobeReturned.add(leett[0]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace(); //Handling FileNotFoundException
        } catch (IOException e) {
            e.printStackTrace(); //Handling IOException
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return tobeReturned;
    }

    public static void main(String[] args) {
        ArrayList<String> stringArrayList=new ArrayList<>(); //Initialising ArrayList to eradicate the chance of NullPointerException
        ArrayList<Integer> counts=new ArrayList<>();

       stringArrayList=CsvtoArrayList();  //Converting the csv file to arrayList

        Scanner scanner=new Scanner(System.in);

        String io=scanner.next();  //User input String


        //Finding the closest match for the input String by comparing its each character with the word from dictionary
        for (String string :stringArrayList) {
            String aString=string;
            int count=0;
            for (int i = 0; i < io.length(); i++) {
               Character character=io.charAt(i);
               if(aString.contains(character+""))
               {
                   count++;
               }
            }
            counts.add(count);
        }


        HashMap<String,Integer> hashMap=new HashMap<>();

        for (int i = 0; i < counts.size(); i++) {
            hashMap.put(stringArrayList.get(i),counts.get(i));
        }

        Set<Map.Entry<String, Integer>> set = hashMap.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
                set);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (int i = 0; i < 5; i++) {
            System.out.print(list.get(i).getKey()+",");
        }

//        for (Map.Entry<String, Integer> entry : list) {
//            System.out.print(entry.getKey()+",");
//        }



    }



}
