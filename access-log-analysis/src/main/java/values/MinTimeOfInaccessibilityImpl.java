package values;

import javax.management.NotificationFilter;
import java.io.NotActiveException;
import java.util.Scanner;

/**
 * Ввод минимального времени обратоки Put запроса
 */
public class MinTimeOfInaccessibilityImpl  implements MinTimeOfInaccessibility {
    @Override
    public Float getMinTimeOfInaccessibility()  {
        Float minTime = null;
        System.out.println("Введите допустимое время обратоки: ");
        Scanner scTime = new Scanner(System.in);
        if (scTime.hasNextFloat()) {
            minTime = scTime.nextFloat();
        } else {
           throw new  NotFindException("вы ввели недопустимое время обратоки") ;
        }
    return minTime;
    }
}
