import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scn.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input){
        // Конвертирует арабские числа в римские
        Converter converter = new Converter();
        // Массивы для определения математической операции
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {" \\+ ", " - ", " / ", " \\* "};
        // Проверяем математическую операцию
        int actionIndex=-1;
        for (int i = 0; i < actions.length; i++) {
            if(input.contains(actions[i])){
                actionIndex = i;
                break;
            }
        }
        if(actionIndex == -1){
            return "строка не является математической операцией";
        }

        if(input.split(" ").length > 3){
            return "формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        }

        //Делим строчку по найденному арифметическому знаку
        String[] data = input.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                //если римские, то конвертируем их в арабские
                //X+V
                //x-10
                //v - 5
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            }else{
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            if(a > 10 | b > 10){
                return "Числа должны быть не больше 10";
                
            }

            //выполняем с числами арифметическое действие
            int result;
            switch (actions[actionIndex]){
                case "+":
                    result = a+b;
                    break;
                case "-":
                    result = a-b;
                    break;
                case "*":
                    result = a*b;
                    break;
                default:
                    result = a/b;
                    break;
            }

            //15->XV
            if(isRoman){
                if(result < 0){
                    return "т.к. в римской системе нет отрицательных чисел";
                }
                //если числа были римские, возвращаем результат в римском числе
                return converter.intToRoman(result);
            }
            else{
                //если числа были арабские, возвращаем результат в арабском числе
                return String.valueOf(result);
            }
        }else{
            return "Числа должны быть в одном формате";
        }
        
    }
}