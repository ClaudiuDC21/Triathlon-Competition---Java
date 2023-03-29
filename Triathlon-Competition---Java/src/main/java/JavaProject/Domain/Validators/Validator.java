package JavaProject.Domain.Validators;

import JavaProject.Exceptions.ValidationException;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}