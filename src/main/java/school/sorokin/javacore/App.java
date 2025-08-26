package school.sorokin.javacore;


import java.util.Scanner;

public class App {
    static int capacityContactList = 2;
    static String[] names = new String[capacityContactList];
    static String[] phoneNumbers = new String[capacityContactList];
    static int countContacts = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int userNumber = 0;
        while (userNumber != 5) {
            System.out.println("Выберите действие и затем введите только цифру:\n" +
                    " 1. Добавить контакт\n" +
                    " 2. Просмотреть контакты\n" +
                    " 3. Найти контакт\n" +
                    " 4. Удалить контакт\n" +
                    " 5. Выйти");
            try {
                String input = scanner.nextLine();
                userNumber = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                userNumber = 0;
            }
            switch (userNumber) {
                case 1:
                    addContact();
                    break;
                case 2:
                    getAllContacts();
                    break;
                case 3:
                    getContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Введите только цифру от 1 до 5");
                    break;
            }
        }
        scanner.close();
    }

    private static void addContact() {
        if (countContacts >= capacityContactList) {
            System.out.println("В списке контактов нет места!");
            return;
        }

        System.out.println("Введите сначала имя, а затем на другой строчке - номер телефона");
        String newContactName = scanner.nextLine();
        String newContactPhoneNumber = scanner.nextLine();
        if (inputValid(newContactName) || inputValid(newContactPhoneNumber)) {
            System.out.println("Имя или номер телефона не могут быть пустыми.");
            return;
        }

        if (findContact(newContactName) != -1) {
            System.out.println("Такое имя уже существует!");
            return;
        }
        names[countContacts] = newContactName;
        phoneNumbers[countContacts] = newContactPhoneNumber;
        countContacts++;
        System.out.printf("Контакт добавлен: %s - %s%n", newContactName, newContactPhoneNumber);
    }

    private static void getAllContacts() {
        if (countContacts == 0) {
            System.out.println("Список контактов пуст.");
            return;
        }
        System.out.println("Список контактов: \n");
        for (int i = 0; i < countContacts; i++) {
            System.out.printf("%d. %s - %s%n", i + 1, names[i], phoneNumbers[i]);
        }
    }

    private static void getContact() {
        System.out.print("Имя для поиска: ");
        String findContactName = scanner.nextLine();
        int findContactIndex = findContact(findContactName);
        if (findContactIndex == -1) {
            System.out.printf("Контакт с именем %s не найден.%n", findContactName);
        } else {
            System.out.printf("Телефон %s: %s%n",
                    names[findContactIndex], phoneNumbers[findContactIndex]);
        }
    }

    private static int findContact(String name) {
        if (inputValid(name)) {
            return -1;
        }
        for (int i = 0; i < countContacts; i++) {
            if (names[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private static void deleteContact() {
        System.out.print("Введите имя контакта для удаления: ");
        String deleteContactName = scanner.nextLine();
        if (inputValid(deleteContactName)) {
            System.out.println("Имя для удаления не может быть пустым.");
            return;
        }
        int deleteContactIndex = findContact(deleteContactName);
        if (deleteContactIndex != -1) {
            for (int i = deleteContactIndex; i < countContacts - 1; i++) {
                names[i] = names[i + 1];
                phoneNumbers[i] = phoneNumbers[i + 1];
            }
            names[countContacts - 1] = null;
            phoneNumbers[countContacts - 1] = null;
            countContacts--;
            System.out.printf("Контакт %s успешно удален.%n", deleteContactName);
        } else {
            System.out.printf("Контакт с именем %s не найден!%n", deleteContactName);
        }
        System.out.println();
    }

    private static boolean inputValid(String input) {
        return input == null || input.trim().isEmpty();
    }
}

