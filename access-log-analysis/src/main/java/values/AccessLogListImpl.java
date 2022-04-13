package values;

import mapping.AccessLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ввод содержимого файла на консоль
 */
public class AccessLogListImpl implements AccessLogList{
    @Override
    public AccessLog[] getAccessLogList() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss");
        ArrayList<String> listFile = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (!sc.hasNext("end")) {
            listFile.add(sc.nextLine());
        }

        int ind = 0;
        AccessLog[] accessLog = new AccessLog[listFile.size()];
        for (String e : listFile) {
            String[] mainValue = e.split(" ");
            if(mainValue.length>=10) {
                accessLog[ind] = new AccessLog(LocalDateTime.parse(mainValue[3].substring(1), formatter),
                        mainValue[8], Float.parseFloat(mainValue[10]));

                ind++;
            }
            else throw new  NotFindException("Недопустимый список значений") ;

        }

     return accessLog;
    }
}
