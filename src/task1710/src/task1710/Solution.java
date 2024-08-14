package task1710.src.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
CRUD - Create, Read, Update, Delete.

Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-r id
-u id name sex bd
-d id

Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-r - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
-u - обновляет данные человека с данным id
-d - производит логическое удаление человека с id, заменяет все его данные на null

id соответствует индексу в списке.
Все люди должны храниться в allPeople.
Используй Locale.ENGLISH в качестве второго параметра для SimpleDateFormat.

Пример параметров:
-c Миронов м 15/04/1990

Пример вывода для параметра -r:
Миронов м 15-Apr-1990

Если программа запущена с параметрами, то они попадают в массив args (аргумент метода main - String[] args).
Например, при запуске программы c параметрами:
-c name sex bd
получим в методе main
args[0] = "-c"
args[1] = "name"
args[2] = "sex"
args[3] = "bd"
Для запуска кода с параметрами в IDE IntellijIDEA нужно их прописать в поле Program arguments в меню Run -> Edit Configurations.


Requirements:
1. Класс Solution должен содержать public static поле allPeople типа List<Person>.
2. Класс Solution должен содержать статический блок, в котором добавляются два человека в список allPeople.
3. При запуске программы с параметром -с программа должна добавлять человека с заданными параметрами в конец списка allPeople, и выводить id (index) на экран.
4. При запуске программы с параметром -r программа должна выводить на экран данные о человеке с заданным id по формату указанному в задании.
5. При запуске программы с параметром -u программа должна обновлять данные человека с заданным id в списке allPeople.
6. При запуске программы с параметром -d программа должна логически удалять человека с заданным id в списке allPeople.
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //напишите тут ваш код

        String command = args[0];
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        switch (command) {
            case "-c":
                try {
                    String name = args[1];
                    String gender = args[2];
                    Date birthDate = dateFormat.parse(args[3]);
                    Person person;

                    if (gender.equals("м")) {
                        person = Person.createMale(name, birthDate);
                    } else {
                        person = Person.createFemale(name, birthDate);
                    }
                    allPeople.add(person);
                    System.out.println(allPeople.size() - 1); // выводит ID
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;

            case "-r":
                int readId = Integer.parseInt(args[1]);
                if (readId >= 0 && readId < allPeople.size()) {
                    Person readPerson = allPeople.get(readId);
                    if (readPerson != null) {
                        String genderSymbol = readPerson.getSex() == Sex.MALE ? "м" : "ж";
                        String formattedBirthDate = outputDateFormat.format(readPerson.getBirthDate());
                        System.out.println(readPerson.getName() + " " + genderSymbol + " " + formattedBirthDate);
                    }
                }
                break;

            case "-u":
                int updateId = Integer.parseInt(args[1]);
                if (updateId >= 0 && updateId < allPeople.size()) {
                    try {
                        String updateName = args[2];
                        String updateGender = args[3];
                        Date updateBirthDate = dateFormat.parse(args[4]);
                        Person updatePerson;

                        if (updateGender.equals("м")) {
                            updatePerson = Person.createMale(updateName, updateBirthDate);
                        } else {
                            updatePerson = Person.createFemale(updateName, updateBirthDate);
                        }
                        allPeople.set(updateId, updatePerson);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case "-d":
                int deleteId = Integer.parseInt(args[1]);
                if (deleteId >= 0 && deleteId < allPeople.size()) {
                    allPeople.set(deleteId, null);
                }
                break;
        }
    }
}