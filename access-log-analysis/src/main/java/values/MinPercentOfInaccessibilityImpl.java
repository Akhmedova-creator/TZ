package values;

import lombok.AllArgsConstructor;

import java.util.Scanner;

/**
 * Вввод минимального допустимого процента обработанных Put запросов
 */
public class MinPercentOfInaccessibilityImpl implements MinPercentOfInaccessibility {
    @Override
    public Float getMinPercentOfInaccessibility() {
        Float minLevel = null;
        System.out.println("Введите минимально допустимый уровень доступности: ");
        Scanner scLevel = new Scanner(System.in);
        if (scLevel.hasNextFloat()) {
            float value = scLevel.nextFloat();
            if (value <= 0 || value >= 100 ) {
                System.out.println("Диапазон допустимого значения должен быть в интервале (0,100)");
            }
            else{
                minLevel =   value;
            }

            }
        else {
            throw new NotFindException("вы ввели недопустимый уровень доступности");
        }

        return minLevel;
        }


}
