package task1703;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* 
Синхронизированные заметки
1. Класс Note будет использоваться нитями. Поэтому сделай так, чтобы обращения к листу
notes блокировали мьютекс notes, не this
2. Все System.out.println не должны быть заблокированы (синхронизированы), т.е. не должны
находиться в блоке synchronized


Requirements:
1. Метод addNote() должен добавлять записки в список notes.
2. Метод removeNote() должен удалять записку из списка notes.
3. В методе addNote() должен находиться synchronized блок.
4. В методе removeNote() должен находиться synchronized блок.
5. Synchronized блок в методе addNote() должен блокировать мьютекс notes.
6. Synchronized блок в методе removeNote() должен блокировать мьютекс notes.
7. В synchronized блоке метода addNote() должен находиться вызов метода add у notes.
8. В synchronized блоке метода removeNote() должен находиться вызов метода remove у notes.
9. Все команды вывода на экран не должны находиться в блоке synchronized.*/

public class Solution {

    public static void main(String[] args) {
        Note note = new Note();

        note.addNote(0, "заметка 1");
        note.addNote(1, "заметка 2");
        note.removeNote(0);
    }

    public static class Note {

        public final List<String> notes = new ArrayList<String>();
        private final Object notesLock = new Object();

        public void addNote(int index, String note) {
            synchronized (notesLock) {
                System.out.println("Сейчас будет добавлена заметка [" + note + "] На позицию " + index);
                notes.add(index, note);
                System.out.println("Уже добавлена заметка [" + note + "]");
            }
        }
            public void removeNote ( int index){
                synchronized (notesLock) {
                    System.out.println("Сейчас будет удалена заметка с позиции " + index);
                    String note = notes.remove(index);
                    System.out.println("Уже удалена заметка [" + note + "] с позиции " + index);
                }
            }

        }
    }
