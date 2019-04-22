package ie.demo.domain;

public abstract class UserBuilder<T extends UserBuilder<T>> {

    T withUserId(int userId) {
        getUser().setUserId(userId);
        return getThis();
    }

    protected abstract User getUser();

    protected abstract T getThis();
}
