package com.xiaofan0408.common;

public interface Result<T> {

    void bind(Effect<T> success,Effect<String> failure);


    public static <T> Failure<T> failure(String message) {
        return new Failure<>(message);
    }

    public static <T> Success<T> success(T value) {
        return new Success<>(value);
    }

    public class Success<T> implements Result<T>{

        private final T value;

        private Success(T v){
            this.value = v;
        }

        @Override
        public void bind(Effect<T> success, Effect<String> failure) {
            success.apply(value);
        }
    }

    public class Failure<T> implements Result<T>{

        private final String errorMessage;

        public Failure(String s) {
            this.errorMessage = s;
        }

        @Override
        public void bind(Effect<T> success, Effect<String> failure) {
            failure.apply(errorMessage);
        }
    }
}
