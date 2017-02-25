package LIon;

import java.util.*;

import java.io.*;

class Person {
    private String firstName;
    private String surname;
    private String post;

    private int age;

    public Person(String firstName, String surname, String post, int age) {

        this.firstName = firstName;
        this.surname = surname;
        this.post = post;
        this.age = age;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Фамилия: " + firstName + ", Имя: " + surname + ", Должность: " + post + ", Возраст: " + age;
    }

    public static void main(String[] args) {
        Person menu = new Person();
        menu.run();
    }

    private static List<Person> users = new ArrayList<Person>();
    public static final File file = new File("text.txt");

    private void run() {
        loadUser();
        int c;
        do {
            System.out.println("\n1: Добавить пользователя ");
            System.out.println("2: Список пользователей");
            System.out.println("3: Сохранить пользователей");
            System.out.println("4: Переименовать пользователя");
            System.out.println("5: Удалить пользователя");
            System.out.println("6: Обновить информацию о пользователе");
            System.out.println("7: Удалить всех пользователей");
            System.out.println("0: Выход");
            Scanner sc = new Scanner(System.in);
            c = sc.nextInt();
            switch (c) {
                case 1:
                    addUser();
                    break;
                case 2:
                    printUser();
                    break;
                case 3:
                    saveUser();
                    break;
                case 4:
                    editUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    updateUser();
                    break;
                case 7:
                    deleteUsers();
                    break;
                case 0:
                    System.out.println("Выход");
                    break;
            }
        } while (c > 0);

    }

    private void saveUser() {
        try (FileWriter writer = new FileWriter(file, false)) {
            for (Person person : users) {
                writer.write(person.getFirstName() + ",");
                writer.write(person.getSurname() + ",");
                writer.write(person.getPost() + ",");
                writer.write(person.getAge() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Пользователи сохранены");
    }

    private void addUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите фамилию пользователя: ");
        String firstName = sc.next();
        System.out.print("Введите имя пользователя: ");
        String surname = sc.next();
        System.out.print("Введите должность пользователя: ");
        String post = sc.next();
        System.out.print("Введите возраст пользователя: ");
        int age = sc.nextInt();
        Person person = new Person(firstName, surname, post, age);
        users.add(person);
    }

    private void printUser() {
        for (Person person : users) {
            System.out.println(person);
        }
    }

    private void loadUser() {
        try {
            BufferedReader fin = new BufferedReader(new FileReader(file));
            Person person;
            String line = "";
            while ((line = fin.readLine()) != null) {
                person = getPersonFromLine(line);
                users.add(person);
            }
        } catch (Exception e) {
            System.out.println("Создание файла " );
        }
    }

    private Person getPersonFromLine(String line) {

        String[] personDetails = line.split(",");
        String firstName = personDetails[0];
        String surname = personDetails[1];
        String post = personDetails[2];
        int age = Integer.parseInt(personDetails[3]);
        return new Person(firstName, surname, post, age);
    }

    private void editUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите имя существующего пользователя: ");
        String oldName = sc.next();
        String newName = "";
        if (UserAdded(oldName)) {
            System.out.print("Введите новое имя пользователя: ");
            newName = sc.next();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getSurname().equals(oldName)) {
                    users.get(i).setSurname(newName);
                }
            }
        } else {
            System.out.print("Введеное имя не существует!\n");
        }
    }

    private void deleteUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите имя пользователя: ");
        String name = sc.next();
        if (UserAdded(name)) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getSurname().equals(name)) {
                    users.remove(i);
                }
            }
        } else {
            System.out.print("Введеное имя не существует!\n");
        }
    }

    private void deleteUsers() {
        users.clear();
        System.out.println("Пользователи удалены");
    }

    private void updateUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите имя существующего пользователя: ");
        String oldName = sc.next();
        if (UserAdded(oldName)) {
            System.out.print("Введите новую фамилию пользователя: ");
            String newFirstName = sc.next();
            System.out.print("Введите новое имя пользователя: ");
            String newSurname = sc.next();
            System.out.print("Введите новую должность пользователя: ");
            String newPost = sc.next();
            System.out.print("Введите возраст пользователя: ");
            int newAge = sc.nextInt();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getSurname().equals(oldName)) {
                    users.get(i).setFirstName(newFirstName);
                    users.get(i).setSurname(newSurname);
                    users.get(i).setPost(newPost);
                    users.get(i).setAge(newAge);
                }
            }
            System.out.println("Пользователь обновил о себе информацию!");
        } else {
            System.out.print("Введеное имя не существует!\n");
            printUser();
        }
    }

    private boolean UserAdded(String oldName) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getSurname().equals(oldName)) {
                return true;
            }
        }
        return false;
    }
}