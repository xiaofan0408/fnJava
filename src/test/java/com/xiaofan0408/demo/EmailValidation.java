package com.xiaofan0408.demo;

import com.xiaofan0408.common.Effect;
import com.xiaofan0408.common.Executable;
import com.xiaofan0408.common.Function;
import com.xiaofan0408.common.Result;

import java.util.regex.Pattern;

/**
 * @author xuzefan  2020/1/20 16:18
 */
public class EmailValidation {

    static Pattern emailPattern =
            Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");

    static Effect<String> success = s ->
            System.out.println("Mail sent to " + s);

    static Effect<String> failure = s ->
            System.err.println("Error message logged: " + s);

    static Function<String, Result> emailChecker = s -> {
        if (s == null) {
            return Result.failure("email must not be null");
        } else if (s.length() == 0) {
            return Result.failure("email must not be empty");
        } else if (emailPattern.matcher(s).matches()) {
            return  Result.success(s);
        } else {
            return  Result.failure("email " + s + " is invalid.");
        }
    };

    public static void main(String... args) {
//        validate("this.is@my.email");
//        validate(null);
//        validate("");
//        validate("john.doe@acme.com");
//
//        validate2("this.is@my.email").exec();
//        validate2(null).exec();
//        validate2("").exec();
//        validate2("john.doe@acme.com").exec();

        emailChecker.apply("this.is@my.email").bind(success, failure);
        emailChecker.apply(null).bind(success, failure);
        emailChecker.apply("").bind(success, failure);
        emailChecker.apply("john.doe@acme.com").bind(success, failure);
    }

    private static void logError(String s) {
        System.err.println("Error message logged: " + s);
    }

    private static void sendVerificationMail(String s) {
        System.out.println("Mail sent to " + s);
    }

//    static void validate(String s) {
//        Result result = emailChecker.apply(s);
//        if (result instanceof Result.Success) {
//            sendVerificationMail(s);
//        } else {
//            logError(((Result.Failure) result).getMessage());
//        }
//    }
//
//    static Executable validate2(String s) {
//        Result result = emailChecker.apply(s);
//        if (result instanceof Result.Success) {
//           return () -> sendVerificationMail(s);
//        } else {
//           return() -> logError(((Result.Failure) result).getMessage());
//        }
//    }
}
