import dz.Authenticator;
import dz.WrongLoginException;
import dz.WrongPasswordException;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) {
        Authenticator user1 = new Authenticator("privet", "log3an", "logan");
        System.out.println("checkValidityPassword(user1) = " + checkValidityPassword(user1));
        checkUserData(user1);

    }

    public static boolean checkValidityLogin (Authenticator authenticator) {
        for (int i = 0; i < authenticator.getLogin().length(); i++) {
            char symbol = authenticator.getLogin().charAt(i);
            if (!(symbol == '_'
                    || (symbol >= 'A' && symbol <= 'Z')
                    || (symbol >= 'a' && symbol <= 'z')
                    || (symbol >= '0' && symbol <= '9'))){
                return false;
            }
            if (!(authenticator.getLogin().length() <= 20)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkValidityPassword (Authenticator authenticator) {
        for (int i = 0; i < authenticator.getPassword().length(); i++) {
            char symbol = authenticator.getPassword().charAt(i);
            if (!(symbol == '_'
                    || (symbol >= 'A' && symbol <= 'Z')
                    || (symbol >= 'a' && symbol <= 'z')
                    || (symbol >= '0' && symbol <= '9'))){
                return false;
            }
            if (!authenticator.getPassword().equals(authenticator.getConfirmPassword())) {
                return false;
            }
            if (!((authenticator.getPassword().length() <= 20) || (authenticator.getConfirmPassword().length() <= 20))) {
                return false;
            }
        }
        return true;
    }

    public static void checkAll (Authenticator authenticator) throws WrongLoginException, WrongPasswordException {
        if (!checkValidityLogin(authenticator)) {
            throw new WrongLoginException();
        }
        if (!checkValidityPassword(authenticator)) {
            throw new WrongPasswordException();
        }
    }

    public static void checkUserData (Authenticator authenticator) {
        try {
            checkAll(authenticator);
        } catch (WrongLoginException e) {
            System.out.println("Логин должен состоять из латиницы / длина логина больше 20 символов /");
        } catch (WrongPasswordException e) {
            System.out.println("Пароль должен состоять из латиницы / длина пароля больше 20 символов / пароли не совпадают");
        } finally {
            System.out.println("Проверка завершена");
        }
    }
}