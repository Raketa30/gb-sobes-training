package ru.gb.sobes.hw5;

import ru.gb.sobes.hw5.dao.StudentDB;
import ru.gb.sobes.hw5.domain.Student;

import java.util.List;
import java.util.Optional;

public class HwApplication {
    public static void main(String[] args) {
        StudentDB studentDB = new StudentDB();
        fillStudentDB(studentDB, 100);
        checkUpdateFunc(studentDB, 10L, "testname`1", "testmark1221");
        checkUpdateFunc(studentDB, 20L, "testnam1221e", "test3223mark");
        checkDeletingFunc(studentDB, 11L);
        checkDeletingFunc(studentDB, 22L);
        checkFindAllFunc(studentDB);
    }

    //Добавить 1000 записей в таблицу Student.
    private static void fillStudentDB(StudentDB studentDB, int size) {
        System.out.println("------------------------------------fillStudentDB------------------------------------");
        if(studentDB.getTableRowsCount() == 0) {
            for (int i = 1; i <= size; i++) {
                Student student = new Student();
                student.setName("Name-" + i);
                student.setMark("Mark-" + i);
                studentDB.save(student);
            }
        }
        System.out.println(studentDB.getTableRowsCount());
    }

    //проверка удаления
    private static void checkDeletingFunc(StudentDB studentDB, Long id) {
        System.out.println("------------------------------------checkDeletingFunc------------------------------------");
        System.out.println(studentDB.getTableRowsCount());
        Optional<Student> byId = studentDB.findById(id);
        byId.ifPresent(System.out::println);
        studentDB.deleteById(id);
        System.out.println(studentDB.getTableRowsCount());
    }

    //проверка апдейта
    //проверка поиска по id
    private static void checkUpdateFunc(StudentDB studentDB, Long id, String name, String mark) {
        System.out.println("------------------------------------checkUpdateFunc------------------------------------");
        Optional<Student> optionalStudent = studentDB.findById(id);

        if(optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            System.out.println(student);
            student.setName(name);
            student.setMark(mark);
            studentDB.update(student);
        }

        Optional<Student> optionalStudentTest = studentDB.findById(id);
        optionalStudentTest.ifPresent(System.out::println);
        System.out.println(studentDB.getTableRowsCount());
    }

    private static void checkFindAllFunc(StudentDB studentDB) {
        System.out.println("------------------------------------checkFindAllFunc------------------------------------");
        List<Student> all = studentDB.findAll();
        System.out.println(all.size());
    }

}
