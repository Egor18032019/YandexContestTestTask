import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CachingRequests {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(
                new InputStreamReader(System.in));
        HashMap<String, Integer> requestAndTime = new HashMap<>();
        int numberRequest = 0;
        int cashSize = 0;
        String str = "read";
        String typeRequest = "";
        int timeRequest = 0;
        Set<String> keys;
        LinkedList <String> answer = new LinkedList <>();
        String text = "";
        int iterator =0;
        do {
            str = r.readLine();
            if (str == null || str.length() == 0) {
                System.out.println("continue");
                continue;
            }
            String[] foo = str.split(" ");
            if (numberRequest == 0 && cashSize == 0) {
//                System.out.println(str);
                numberRequest = Integer.parseInt(foo[0]);
                cashSize = Integer.parseInt(foo[1]);

            } else {
                typeRequest = foo[0];
                timeRequest = Integer.parseInt(foo[1]);
                if (requestAndTime.isEmpty()) {
                    requestAndTime.put(typeRequest, timeRequest);
                    iterator++;
                    text = iterator +" PUT " + typeRequest;
                    answer.add(text);
                } else {
                    if (requestAndTime.size() < cashSize) {
                        keys = requestAndTime.keySet();
                        if (!keys.contains(typeRequest)) {
//                            System.out.println("добавили " + typeRequest + " " + timeRequest);
                            requestAndTime.put(typeRequest, timeRequest);
                            iterator++;
                            text = iterator +" PUT " + typeRequest;
                            answer.add(text);
                        } else {
//                            System.out.println("Уже есть такой ключ " + typeRequest);
                            if (requestAndTime.get(typeRequest) < timeRequest) {
                                requestAndTime.put(typeRequest, timeRequest);
                                iterator++;
                                text = iterator+ " UPDATE " + typeRequest;
                                answer.add(text);
                            }
                        }
                    } else {
                        // HashMap/кэш уже заполнился
                        // надо по значению найти самое маленькое время
                        // и перезаписать это ключ/значение на новый запрос
                        String minKeys = getMapKeyWithMinValue(requestAndTime);
//                        System.out.println("минимальный ключ" + minKeys);
                        // здесь еще нужна проверка на то что значение ключа < timeRequest

//                        System.out.println(requestAndTime.get(minKeys) + " " + timeRequest);
                        /* Тут нужна проверка что есть и такой же ключ в кэш/requestAndTime
                        Операция PUT применяется только к запросам, которых нет в кэше на момент совершения операции,
                                а UPDATE - только к уже находящимся в кэше.
                        */
                        keys = requestAndTime.keySet();
                        if (keys.contains(typeRequest) && requestAndTime.get(typeRequest) < timeRequest) {
                            requestAndTime.put(typeRequest, timeRequest);
                            iterator++;
                            text = iterator+ " UPDATE " + typeRequest;
                            answer.add(text);
                        } else {
                            if (requestAndTime.get(minKeys) < timeRequest) {
                                iterator++;
                                text =iterator+ " DELETE " + minKeys;
                                answer.add(text);
                                requestAndTime.remove(minKeys);
                                text =iterator+ " PUT " + typeRequest;
                                answer.add(text);
                                requestAndTime.put(typeRequest, timeRequest);
                            }
                        }
                    }
                }
            }

        }
        while (r.ready());
        for (Object o : answer) {
            System.out.println(o);
        }

        r.close();
    }

    static public String getMapKeyWithMinValue(HashMap<String, Integer> map) {
        String keyWithMinimumVal = "";

        // getting the maximum value in the Hashmap
        int minValueInMap = (Collections.min(map.values()));

        //iterate through the map to get the key that corresponds to the maximum value in the Hashmap
        for (Map.Entry<String, Integer> entry : map.entrySet()) {  // Iterate through hashmap
            if (entry.getValue() == minValueInMap) {

                keyWithMinimumVal = entry.getKey();     // this is the key which has the max value
            }

        }
        return keyWithMinimumVal;
    }
}
