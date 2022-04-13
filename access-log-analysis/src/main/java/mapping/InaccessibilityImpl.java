package mapping;

import lombok.AllArgsConstructor;
import mapping.view.ViewFile;
import values.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * класс для анализа файла.Содержит методы проверки не соответсвующих логов и выдает список
 */
@AllArgsConstructor
public class InaccessibilityImpl implements Inaccessibility {
    private  AccessLog[] accessLogs;
    private  Float minPercentOfInaccessibility;
    private   Float minTimeOfInaccessibility;
    private final static Long DEFAULT_INTERVAL = 2l;
    private  final Hashtable<Integer, Integer> interval = new Hashtable<>();

    public InaccessibilityImpl() {

        AccessLogList accessLoglist = new AccessLogListImpl();
        accessLogs = accessLoglist.getAccessLogList();

        for (AccessLog s :
                accessLogs) {
            System.out.println("listfield " + s);
        }

        MinPercentOfInaccessibility minPercent = new MinPercentOfInaccessibilityImpl();
        minPercentOfInaccessibility = minPercent.getMinPercentOfInaccessibility();
        System.out.println("minPercent "+minPercentOfInaccessibility);

        MinTimeOfInaccessibility minTime = new MinTimeOfInaccessibilityImpl();
        minTimeOfInaccessibility = minTime.getMinTimeOfInaccessibility();
        System.out.println("minTime "+minTimeOfInaccessibility);

    }
    @Override
    public Hashtable<Integer, Integer> getInterval(){
        int begin = 0;
        int finish = 1;
        if (finish == accessLogs.length-1) {
            interval.put(begin, finish);
        }

        while (finish < accessLogs.length) {

            if (accessLogs[finish].getTime().toEpochSecond(ZoneOffset.from(ZonedDateTime.now())) -
                    (accessLogs[begin].getTime().toEpochSecond(ZoneOffset.from(ZonedDateTime.now()))) < DEFAULT_INTERVAL){
                finish = finish+1;

            }
            else{
                interval.put(begin,finish-1);
                begin = finish;
                finish = finish+1;

            }
        }
        interval.put(begin,finish-1);

        return interval;
    }

   @Override
    public ViewFile [] getAllInaccessibilityValue() {
        ViewFile[] viewFile = new ViewFile[accessLogs.length];
        Iterator<Map.Entry<Integer, Integer>> iterator = getInterval().entrySet().iterator();
        int i = 0;

        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            Integer key = next.getKey();
            Integer value = next.getValue();
            System.out.println(key + ":" + value);
            viewFile[i] = getMinPercentOfInaccessibility(key, value);
            i++;
        }

        return viewFile;
    }

    @Override
    public ViewFile getMinPercentOfInaccessibility(int begin, int finish){
        ViewFile result = null;
        if (finish != begin) {
            int countAccessibility = finish-begin+1;
            LocalDateTime beginTime = accessLogs[begin].getTime();
            LocalDateTime finishTime = accessLogs[finish].getTime();

            for (int i = begin; i <= finish; i++) {
                System.out.println("Сравниваем " + minTimeOfInaccessibility + " и " + accessLogs[i].getMs());
                if ((minTimeOfInaccessibility < accessLogs[i].getMs()) || (accessLogs[i].getStatus().equals("500"))) {
                    countAccessibility = countAccessibility - 1;
                }
            }
            System.out.println("Количество прошедших запросов: " + countAccessibility);

            float var = (((float) countAccessibility * 100) / (float) (finish-begin+1));

            System.out.println("Процент прошедших запросов: " + var);

            if (var < minPercentOfInaccessibility) {
                result = new ViewFile(beginTime, finishTime, var);
            }
        }
        else System.out.println("След значения: "+accessLogs[finish]+" не получилось проанализировать, так как они не входятв интервал.Увеличьте диапазон");
       return  result;
    }

}
