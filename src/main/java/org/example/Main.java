

//  Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут быть заменены знаком вопроса,
//  например 2? + ?5 = 69. Требуется восстановить выражение до верного равенства. Предложить хотя бы одно
//  решение или сообщить, что его нет.

package org.example;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int n = 3;
        System.out.println("Enter an expression for example  2? + ?5 = 69");

        String[] str = new String[n];

        for (int i = 0; i < n; i++) {
            // System.out.println("Input number " + i);
            str[i] = sc.nextLine();
        }

        int l0 = str[0].length(); // длина первого числа
        int l1 = str[1].length(); // длина второго числа
        int l2 = str[2].length(); // длина третьего числа (после равно)

        int max = l0;
        if ( l1 > max){ max = l1;}
        if ( l2 > max){ max = l2;}
        //System.out.println("max lenght = " + max); // число с максимальной длиной
        int maxout = max;  // дубликат числа с максимальной длиной

        int l0plus = max - l0; // сколько нолей добавить первому числу
        int l1plus = max - l1; // сколько нолей добавить второму числу
        int l2plus = max - l2; // сколько нолей добавить третьему числу

        while (l0plus > 0) {
            str[0] = 0 + str[0];
            l0plus = l0plus - 1;}  // создали число 1 длиной max

        while (l1plus > 0) {
            str[1] = 0 + str[1];
            l1plus = l1plus - 1;}  // создали число 2 длиной max

        while (l2plus > 0) {
            str[2] = 0 + str[2];
            l2plus = l2plus - 1;}  // создали число 3 длиной max

        System.out.println(str[0] + " + " + str[1] + " = " + str[2]); // распечатали неравенство к решению

        int count = 0; // счетчик разрядов
        int y = 0; // временная память, если в верхнем регистре вместо цифры стоит "?"
        int z0 = 0; // для преобразования char0 в число
        int z1 = 0; // для преобразования char1 в число
        int z2 = 0; // для преобразования char2 в число


        String out0 = new String(); // Для записи искомого значения первой цифры
        String out1 = new String(); // Для записи искомого значения второй цифры
        String out2 = new String(); // Для записи искомого значения третьей цифры

        for ( int i = 1; i <= max; i++){ // перебор чисел по длине

            int char0 = str[0].charAt(max-i); // перебор char0 справа налево (от max индекса до 0)
            int char1 = str[1].charAt(max-i); // перебор char1 справа налево (от max индекса до 0)
            int char2 = str[2].charAt(max-i); // перебор char2 справа налево (от max индекса до 0)


            // ситуации, когда все три неизвестное
            if (char0 == '?' && char1 == '?' && char2 == '?' && count == 0) { // если ?(0) + ?(1) =?(2), то все нули
                char0 = 48;
                char1 = 48;
                char2 = 48;
            }
            if (char0 == '?' && char1 == '?' && char2 == '?' && count > 0) {
                char0 = 48; // для верхних разрядов  только  ?(0) = 0
            }


            // Назначаем сумму равной одному из слагаемых. Второе слагаемое будем подбирать
            if (char2 == '?'&& char0 == '?') { // если ?(0) + Y = ?(2), то ?(2) = Y
                char2 = char1;
            }
            if (char2 == '?'&& char1 == '?') { // если X + ?(1) = ?(2), то ?(2) = X
                char2 = char0;
            }

            // определяем сумму, если известны оба слагаемых (десяток в уме)
            if (char2 == '?') { // если X + Y = ?(2), то ?(2) = X + Y
                char2 = char0 + char1 - 48 ;
                if(char2 > 58) {
                    char2 = char2 - 10;
                    y = 1;// один десяток переходит на следующий уровень
                }
            }



            // если оба слагаемых неизвестны, одно из них назначим нулем, второе будем искать
            if (char0 == '?' && char1 == '?') { // если ?(0) + ?(1) = X, то ?(1) = X
                char0 = 48;
             }

            // находим слагаемое char0, когда есть сумма и второе слагаемое (с учетом десятка в уме)
            if (char0 == '?') {  // если ?(0) + Y = X, то ?(0) = X - Y
                char0 = 48 + char2 - char1 - y ; //  Y - это если перешел десяток с предыдущей операции
                y = 0;// если условие сработало, обнуляем десяток
                if(char0 < 48){char0 = char0 + 10;
                }
            }

            // находим слагаемое char1, когда есть сумма и второе слагаемое (с учетом десятка в уме)
            if (char1 == '?') { // если Y + ?(1) = X, то ?(1) = X - Y
                char1 = 48 + char2 - char0 - y;  //  Y - это если перешел десяток с предыдущей операции
                y = 0;// если условие сработало, обнуляем десяток
                if(char1 < 48){char1 = char1 + 20;
                }
            }

            z0 = Character.getNumericValue(char0); // преобразуем char0 в цифру
            z1 = Character.getNumericValue(char1); // преобразуем char0 в цифру
            z2 = Character.getNumericValue(char2); // преобразуем char0 в цифру

            out0 = z0 + out0; // собираем число 1 из цифр
            out1 = z1 + out1; // собираем число 2 из цифр
            out2 = z2 + out2; // собираем число 3 из цифр (значение = )

            count = 2; // Мы ушли от нулевого разряда к верхним. Счетчик закончил работу


        }
        int num0 = Integer.parseInt(out0); // переводим строки в числа
        int num1 = Integer.parseInt(out1);
        int num2 = Integer.parseInt(out2);

        if (num0 + num1 == num2) {
            System.out.println("True");
        }
        else{
            System.out.println("False");
        }
        System.out.println("text = " + out0 + "+ " + out1 + " = " + out2 );
        System.out.println("_______________________");


    }
}