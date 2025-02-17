/*
Comparable
Реализуй интерфейс Comparable<Beach> в классе Beach. Пляжи(Beach) будут использоваться нитями,
поэтому позаботься, чтобы все методы были синхронизированы.
Реализуй метод compareTo так, чтобы при сравнении двух пляжей он выдавал:
– положительное число, если первый пляж лучше;
– отрицательное число, если второй пляж лучше;
– ноль, если пляжи одинаковые.
Сравни каждый критерий по отдельности, чтобы победителем был пляж с лучшими показателями по большинству критериев. Учти при сравнении, чем меньше расстояние к пляжу (distance), тем пляж лучше.


Requirements:
1. Класс Beach должен содержать три поля: String name, float distance, int quality.
2. Класс Beach должен реализовывать интерфейс Comparable<Beach>.
3. Метод compareTo класса Beach должен учитывать качество пляжа (quality) и дистанцию (distance).
4. Все методы класса Beach, кроме метода main, должны быть синхронизированы.
*/

public class Beach implements Comparable<Beach> {
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;

        }

        public synchronized String getName() {
            return name;
        }

        public synchronized void setName(String name) {
            this.name = name;
        }

        public synchronized float getDistance() {
            return distance;
        }

        public synchronized void setDistance(float distance) {
            this.distance = distance;
        }

        public synchronized int getQuality() {
            return quality;
        }

        public synchronized void setQuality(int quality) {
            this.quality = quality;
        }

        @Override
        public synchronized int compareTo(Beach other) {

            if (this.quality > other.quality) {
                return 1;
            } else if (this.quality < other.quality) {
                return -1; // другой пляж лучше
            } else {

                if (this.distance < other.distance) {
                    return 1;
                } else if (this.distance > other.distance) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

        public static void main(String[] args) {

            Beach beach1 = new Beach("Пляж 1", 100, 5);
            Beach beach2 = new Beach("Пляж 2", 150, 5);
            Beach beach3 = new Beach("Пляж 3", 80, 4);

            System.out.println(beach1.compareTo(beach2));
            System.out.println(beach1.compareTo(beach3));
            System.out.println(beach2.compareTo(beach3));
            System.out.println(beach1.compareTo(beach1));
        }
    }